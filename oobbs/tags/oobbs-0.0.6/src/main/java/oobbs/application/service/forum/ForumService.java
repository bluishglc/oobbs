package oobbs.application.service.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.util.NavigationNode;

public interface ForumService {
	
	public List<NavigationNode> getForumPath(Long forumId);
	
	public List<NavigationNode> getThreadPath(Long forumId);
	
	public int getForumThreadCount(Long threadId);
	
	public ForumDTO getForum(Long id, int startThreadIndex, int threadTotal);
	
}
