package com.gft.dataservice.web.ui.dashboard;

import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentType;
import com.gft.dataservice.repositories.DocumentRepository;
import com.gft.dataservice.web.ui.client.ClientEventPayload;
import com.gft.dataservice.web.ui.document.DocumentEventPayload;
import com.gft.dataservice.web.ui.start.HomePage;

public class DashboardPanel extends Panel {

	@SpringBean
	private DocumentRepository documentRepository;
	
	private static final String CLIENTLINKID = "clientLink";
	private static final String OFFERLINKID = "offerLink";
	private static final String BILLLINKID = "billLink";
	private static final String ALLOWANCELINKID = "allowanceLink";
	
	public DashboardPanel(String id, IModel<?> model) {
		super(id, model);
	}

	public DashboardPanel(String id) {
		super(id);
		add(generateBookmarkablePageLink(null,CLIENTLINKID));
		add(generateBookmarkablePageLink(DocumentType.OFFER,OFFERLINKID));
		add(generateBookmarkablePageLink(DocumentType.STATEMENT,BILLLINKID));
		add(generateBookmarkablePageLink(DocumentType.ALLOWANCE,ALLOWANCELINKID));
		
		add(new Label("cntAG",getCountForDocumentType(DocumentType.OFFER)));
		add(new Label("cntRE",getCountForDocumentType(DocumentType.STATEMENT)));
		add(new Label("cntAM",getCountForDocumentType(DocumentType.ALLOWANCE)));
	}
	
	public BookmarkablePageLink<String> generateBookmarkablePageLink(final DocumentType documentType,String id){
		BookmarkablePageLink link =  new BookmarkablePageLink<String>(id,HomePage.class);
		link.add(new AjaxEventBehavior ("onclick") {
            @Override
            protected void onEvent(AjaxRequestTarget target){
				send(getPage(), Broadcast.BREADTH, documentType == null ? new ClientEventPayload(target): new DocumentEventPayload(documentType,target));
            }
         });
		return link;
	}
	
	private int getCountForDocumentType(DocumentType documentType){
		List<Document> docs = documentRepository.findAllByDocumentType(documentType);
		return docs.size();
	}
}
