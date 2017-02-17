package com.gft.dataservice.web.ui.document;

import java.util.Date;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentStatus;
import com.gft.dataservice.entities.DocumentType;
import com.gft.dataservice.repositories.ClientRepository;
import com.gft.dataservice.repositories.DocumentRepository;

public class DocumentModel extends LoadableDetachableModel<Document> {

	@SpringBean
	private DocumentRepository documentRepository;
	@SpringBean
	private ClientRepository clientRepository;
	
	private DocumentStatus selectedStatus = null;
	private Date selectedDate = new Date();
	private DocumentType documentType =null;
	private Document document;
	private Long id ;

	public DocumentModel(Document document) {
		Injector.get().inject(this);
		this.document = document;
		selectedStatus  = document.getStatus();
		selectedDate = document.getDate() == null ? new Date() : document.getDate();
	}

	public DocumentModel(DocumentType documentType) {
		Injector.get().inject(this);
		this.documentType = documentType;
	}
	
	public DocumentModel(Long id) {
		Injector.get().inject(this);
		this.id = id;
	}

	public DocumentRepository getDocumentRepository() {
		return documentRepository;
	}

	public ClientRepository getKundeRepository() {
		return clientRepository;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public DocumentStatus getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(DocumentStatus selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	@Override
	protected Document load() {
		if (id != null) {
			document = (Document) documentRepository.getOne(this.id);
		}
		return document;
	}
	
	public void save(){
		document.setStatus(selectedStatus);
		document.setDate(selectedDate);
		documentRepository.save(document);
		clientRepository.saveAndFlush(new Client(document.getNumber(), document.getClient()));
	}
}