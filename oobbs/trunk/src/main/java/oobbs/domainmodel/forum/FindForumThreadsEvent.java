package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainEvent;

/**
 * The Event that forum request to get its threads.
 * @author laurence.geng
 */
public class FindForumThreadsEvent extends DomainEvent{

	/** The start index of request thread. */
	private int startIndex;
	
	/** The count of request thread. */
	private int count;
	
	public FindForumThreadsEvent(Forum source, int startIndex, int count) {
		super(source);
		this.startIndex = startIndex;
		this.count = count;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getCount() {
		return count;
	}
	
}
