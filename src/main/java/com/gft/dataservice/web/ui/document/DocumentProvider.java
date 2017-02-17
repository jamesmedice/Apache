package com.gft.dataservice.web.ui.document;

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
import com.gft.dataservice.entities.Document;
import com.gft.dataservice.entities.DocumentType;
import com.gft.dataservice.repositories.DocumentPositionRepository;
import com.gft.dataservice.repositories.DocumentRepository;

public class DocumentProvider extends SortableDataProvider {
 
	@SpringBean
	private DocumentRepository documentRepository;

	@SpringBean
	private DocumentPositionRepository documentPositionRepository;
	
    class SortableDataProviderComparator implements Comparator<Document>, Serializable {
        public int compare(final Document o1, final Document o2) {
            PropertyModel<Comparable> model1 = new PropertyModel<Comparable>(o1, getSort().getProperty().toString());
            PropertyModel<Comparable> model2 = new PropertyModel<Comparable>(o2, getSort().getProperty().toString());
 
            int result = model1.getObject().compareTo(model2.getObject());
 
            if (!getSort().isAscending()) {
                result = -result;
            }
 
            return result;
        }
 
    }
 
    private List<Document> list = new ArrayList<Document>();
    private SortableDataProviderComparator comparator = new SortableDataProviderComparator();
 
    public DocumentProvider(DocumentType documentType) {
    	Injector.get().inject(this);
        // The default sorting
    	setSort("number", SortOrder.ASCENDING);
    	List<Document> docs = documentRepository.findAllByDocumentType(documentType);
    	list.addAll(docs);
    }
    
    @Override
    public Iterator iterator(long first, long count) { 
        List<Document> newList = new ArrayList<Document>(list);
        Collections.sort(newList, comparator);
        return newList.subList((int)first, (int)(first + count)).iterator();
    }
 
    public IModel<Document> model(final Object object) {
        return new AbstractReadOnlyModel<Document>() {
            @Override
            public Document getObject() {
                return (Document) object;
            }
        };
    }
 
    public long size() {
        return list.size();
    }
 
}

 

