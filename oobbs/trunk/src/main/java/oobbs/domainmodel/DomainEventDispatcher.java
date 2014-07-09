package oobbs.domainmodel;

import java.util.HashMap;
import java.util.Map;

/**
 * The DomainEventDispatcher take charge of listener registration and dispatch domain event
 * to corresponding listener to handle. Usually, one dispatch per domain object.
 */
public class  DomainEventDispatcher {
	
	/** The listener map. all registered listeners are stored in this map. */
	protected Map<String,DomainEventListener> listeners = new HashMap<String, DomainEventListener>();
	
	/**
	 * Adds a listener.
	 *
	 * @param listener the listener
	 */
	public void addListener(DomainEventListener listener){
		listeners.put(listener.getName(), listener);
	}

	/**
	 * Removes all listeners.
	 */
	public void removeAllListeners(){
		listeners.clear();
	}
	
	public void dispatch(DomainEvent event){
		
	}
	
	public void dispatch(DomainEvent event,ResultCollector result){
		
	}
	
	public void inject(Object Data){
		
	}
	
}
