package oobbs.domainmodel.forum;

import org.springframework.stereotype.Component;

import oobbs.Constants;
import oobbs.domainmodel.DomainEventDispatcher;
import oobbs.domainmodel.ResultCollector;

@Component
public class ThreadEventDispatcher extends DomainEventDispatcher{
	
	public void dispatch(FindThreadPostsEvent event, ResultCollector result){
		ThreadListener threadListener = (ThreadListener) listeners.get(Constants.THREAD_REPO_AS_THREAD_LISTENER);
		threadListener.handleGetThreadPostEvent(event, result);
	}
}
