package oobbs.domainmodel;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class DomainObjectSupport.
 */
public class DomainObjectSupport { //Static or Singleton?
	
	//Design and apply observer for both of Performers & Listeners, such as PureMVC. 
	private Map<String, DomainTaskPerformer> taskPerformers = new HashMap<String, DomainTaskPerformer>();
	
	public void registerDomainTaskPerformer(String taskName, DomainTaskPerformer taskPerformer){
		taskPerformers.put(taskName, taskPerformer);
	}
	
	public void delegate(DomainTask task){
		DomainTaskPerformer taskPerformer = taskPerformers.get(task.getName());
		taskPerformer.perform(task);
	}
}
