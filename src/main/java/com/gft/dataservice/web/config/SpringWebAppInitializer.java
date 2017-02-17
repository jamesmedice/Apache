package com.gft.dataservice.web.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.gft.dataservice.config.AppConfig;
import com.gft.dataservice.web.ui.WicketApplication;

/**
 * @author TMedice
 * 
 */
public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class<?>[] { WebMvcConfig.class, RepositoryRestMvcConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {

	return new String[] { "/rest/*" };
    }

    @Override
    protected Filter[] getServletFilters() {
	return new Filter[] {
	// new DelegatingFilterProxy("springSecurityFilterChain")
	new OpenEntityManagerInViewFilter() };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
	FilterRegistration.Dynamic filter = servletContext.addFilter("com.gft.dataservice.web.ui", WicketFilter.class.getName());
	filter.setInitParameter("applicationClassName", WicketApplication.class.getName());
	filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
	filter.addMappingForUrlPatterns(null, false, "/*");
	super.onStartup(servletContext);
    }

}
