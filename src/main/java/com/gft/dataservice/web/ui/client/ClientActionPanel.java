package com.gft.dataservice.web.ui.client;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.repositories.ClientRepository;
import com.gft.dataservice.web.ui.ConfirmationDeleteLink;
import com.gft.dataservice.web.ui.client.edit.ModalWindowContentPage;
import com.gft.dataservice.web.ui.start.HomePage;

public class ClientActionPanel extends Panel {

	@SpringBean
	private ClientRepository clientRepository;	
	private ModalWindowContentPage modalWindowContent;
	private IModel<Client> clientModel;
		
	public ClientActionPanel(String id, IModel<Client> model) {
		super(id);
		this.clientModel = model;
		this.modalWindowContent = modalWindowContent;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		add(getDeleteLink());
		add(getEditLink());
	}
	
	private ConfirmationDeleteLink getDeleteLink(){
		ConfirmationDeleteLink deleteLink = new ConfirmationDeleteLink("delete","Are you sure to remove this Customer?"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				ClientEventPayload<Client> eventPayLoad =  new ClientEventPayload<Client>(clientModel, target);
				clientRepository.delete(clientModel.getObject());
				send(getPage(), Broadcast.EXACT, eventPayLoad);
			}
		};
		deleteLink.setOutputMarkupId(true);
		return deleteLink;
	}
	
	private BookmarkablePageLink<String> getEditLink(){
		BookmarkablePageLink  link = new BookmarkablePageLink<String>("edit",HomePage.class);
		link.add(new AjaxEventBehavior ("onclick") {
		    @Override
		    protected void onEvent(AjaxRequestTarget target){
		    ModalWindow contentWindow = new ModalWindowContentPage(new ClientModel(clientModel.getObject()));
		    getPage().replace(contentWindow);
			contentWindow.show(target); 		    
		    }
		 });
		return link;
	}
}
