package com.gft.dataservice.web.ui.client.edit;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.web.ui.client.ClientEventPayload;
import com.gft.dataservice.web.ui.client.ClientModel;

@SuppressWarnings("serial")
public class ModalWindowContentPage extends ModalWindow {

	private ClientModel clientModel;
	private boolean feedbackMessage = false; 

	public ModalWindowContentPage(String id, ClientModel clientModel) {
		super(id);
		this.clientModel = clientModel;
	}

	public ModalWindowContentPage(ClientModel clientModel) {
		this("modalWindowPlaceHolder", clientModel);
		this.clientModel = clientModel;
	}
	
	@Override
	protected void onInitialize() { 
		super.onInitialize();
		this.setInitialWidth(650);
		this.setInitialHeight(200);
		this.setResizable(false);
		this.setOutputMarkupId(true);
		this.setContent(setMainPanel("content", clientModel));
		
		this.setWindowClosedCallback(new WindowClosedCallback() {

			@Override
			public void onClose(AjaxRequestTarget target) {
				if(feedbackMessage){
					ClientEventPayload<Client> eventPayLoad = new ClientEventPayload<Client>(clientModel, target);
					send(getPage(), Broadcast.EXACT, eventPayLoad);
				}
			}
		});
	}
	
	public ModalPanelContent setMainPanel(String id, ClientModel clientModel){
		return new ModalPanelContent(id, clientModel) {

			@Override
			void onCloseModal(AjaxRequestTarget target, boolean refreshPage) {
				close(target);
				feedbackMessage = refreshPage;
			}
			
		};
	}
}
