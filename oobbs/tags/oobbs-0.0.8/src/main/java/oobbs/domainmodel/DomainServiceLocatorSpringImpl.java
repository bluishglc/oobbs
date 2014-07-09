package oobbs.domainmodel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * The Class DomainServiceLocatorSpringImpl.
 */
public class DomainServiceLocatorSpringImpl extends DomainServiceLocator implements ServletContextListener {
	
	private ApplicationContext applicationContext;
	
	public DomainServiceLocatorSpringImpl(){}
	
	public DomainServiceLocatorSpringImpl(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	
	public Object locate(String serviceName){
		return applicationContext.getBean(serviceName);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		DomainServiceLocator.clearInstance();
	}

	public void contextInitialized(ServletContextEvent sce) {
		this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		DomainServiceLocator.injectInstance(this);
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
