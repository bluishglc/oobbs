package oobbs.application.facade.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.util.NavigationNode;

public interface ForumFacade {
	public int getForumThreadCount(Long threadId);
	public ForumDTO getForum(Long id, int startThreadIndex, int threadTotal);
	public List<NavigationNode> getThreadNavigationPath(Long forumId);
}
