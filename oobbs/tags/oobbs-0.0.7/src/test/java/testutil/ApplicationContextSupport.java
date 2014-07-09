package testutil;

import oobbs.domainmodel.DomainServiceLocator;
import oobbs.domainmodel.DomainServiceLocatorSpringImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-infrastructure.xml",
								   "classpath:/applicationContext-domainModel.xml",
								   "classpath:/applicationContext-test.xml"})
public class ApplicationContextSupport{
	
	private DomainServiceLocatorSpringImpl domainServiceLocatorSpringImpl;
	
	@Before
	public void setUp(){
		DomainServiceLocator.injectInstance(domainServiceLocatorSpringImpl);
	}
	
	@After
	public void tearDown() {
		DomainServiceLocator.clearInstance();
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		domainServiceLocatorSpringImpl = new DomainServiceLocatorSpringImpl(applicationContext);
	}

}
