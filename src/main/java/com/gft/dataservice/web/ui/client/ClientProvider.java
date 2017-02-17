package com.gft.dataservice.web.ui.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.repositories.ClientRepository;

public class ClientProvider extends SortableDataProvider {
 
	@SpringBean
	private ClientRepository clientRepository;
	
    private List<Client> list = new ArrayList<Client>();
    private SortableDataProviderComparator comparator = new SortableDataProviderComparator();
	
    class SortableDataProviderComparator implements Comparator<Client>, Serializable {
        public int compare(final Client o1, final Client o2) {
            PropertyModel<Comparable> model1 = new PropertyModel<Comparable>(o1, getSort().getProperty().toString());
            PropertyModel<Comparable> model2 = new PropertyModel<Comparable>(o2, getSort().getProperty().toString());
 
            int result = model1.getObject().compareTo(model2.getObject());
 
            if (!getSort().isAscending()) {
                result = -result;
            }
 
            return result;
        } 
    } 
    
    public void restClientProvide(){
    	this.list = new ArrayList<Client>();
    }
    
 
    public ClientProvider() {
    	Injector.get().inject(this);
    	setSort("number", SortOrder.ASCENDING);
    	List<Client> client = getAllItems();
    	list.addAll(client);
    }

	public void setList(List<Client> list) {
		this.list = list;
	}


	public List<Client> getAllItems() { 
		return clientRepository.findAll();
	}
    
    @Override
    public Iterator iterator(long first, long count) {
       
        List<Client> newList = new ArrayList<Client>(list); 
        Collections.sort(newList, comparator);

        return newList.subList((int)first, (int)(first + count)).iterator();
    }
 
    public IModel<Client> model(final Object object) {
        return new AbstractReadOnlyModel<Client>() {
            @Override
            public Client getObject() {
                return (Client) object;
            }
        };
    }
 
    public long size() {
        return list.size();
    }
 
}
