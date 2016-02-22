package com.civil.web.configuration;

import com.civil.configuration.EduOnlineDomainApplicationConfig;
import com.vzs.mvc.configuration.AbstractVzsWebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by byao on 4/21/15.
 */
public class CivilWebApplicationInitializer extends AbstractVzsWebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        loadLogbackConfigListener(servletContext);
        loadRootApplicationContext(servletContext, EduOnlineDomainApplicationConfig.class);
        loadDefaultFilters(servletContext);
        addDispatcherServlet(servletContext, "webServlet", CivilWebServletApplicationContextConfig.class, "/");
    }
}
