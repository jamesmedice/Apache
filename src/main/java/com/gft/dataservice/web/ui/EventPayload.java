package com.gft.dataservice.web.ui;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

public interface EventPayload<AbstractAuditingEntity> {

	public AjaxRequestTarget getTarget();
	
	public IModel<AbstractAuditingEntity> getModel();

}
