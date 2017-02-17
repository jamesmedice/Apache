package com.gft.dataservice.web.ui.start;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentType;
import com.gft.dataservice.web.ui.EventPayload;
import com.gft.dataservice.web.ui.client.ClientEventPayload;
import com.gft.dataservice.web.ui.client.ClientPanel;
import com.gft.dataservice.web.ui.dashboard.DashboardPanel;
import com.gft.dataservice.web.ui.document.DocumentDetailsPanel;
import com.gft.dataservice.web.ui.document.DocumentEventPayload;
import com.gft.dataservice.web.ui.document.DocumentModel;
import com.gft.dataservice.web.ui.document.DocumentPanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final String OFFERLINKID = "offerLink";
	private static final String STATEMENTLINKID = "statementLink";
	private static final String PAYMENTLINKID = "paymentLink";
	private static final String ALLOWANCELINKID = "allowanceLink";
	private static final String CREDITLINKID = "creditLink";
	private static final String DASHBOARDLINKID = "dashboardLink";
	private static final String CLIENTLINKID = "clientLink";
	private static final String PAGECONTENTID = "pageContent";
	
	public HomePage(final PageParameters parameters) {
		super(parameters);
		setOutputMarkupId(true);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new DashboardPanel(PAGECONTENTID).setOutputMarkupId(true));
		add(generateBookmarkablePageLink(null,CLIENTLINKID));
		add(generateBookmarkablePageLink(DocumentType.OFFER,OFFERLINKID));
		add(generateBookmarkablePageLink(DocumentType.STATEMENT,STATEMENTLINKID));
		add(generateBookmarkablePageLink(DocumentType.HOWTOPAY,PAYMENTLINKID));
		add(generateBookmarkablePageLink(DocumentType.ALLOWANCE,ALLOWANCELINKID));
		add(generateBookmarkablePageLink(DocumentType.CREDIT,CREDITLINKID));
		add(generateBookmarkablePageLink(null,DASHBOARDLINKID));
		
		add(new WebMarkupContainer("modalWindowPlaceHolder").setOutputMarkupPlaceholderTag(true));
	}
  

	public BookmarkablePageLink<String> generateBookmarkablePageLink(final DocumentType documentType,final String id){
		BookmarkablePageLink link =  new BookmarkablePageLink<String>(id,HomePage.class);
		link.add(new AjaxEventBehavior ("onclick") {
			@Override
			protected void onEvent(AjaxRequestTarget target){
				Component pageContent = getPage().get(PAGECONTENTID);
				if(pageContent instanceof DashboardPanel){
					if(documentType == null && id.equals(CLIENTLINKID)){
						pageContent.replaceWith(new ClientPanel(PAGECONTENTID).setOutputMarkupId(true));
					}else{
						pageContent.replaceWith(new DocumentPanel(PAGECONTENTID,new DocumentModel(documentType)).setOutputMarkupId(true));
					}
				}else{
					if(documentType == null){
						if(id.equals(DASHBOARDLINKID)){
							pageContent.replaceWith(new DashboardPanel(PAGECONTENTID).setOutputMarkupId(true));	
						}else{
							pageContent.replaceWith(new ClientPanel(PAGECONTENTID).setOutputMarkupId(true));
						}
					}else{
						pageContent.replaceWith(new DocumentPanel(PAGECONTENTID,new DocumentModel(documentType)).setOutputMarkupId(true));

					}
				}
				target.add(getPage());
				target.add(pageContent);
			}
		});
		return link;
	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);
		if (event.getPayload() instanceof EventPayload) {
			EventPayload actionEvent = (EventPayload) event.getPayload();
			Component pageContent = getPage().get(PAGECONTENTID);
			if(pageContent instanceof DashboardPanel){
				if(actionEvent instanceof DocumentEventPayload){
					pageContent.replaceWith(new DocumentPanel(PAGECONTENTID, new DocumentModel(((DocumentEventPayload)actionEvent).getDocumentType())).setOutputMarkupId(true));
				}else{
					pageContent.replaceWith(new ClientPanel(PAGECONTENTID).setOutputMarkupId(true));
				}
			}else if(actionEvent instanceof ClientEventPayload){ 
					pageContent.replaceWith(new ClientPanel(PAGECONTENTID).setOutputMarkupId(true)); 
			}else if(actionEvent instanceof DocumentEventPayload){
					if(((DocumentEventPayload)actionEvent).isDeletAction()){
						pageContent.replaceWith(new DocumentPanel(PAGECONTENTID,new DocumentModel(((DocumentEventPayload)actionEvent).getDocumentType())).setOutputMarkupId(true));	
					}else{
						pageContent.replaceWith(new DocumentDetailsPanel(PAGECONTENTID,new DocumentModel((Document)((DocumentEventPayload)actionEvent).getModel().getObject())).setOutputMarkupId(true));
					}
			}
			 	actionEvent.getTarget().add(getPage());
				actionEvent.getTarget().add(pageContent); 
		}
	}
    
}
