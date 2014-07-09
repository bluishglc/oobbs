package oobbs.domainmodel;

/**
 * The supper class of all domain events. all events should provide event source, the domain
 * object which fired this event.
 */
public class DomainEvent {
	
	/** The event source, the domain object which fired this event. */
	protected Object source;	

	public DomainEvent(Object source) {
		super();
		this.source = source;
	}

	/**
	 * Gets the event source.
	 *
	 * @return the event source
	 */
	public Object getSource() {
		return source;
	}
	
}
