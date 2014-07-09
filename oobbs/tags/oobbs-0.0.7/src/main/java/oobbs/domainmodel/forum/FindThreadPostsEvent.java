package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainEvent;

public class FindThreadPostsEvent  extends DomainEvent{
	
	private int startIndex;
	
	private int count;
	
	public FindThreadPostsEvent(Thread source, int startIndex, int count) {
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
