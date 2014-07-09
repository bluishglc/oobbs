package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainEventListener;
import oobbs.domainmodel.DomainSupportRequestProcessor;
import oobbs.domainmodel.Repository;


public interface ForumRepository extends Repository<Forum, Long>,DomainEventListener,DomainSupportRequestProcessor {
	public Forum getForum(Long forumId, int firstThreadIndex, int threadCount);
	public ForumGroup getForumGroup(Long forumId);
	public Long getForumThreadCount(Long forumId);
	public Forum getForumWithModeratorsAndThreads( long forumId);
	//For test
	public Forum getForumWithThreadByJoin( Long forumId);
	public Forum getForumWithThreadByFetch(final Long forumId);
	public void getForumById(final Long id);
}
