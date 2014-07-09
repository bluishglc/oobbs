package oobbs.domainmodel;

import org.apache.log4j.Logger;


public abstract class DomainServiceLocator {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DomainServiceLocator.class);
	
	private static DomainServiceLocator instance;
	
	public static void injectInstance(DomainServiceLocator locator){
		if(instance==null){
			instance = locator;
			logger.debug("An instance is injected into DomainServiceLocator.");
		}else{
			logger.debug("Trying to injecte instance to The DomainServiceLocator, however, There is already instance injected!");
			throw new RuntimeException("The DomainServiceLocator has injected an instance!");
		}
	}
	
	public static void clearInstance(){
		instance = null;
	}
	
	public static DomainServiceLocator getInstance(){
		return instance;
	}
	
	public abstract Object locate(String serviceName);
}
