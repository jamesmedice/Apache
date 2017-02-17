package com.gft.dataservice.web.controllers;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentPosition;
import com.gft.dataservice.entities.DocumentStatus;
import com.gft.dataservice.entities.DocumentType;
import com.gft.dataservice.repositories.DocumentPositionRepository;
import com.gft.dataservice.repositories.DocumentRepository;


@RestController
@RequestMapping("/data")
public class DocumentResource {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentPositionRepository documentPositionRepository;

	/**
	 * GET  /document/:suchString -> get the "document".
	 */
	@RequestMapping(value = "/document/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Document getDocument(@RequestBody SearchObject suchString) {
		DocumentType type = getDocumenttype(suchString.getDocumentType());
		List<Document> found = documentRepository.findAllByNumberAndDocumentType(suchString.getSuchString(),type);
		return (found.size() > 0 ? found.get(0):null);
	}

	@RequestMapping(value = "/documentsWithCriteria/",
			method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getDocumentWithCriteria(@RequestBody String suchString) {
		List<Document> liste = documentRepository.findAllByNumberLikeOrDesignationLikeOrClientLike(suchString,suchString,suchString);
		return liste;        
	}

	@RequestMapping(value = "/documents/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Document> findAll() {
		return documentRepository.findAll();
	}

	@RequestMapping(value = "/allDocumentsForDocumentType/",
			method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> findAllForDocumentType(@RequestBody String suchString) {
		DocumentType type = getDocumenttype(suchString);
		List<Document> allDocuments =  documentRepository.findAllByDocumentType(type);
		return allDocuments;
	}

	@RequestMapping(value = "/documents/allStatus",
			method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Status> allDocumentStatus(){
		List<Status> allStatus = new ArrayList<Status>();
		for(DocumentStatus status : DocumentStatus.values()){
			allStatus.add(new Status(status.name(),status.getDesignation()));
		}
		return allStatus;
	}

	@RequestMapping(value = "/documents/allDocumentTypes",
			method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DocumentTypeJSON> allDocumentTypes(){
		List<DocumentTypeJSON> allDocumentTypes = new ArrayList<DocumentTypeJSON>();
		for(DocumentType documentType : DocumentType.values()){
			allDocumentTypes.add(new DocumentTypeJSON(documentType.name(),documentType.getDesignation()));
		}
		return allDocumentTypes;
	}

	@RequestMapping(value = "/documents/createPosForAngebot/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DocumentPosition createPosForDocument(@RequestBody SearchObject suchString) {
		System.out.println("REST request to get Deals : {}"+ suchString.getSuchString()+" "+suchString.getDocumentType());
		DocumentType type = getDocumenttype(suchString.getDocumentType());
		Document deals = documentRepository.findOneByNumberAndDocumentType(suchString.getSuchString(),type);
		if(deals != null){
			DocumentPosition pos = new DocumentPosition();
			documentPositionRepository.save(pos);
			deals.getAngebotPositionen().add(pos);
			documentRepository.save(deals);
			return pos;
		}else{
			return null;
		}
	}
		
	@RequestMapping(value = "/documentposition/save/", method = RequestMethod.POST)
	@ResponseBody
	public DocumentPosition save(@RequestBody DocumentPosition angebotPosition) {
		documentPositionRepository.save(angebotPosition);
		return angebotPosition;
	}

	@RequestMapping(value = "/documents/save/", method = RequestMethod.POST)
	@ResponseBody
	public Document saveDocument(@RequestBody Document deals) {
		documentRepository.save(deals);
		return deals;
	}

	@RequestMapping(value = "/documents/delete/", method = RequestMethod.POST)
	@ResponseBody
	public Document deleteDocument(@RequestBody Document deals) {
		documentRepository.delete(deals);
		return deals;
	}
	
	@RequestMapping(value = "/documents/deleteForNumberAndDoctype/", method = RequestMethod.POST)
	public void deleteDocumentForNumberAndDoctype(@RequestBody SearchObject suchString) {
		System.out.println("REST request deleteForNumberAndDoctype : "+ suchString.getSuchString()+" "+suchString.getDocumentType());
		DocumentType type = getDocumenttype(suchString.getDocumentType());
		Document deals = documentRepository.findOneByNumberAndDocumentType(suchString.getSuchString(),type);
		System.out.println("REST request deleteForNumberAndDoctype : deals "+deals.getId());
		documentRepository.delete(deals);
		System.out.println("REST request deleteForNumberAndDoctype : deals has been deleted");
	}
	
	private DocumentType getDocumenttype(String docType){
		for(DocumentType documentType : DocumentType.values()){
			if(documentType.name().equals(docType)){
				return documentType;
			}
		}
		return null;
	} 

}

class Status {
	private String name;
	private String designation;
	public Status(String name,String designation){
		this.name=name;
		this.designation=designation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
}

class DocumentTypeJSON {
	private String name;
	private String designation;
	public DocumentTypeJSON(String name,String designation){
		this.name=name;
		this.designation=designation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
}

class SearchObject {
    private String suchString;
    private String documentType;
	public String getSuchString() {
		return suchString;
	}
	public void setSuchString(String suchString) {
		this.suchString = suchString;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}  
}