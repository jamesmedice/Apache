package com.gft.dataservice.web.ui.client;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.repositories.ClientRepository;

public class ClientModel extends LoadableDetachableModel<Client> {

	@SpringBean
	private ClientRepository clientRepository;

	private Client client;
	private Long id;

	public ClientModel(Client client) {
		Injector.get().inject(this);
		this.client = client;
	}

	public ClientModel(Long id) {
		Injector.get().inject(this);
		this.id = id;
	}

	@Override
	protected Client load() {
		if (id != null) {
			client = (Client) clientRepository.getOne(this.id);
		}
		return client;
	}

	public Client getClient() {
		return client;
	}

	public Long getId() {
		return id;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void save() {
		clientRepository.save(client);
	}

}
