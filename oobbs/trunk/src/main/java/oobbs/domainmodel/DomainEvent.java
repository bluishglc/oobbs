package oobbs.domainmodel;

/**
 * The supper class of all domain events. all events should provide event source, the domain
 * object which fired this event.
 */
public class DomainEvent {
	
	protected String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSource(Object source) {
		this.source = source;
	}
	
	
}
