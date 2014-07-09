package oobbs.infrastructure.appcontext;

import oobbs.domainmodel.DomainObejctListener;
import oobbs.domainmodel.forum.ForumEventDispatcher;
import oobbs.domainmodel.forum.ThreadEventDispatcher;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The ApplicationBeanPostProcessor will do some initialization work when bean is created by spring ioc container,
 * such as: Adding listeners for domain event dispatchers and so on. 
 * @author laurence.geng
 */
public class ApplicationBeanPostProcessor implements BeanPostProcessor,ApplicationContextAware {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ApplicationBeanPostProcessor.class);
	
	/** The application context. */
	private ApplicationContext applicationContext;
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean; // we could potentially return any object reference here...
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName)	throws BeansException {
		logger.debug("Bean '" + beanName + "' created : " + bean.toString());
		// Adding listeners for domain event dispatchers.
		if("forumEventDispatcher".equals(beanName)){
			ForumEventDispatcher forumEventDispatcher = (ForumEventDispatcher) bean;
			forumEventDispatcher.addListener((DomainObejctListener) applicationContext.getBean("forumRepository"));
		}
		if("threadEventDispatcher".equals(beanName)){
			ThreadEventDispatcher threadEventDispatcher = (ThreadEventDispatcher) bean;
			threadEventDispatcher.addListener((DomainObejctListener) applicationContext.getBean("threadRepository"));
		}
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}
}