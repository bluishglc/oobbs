package oobbs.domainmodel;

/**
 * The super class of all domain object listeners. it handles events from domain objects.
 * Each listener has to provide a name as key for registering itself to dispatcher.
 * 
 * @see DomainObejctEvent
 * @author laurence.geng
 */
public interface DomainObejctListener {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
}
