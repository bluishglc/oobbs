package testutil;

import oobbs.domainmodel.DomainServiceLocator;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DomainServiceLocatorForTest extends DomainServiceLocator implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		DomainServiceLocator.injectInstance(this);
	}

	@Override
	public Object locate(String serviceName) {
		return applicationContext.getBean(serviceName);
	}
}
