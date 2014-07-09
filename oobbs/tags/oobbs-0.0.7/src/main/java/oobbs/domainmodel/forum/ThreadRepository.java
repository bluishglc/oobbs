package oobbs.domainmodel.forum;

import oobbs.domainmodel.Repository;

public interface ThreadRepository extends Repository<Thread, Long>, ThreadListener{
	
//	public Thread getThreadWithSubject();
	
	public int getThreadPostCount();
	
	public void saveThread(Thread thread);
}
