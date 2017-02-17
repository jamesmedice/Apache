package com.gft.dataservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "T_CLIENT")
public class Client extends AbstractEntity implements Serializable {

	public Client() {
		this(StringUtils.EMPTY, StringUtils.EMPTY);
	}

	public Client(String number, String designation) {
		super();
		this.number = number;
		this.designation = designation;
		this.setCreatedDate(new Date());
		this.setCreatedBy("SUPER");
		this.setLastModifiedDate(new Date());
		this.setLastModifiedBy("ADVISOR");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100)
	private String number;

	@Column(length = 100)
	private String designation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public String getDesignation() {
		return designation;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
