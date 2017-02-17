package com.gft.dataservice.web.ui.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.string.Strings;

import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentStatus;
import com.gft.dataservice.entities.Client;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;

@SuppressWarnings("serial")
public class DocumentDetailsPanel extends Panel {

	private FeedbackPanel feedbackpanel;
	
	private DocumentModel documentModel;


	public DocumentDetailsPanel(String id, DocumentModel model) {
		super(id, model);
		this.documentModel = model;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form<Document> form = new Form<Document>("form");	
		form.add(getNumberTextfield());	
		form.add(getDesignationTextfield().setRequired(true));
		form.add(getStatusChoice());
		form.add(getDocumentDateDatefield());
		form.add(getClientCompleteComponent());
		form.add(saveLink());
		feedbackpanel = new FeedbackPanel("feedback");
		add(feedbackpanel.setOutputMarkupId(true));
		add(new Label("salvation",documentModel.getDocument().getDocumentType().getDesignation()));
		add(form);
	}

	private RequiredTextField<Document> getNumberTextfield(){
		return  new RequiredTextField<Document>("number", new PropertyModel<Document>(this,"documentModel.document.number"));
	}
	
	private TextField<Document> getDesignationTextfield(){
		return new TextField<Document>("designation", new PropertyModel<Document>(this,"documentModel.document.designation"));
	}
	
	private DropDownChoice<DocumentStatus> getStatusChoice(){
		return new DropDownChoice<DocumentStatus>(
				"statusChoice", new PropertyModel<DocumentStatus>(this, "documentModel.selectedStatus"), allDocumentStatus());
	}
	
	private DateTextField getDocumentDateDatefield(){
		DateTextFieldConfig conf =  new DateTextFieldConfig()
		.autoClose(true)
		.withFormat(new StringResourceModel("date.format",this,null).getObject()).showTodayButton(true).withLanguage(new StringResourceModel("date.language",this,null).getObject());
		return new DateTextField("dateTextField",new PropertyModel<Date>(this, "documentModel.selectedDate"),conf);
	}
	
	private AjaxSubmitLink saveLink(){
		return new AjaxSubmitLink("save"){
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				success(new StringResourceModel("save.success.message",this,null).getObject());
				documentModel.save();
				target.add(feedbackpanel);
			}
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);
				target.add(feedbackpanel);
			}
		};
	}
	
	@SuppressWarnings("serial")
	private AutoCompleteTextField<String> getClientCompleteComponent(){
	AutoCompleteTextField<String> clientAutoComplete = new AutoCompleteTextField<String>("client",
			new PropertyModel<String>(this,"documentModel.document.client"))
			{
		@Override
		protected Iterator<String> getChoices(String input)
		{
			if (Strings.isEmpty(input))
			{
				List<String> emptyList = Collections.emptyList();
				return emptyList.iterator();
			}

			List<String> choices = new ArrayList<String>(10);

			List<Client> clients = documentModel.getKundeRepository().findAll();

			for (final Client client : clients)
			{
				final String clientName = client.getDesignation();

				if (clientName.toUpperCase().startsWith(input.toUpperCase()))
				{
					choices.add(clientName);
					if (choices.size() == 10)
					{
						break;
					}
				}
			}

			return choices.iterator();
		}
			};
			return clientAutoComplete;
	}
	
	private List<DocumentStatus> allDocumentStatus(){
		List<DocumentStatus> allStatus = new ArrayList<DocumentStatus>();
		for(DocumentStatus status : DocumentStatus.values()){
			allStatus.add(status);
		}
		return allStatus;
	}

}
