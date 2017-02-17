package com.gft.dataservice.web.ui.document;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentType;
import com.gft.dataservice.web.ui.EventPayload;

@SuppressWarnings("hiding")
public class DocumentEventPayload<Document> implements EventPayload<Document>{
	private DocumentType documentType;
	private AjaxRequestTarget target;
	private IModel<Document> model;
	private boolean isDeletAction=false;
	
	
	public DocumentEventPayload(DocumentType documentType,AjaxRequestTarget target,boolean isDeletAction) {
		this.documentType = documentType;
		this.target = target;
		this.isDeletAction = isDeletAction;
	}
	
	public DocumentEventPayload(DocumentType documentType,AjaxRequestTarget target) {
		this.documentType = documentType;
		this.target = target;
	}

	public DocumentEventPayload(IModel<Document> model,AjaxRequestTarget target) {
		this.target = target;
		this.model = model;
	}
	
	public DocumentType getDocumentType() {
		return documentType;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}
	
	public IModel<Document> getModel() {
		return model;
	}

	public boolean isDeletAction() {
		return isDeletAction;
	}


}
