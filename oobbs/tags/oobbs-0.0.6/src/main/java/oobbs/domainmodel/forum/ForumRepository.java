package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainTaskPerformer;
import oobbs.domainmodel.Repository;


public interface ForumRepository extends Repository<Forum, Long>,ForumListener,DomainTaskPerformer {
	public Forum getForum(Long forumId, int firstThreadIndex, int threadCount);
	public int getForumThreadCount(Long forumId);
	public Forum getForumWithModeratorsAndThreads( long forumId);
	//For test
	public Forum getForumWithThreadByJoin( Long forumId);
	public Forum getForumWithThreadByFetch(final Long forumId);
	public void getForumById(final Long id);
}
