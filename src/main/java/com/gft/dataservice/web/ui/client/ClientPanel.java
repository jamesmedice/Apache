package com.gft.dataservice.web.ui.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.web.ui.client.edit.ModalWindowContentPage;

public class ClientPanel extends Panel {

	private ClientModel clientModel;
	private static final String PAGECONTENTID = "pageContent";
	private static final String DATATABLE = "datatable";

	public ClientPanel(String id) {
		super(id);
		this.setOutputMarkupId(true);
		this.clientModel = new ClientModel(new Client());
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(getDataTable());
		add(addNewClient().add(addLabelLink()));
	}

	private AjaxLink addNewClient() {
		AjaxLink addNew = new AjaxLink("addNewClient") {

			@Override
			public void onClick(AjaxRequestTarget target) { 
				ModalWindowContentPage contentWindow = new ModalWindowContentPage(clientModel);
	            getPage().replace(contentWindow);
				contentWindow.show(target); 
			}
		};
		addNew.setOutputMarkupId(true);
		return addNew;
	}

	private Label addLabelLink() {
		Label newLink = new Label("addNew", new Model(" Add New Client "));
		newLink.setOutputMarkupId(true);
		return newLink;
	}

	private DefaultDataTable getDataTable() {
		 DefaultDataTable dataTable = new DefaultDataTable(DATATABLE, getTableColumns(), new ClientProvider(), 10){
			@Override
			protected Item newRowItem(String id, int index, IModel model) {				
				return super.newRowItem(id, index, model);
			}
		};
		dataTable.setDefaultModel(clientModel);
		dataTable.setOutputMarkupId(true);
		return dataTable;
	}

	private List getTableColumns() {
		List columns = new ArrayList();
		columns.add(new PropertyColumn(new Model("Number"), "number", "number"));
		columns.add(new PropertyColumn(new Model("Designation"), "designation",	"designation"));
		columns.add(new PropertyColumn(new Model("Created On"), "createdDate", "createdDate"));
		columns.add(new AbstractColumn<Client, String>(new Model<String>("")) {
			@Override
			public void populateItem(Item<ICellPopulator<Client>> cellItem,	String componentId, IModel<Client> model) {
				cellItem.add(new ClientActionPanel(componentId, model));
			}
		});
		return columns;
	}
}
