package com.gft.dataservice.entities;

public enum DocumentStatus {
    CREATED("Created"), PROCESSING("In process"), ENABLED("Enable"), RELEASED("Release");
    
    private String designation;

    private DocumentStatus(String designation) {
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

