package com.gft.dataservice.web.ui.document;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentType;

public class DocumentPanel extends Panel {

	private DocumentModel model;

	public DocumentPanel(String id, DocumentModel model){
		super(id,model);
		this.model = model;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();    
        add(getDatatable());   
		add(getHeadlineLabel());
		add(getaddLink().add(getAddLabel()));
	}
	
	private AjaxLink getaddLink(){
		return new AjaxLink("addDocument"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				Document document = new Document();
				document.setDocumentType(model.getDocumentType() != null ? model.getDocumentType() : DocumentType.OFFER);
				document.setCreatedBy(null);
				model.setDocument(document);
				send(getPage(), Broadcast.BREADTH, new DocumentEventPayload(model,target));
			}  	
		};
	}
	
	private Label getAddLabel(){
		DocumentType docType = model.getDocumentType();
		return new Label("addLabel",new Model(" Add New "));
	}
	
	private Label getHeadlineLabel(){
		DocumentType docType = model.getDocumentType();
		return new Label("documentType",translateDoctypeIntoSalvation(docType));
	}
	
	private DefaultDataTable getDatatable(){
		DocumentType docType = model.getDocumentType();
		final DocumentProvider documentProvider = new DocumentProvider(docType);
        
        List columns = new ArrayList();
        columns.add(new PropertyColumn(new Model("Number"), "number", "number"));
        columns.add(new PropertyColumn(new Model("Designation"), "designation", "designation"));
        columns.add(new PropertyColumn(new Model("Posted on"), "createdDate", "createdDate"));
        columns.add(new AbstractColumn<Document, String>(new Model<String>(""))
                {
                    @Override
                    public void populateItem(Item<ICellPopulator<Document>> cellItem, String componentId,
                        IModel<Document> model)
                    {
                        cellItem.add(new DocumentActionPanel(componentId,model));
                    }
                });
        
         return new DefaultDataTable("datatable", columns, documentProvider, 10);
	}
	
	private String translateDoctypeIntoSalvation(DocumentType docType){
		String salvation = docType != null ? docType.getDesignation() : StringUtils.EMPTY;
		
		if(salvation.equals(DocumentType.OFFER.name()) || salvation.equals(DocumentType.ALLOWANCE.name())){
			salvation+=" & Shares";
		}
		else if(salvation.equals(DocumentType.CREDIT.name()) || salvation.equals(DocumentType.HOWTOPAY.name())){
			salvation += " & Funds";
		}
		else if(salvation.equals(DocumentType.STATEMENT.name()) || salvation.equals(DocumentType.ASSIGMENT.name())){
			salvation += " & Tradding";
		}
		else{
			salvation += " & Contracts";
		}
		return salvation;
	}
}
