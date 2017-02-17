package com.gft.dataservice.entities;

public enum DocumentType {
	OFFER("Offer"), ALLOWANCE("Allowance"), STATEMENT("Statement"), CREDIT("Credit"), ASSIGMENT("Assigment"), HOWTOPAY("Howtopay");

	private String designation;

	private DocumentType(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	@Override
	public String toString() {
		return designation;
	}
};
