package com.gft.dataservice.web.ui;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.gft.dataservice.web.ui.start.HomePage;

public class WicketApplication extends WebApplication
{    	

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	public void init()
	{
		super.init();
        onInitialize();
	}

    protected void onInitialize() {
    	// Integrate Spring with Wicket
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
    
	public final Session newSession(Request request, Response response) {
		return new WicketSession(request);
	}
}
