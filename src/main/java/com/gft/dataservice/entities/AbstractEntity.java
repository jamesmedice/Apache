package com.gft.dataservice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified by and created, last modified by date.
 */
@MappedSuperclass
public abstract class AbstractEntity {

	private static final String DEFAULT_AUTHENTICATION_USER = "ADVISOR";

	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 50, updatable = false)
	private String createdBy;

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private Date createdDate = new Date();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 50)
	private String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Date lastModifiedDate = new Date();

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy != null)
			this.createdBy = createdBy;
		else
			this.createdBy = DEFAULT_AUTHENTICATION_USER;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
