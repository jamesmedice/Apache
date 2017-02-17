package com.gft.dataservice.web.ui.client.edit;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.web.ui.client.ClientModel;

@SuppressWarnings("serial")
public abstract class ModalPanelContent extends Panel {

	private FeedbackPanel feedbackpanel;
	private ClientModel clientModel;
	private Form<Client> modelForm;
	private boolean hasFeedBackMessage = false;

	public ModalPanelContent(String id, ClientModel clientModel) {
		super(id, clientModel);
		this.clientModel = clientModel;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		modelForm = new Form<Client>("form");
		modelForm.add(getNumberField());
		modelForm.add(getDesignationField());
		modelForm.add(saveClient());
		modelForm.add(resetAction());
		modelForm.setOutputMarkupId(true);
		this.add(modelForm);
		this.add(closeAction().add(getLabelClose()));
		feedbackpanel = new FeedbackPanel("feedback");
		this.add(feedbackpanel.setOutputMarkupId(true));
	}
	
	private AjaxSubmitLink saveClient() {
		AjaxSubmitLink subLink = new AjaxSubmitLink("saveClient") {
			
			@Override
		protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
			hasFeedBackMessage = true;
			success(new StringResourceModel("save.success.message",this,null).getObject());
			target.add(feedbackpanel);
		}
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				clientModel.save();
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);
				error(new StringResourceModel("save.error.message",this,null).getObject());
				target.add(feedbackpanel);
			}
		
		};
		subLink.setOutputMarkupId(true);
		return subLink;
	}
		
	private AjaxLink closeAction() {
		AjaxLink closeLink = new AjaxLink("closeAction") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				onCloseModal(target, hasFeedBackMessage);
			}
		};
		closeLink.setOutputMarkupId(true);
		return closeLink;
	}
	

	private AjaxLink resetAction() {
		AjaxLink resetLink =  new AjaxLink("resetClient") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				resetModelForm(target);
			}

		};
		return resetLink;
	}

	private void resetModelForm(AjaxRequestTarget target) {
		modelForm.clearInput();
		target.add(modelForm);
	}
	
	private Component getLabelClose() {
		return new Label("closeLabel", "Close");
	}

	private RequiredTextField<Client> getNumberField() {
		return new RequiredTextField<Client>("number", new PropertyModel<Client>(this, "clientModel.client.number"));
	}

	private TextField<Client> getDesignationField() {
		return new TextField<Client>("designation", new PropertyModel<Client>(this, "clientModel.client.designation"));
	}

	abstract void onCloseModal(AjaxRequestTarget target, boolean refreshPage) ;
	}
