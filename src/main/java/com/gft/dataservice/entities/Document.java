package com.gft.dataservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "T_DOCUMENT")
public class Document extends AbstractEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 100)
    private String number;

	@Column(length = 100)
    private String designation;
	
	@Column(length = 100)
    private String client;
	
	@Enumerated(EnumType.STRING)
    private DocumentStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "document_type")
    private DocumentType documentType;
	
	
    @Column(name = "date")
    private Date date = new Date();
    
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "offer_id")
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DocumentPosition> designationPosition = new HashSet<DocumentPosition>();
    
    public Long getId() {
		return id;
	}

	public Set<DocumentPosition> getAngebotPositionen() {
		return designationPosition;
	}

	public void setAngebotPositionen(Set<DocumentPosition> designationPosition) {
		this.designationPosition = designationPosition;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

}
