package oobbs.domainmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oobbs.domainmodel.DomainServiceLocator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

public class DomainServiceLocatorSpringImplTest  {
	
    private MockServletContext mockServletContext;
    private ServletContextListener listener;
    private ContextLoaderListener springListener;
    
    @Before
    public void setUp(){
        mockServletContext = new MockServletContext("");
        
        // initialize Spring
        mockServletContext.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
        		"classpath:/applicationContext-infrastructure.xml, " +
                "classpath:/applicationContext-domainModel.xml, " +
                "classpath:/applicationContext-application.xml, ");

        springListener = new ContextLoaderListener();
        springListener.contextInitialized(new ServletContextEvent(mockServletContext));
        listener = new oobbs.domainmodel.DomainServiceLocatorSpringImpl();
    }
    
    @After
    public void tearDown(){
        springListener = null;
        listener = null;
        mockServletContext = null;
        DomainServiceLocator.clearInstance();
    }

    @Test
	public void testContextInitialized() {
		listener.contextInitialized(new ServletContextEvent(mockServletContext));
		assertNotNull("DomainServiceLocator is null!", DomainServiceLocator.getInstance());
		assertEquals((DomainServiceLocator.getInstance()) instanceof DomainServiceLocatorSpringImpl, true);
	}	
}
