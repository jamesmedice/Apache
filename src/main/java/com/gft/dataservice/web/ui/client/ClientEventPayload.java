package com.gft.dataservice.web.ui.client;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import com.gft.dataservice.entities.Client;
import com.gft.dataservice.web.ui.EventPayload;

@SuppressWarnings("hiding")
public class ClientEventPayload<Client> implements EventPayload<Client>{
	private AjaxRequestTarget target;
	private IModel<Client> model;

	public ClientEventPayload(AjaxRequestTarget target) {
		this.target = target;
	}

	public ClientEventPayload(IModel<Client> model, AjaxRequestTarget target) {
		this.target = target;
		this.model = model;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}
	
	public IModel<Client> getModel() {
		return model;
	}

	public void setModel(IModel<Client> model) {
		this.model = model;
	}

}
