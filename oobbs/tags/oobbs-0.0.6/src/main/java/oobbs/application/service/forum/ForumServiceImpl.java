package oobbs.application.service.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumAssembler;
import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.util.NavigationNode;
import oobbs.application.util.NavigationPathBuilder;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumRepository;

public class ForumServiceImpl implements ForumService{

	private ForumRepository forumRepository;
	
	private ForumAssembler forumAssembler;
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/

	public int getForumThreadCount(Long forumId) {
//		Forum forum = forumRepository.get(threadId);
//		int forumThreadCount = forum.getThreads().size();
//		return forumThreadCount;
		return forumRepository.getForumThreadCount(forumId);
	}

	@Override
	public List<NavigationNode> getForumPath(Long forumId) {
		Forum forum = forumRepository.get(forumId);
		NavigationPathBuilder builder = new NavigationPathBuilder();
		builder.buildIndexNode();
		builder.buildForumGroupNode(forum.getGroup().getName(), forum.getGroup().getId());
		builder.buildForumNode(forum.getName(), forum.getId());
		List<NavigationNode> navigationPath = builder.getNavigationPath();
		return navigationPath;
	}

	/**
	 * Get thread's navigation path is typical application requirement!
	 */
	public List<NavigationNode> getThreadPath(Long forumId) {
		Forum forum = forumRepository.get(forumId);
		NavigationPathBuilder builder = new NavigationPathBuilder();
		builder.buildIndexNode();
		builder.buildForumGroupNode(forum.getGroup().getName(), forum.getGroup().getId());
		builder.buildForumNode(forum.getName(), forum.getId());
		List<NavigationNode> navigationPath = builder.getNavigationPath();
		return navigationPath;
	}

	public ForumDTO getForum(Long forumId, int startThreadIndex, int threadCount) {
		Forum forum = forumRepository.load(forumId);
//		Forum forum = forumRepository.getForum(forumId,startThreadIndex,threadCount);
		ForumDTO forumDTO = forumAssembler.extractForumData(forum,startThreadIndex,threadCount);
		return forumDTO;
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public void setForumRepository(ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}
	public void setForumAssembler(ForumAssembler forumAssembler) {
		this.forumAssembler = forumAssembler;
	}

}
