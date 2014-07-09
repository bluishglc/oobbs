package oobbs.domainmodel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * The Class DomainObjectSupport.
 */
@Component
public class DomainObjectSupport { //Static or Singleton?
	
	/** The listener map. all registered listeners are stored in this map. */
	protected Map<String,DomainEventListener> listeners = new HashMap<String, DomainEventListener>();
	
	//Design and apply observer for both of Performers & Listeners, such as PureMVC. 
	private Map<String, DomainSupportRequestProcessor> domainSupportRequestProcessors = new HashMap<String, DomainSupportRequestProcessor>();
		
	public void registerDomainEventListener(String eventName, DomainEventListener listener) {
		listeners.put(eventName,listener);
	}
	
	public void registerDomainSupportRequestProcessor(String requestName, DomainSupportRequestProcessor processor){
		domainSupportRequestProcessors.put(requestName, processor);
	}
	
	public void raise(DomainEvent event){
		DomainEventListener listener = listeners.get(event.getName());
		listener.handle(event);
	}
	
	public void dispatch(SupportRequest request, SupportResponse response){
		DomainSupportRequestProcessor supportRequestProcessor = domainSupportRequestProcessors.get(request.getName());
		supportRequestProcessor.process(request,response);
	}
}
