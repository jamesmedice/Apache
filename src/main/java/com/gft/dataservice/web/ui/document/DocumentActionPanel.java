package com.gft.dataservice.web.ui.document;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.gft.dataservice.entities.Document;
import com.gft.dataservice.repositories.DocumentRepository;
import com.gft.dataservice.web.ui.ConfirmationDeleteLink;
import com.gft.dataservice.web.ui.start.HomePage;

public class DocumentActionPanel extends Panel {

	@SpringBean
	private DocumentRepository documentRepository;
	private IModel<Document> model;
	
	public DocumentActionPanel(String id, IModel<Document> model) {
		super(id, model);
		this.model = model;
	}
	
	public DocumentActionPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(deleteLink());
		add(getEditLink());
	}
	
	private BookmarkablePageLink< String> getEditLink(){
		BookmarkablePageLink<String> link =  new BookmarkablePageLink<String>("edit",HomePage.class);
		link.add(new AjaxEventBehavior ("onclick") {
		    @Override
		    protected void onEvent(AjaxRequestTarget target){
				send(getPage(), Broadcast.BREADTH, new DocumentEventPayload(model,target));
		    }
		 });
		return link;
	}
	
	private ConfirmationDeleteLink deleteLink(){
		return new ConfirmationDeleteLink("delete",model.getObject().getDesignation()+" delete record?"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				DocumentEventPayload eventPayLoad = new DocumentEventPayload(model.getObject().getDocumentType(),target,true);
				documentRepository.delete(model.getObject());
				send(getPage(), Broadcast.EXACT, eventPayLoad);
			}
		};
	}
}