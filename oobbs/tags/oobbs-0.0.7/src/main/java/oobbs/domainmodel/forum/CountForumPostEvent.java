package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainEvent;

/**
 * The Event that forum request to get its posts' count.
 */
public class CountForumPostEvent extends DomainEvent{

	public CountForumPostEvent(Object source) {
		super(source);
	}

}
