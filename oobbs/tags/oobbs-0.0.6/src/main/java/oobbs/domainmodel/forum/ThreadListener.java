package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainObejctListener;
import oobbs.domainmodel.ResultCollector;

public interface ThreadListener extends DomainObejctListener{
	public void handleGetThreadPostEvent(FindThreadPostsEvent event, ResultCollector result);
}
