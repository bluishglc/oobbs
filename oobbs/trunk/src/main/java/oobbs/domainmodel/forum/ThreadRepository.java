package oobbs.domainmodel.forum;

import oobbs.domainmodel.Repository;

public interface ThreadRepository extends Repository<Thread, Long>{
	
//	public Thread getThreadWithSubject();
	public Forum getForum(Long threadId);
	
	public int getThreadPostCount();
	
	public void saveThread(Thread thread);
}
