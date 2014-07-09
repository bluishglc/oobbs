package oobbs.domainmodel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * The Class DomainObjectSupport.
 */
@Component
public class DomainObjectSupport { //Static or Singleton?
	
	//Design and apply observer for both of Performers & Listeners, such as PureMVC. 
	private Map<String, DomainActionPerformer> actionPerformers = new HashMap<String, DomainActionPerformer>();
	
	public void registerDomainActionPerformer(String actionName, DomainActionPerformer actionPerformer){
		actionPerformers.put(actionName, actionPerformer);
	}
	
	public void delegate(DomainAction action){
		DomainActionPerformer actionPerformer = actionPerformers.get(action.getName());
		actionPerformer.perform(action);
	}
}
