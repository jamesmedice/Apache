package com.gft.dataservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_DOCUMENT_POSITION")
public class DocumentPosition extends AbstractPosition{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
}
