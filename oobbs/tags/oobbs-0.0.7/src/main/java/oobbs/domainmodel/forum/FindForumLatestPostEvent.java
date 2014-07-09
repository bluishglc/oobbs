package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainEvent;

public class FindForumLatestPostEvent extends DomainEvent{

	public FindForumLatestPostEvent(Object source) {
		super(source);
	}

}
