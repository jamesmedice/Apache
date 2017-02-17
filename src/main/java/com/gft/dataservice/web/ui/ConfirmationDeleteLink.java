package com.gft.dataservice.web.ui;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

public class ConfirmationDeleteLink extends ConfirmationLink<IModel>{

	public ConfirmationDeleteLink(String id, String text) {
		super(id, text);
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		
	}

}
