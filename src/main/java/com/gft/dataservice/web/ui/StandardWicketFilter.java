package com.gft.dataservice.web.ui;

import org.apache.wicket.protocol.http.WicketFilter;

public class StandardWicketFilter extends WicketFilter {

    @Override
    protected ClassLoader getClassLoader() {

	return this.getClass().getClassLoader();
    }
}
