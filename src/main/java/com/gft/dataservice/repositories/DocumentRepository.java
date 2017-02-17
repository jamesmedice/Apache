package com.gft.dataservice.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentType;


/**
 * Spring Data JPA repository for the User entity.
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findAllByNumberAndDocumentType(String number,DocumentType documentType);
    
    Document findOneByNumberAndDocumentType(String number,DocumentType documentType);
    
    List<Document> findAllByNumberLikeOrDesignationLikeOrClientLike(String number,String designation,String client );
    
    List<Document> findAllByCreatedDateBefore(Date dateTime);
    
    List<Document> findAllByDocumentType(DocumentType documentType);

    Document findOneByDesignation(String email);


}
