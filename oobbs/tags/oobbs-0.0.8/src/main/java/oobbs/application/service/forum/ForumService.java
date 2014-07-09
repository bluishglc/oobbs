package oobbs.application.service.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.dto.forum.ForumGroupDTO;
import oobbs.presentation.util.NavigationNode;

public interface ForumService {

    public List<NavigationNode> getForumPath(Long forumId);
//	
//	public List<NavigationNode> getThreadPath(Long forumId);

//	public ForumDTO getForum(Long forumId);//How to name this method? forum<-forumgroup

    public ForumGroupDTO getForumGroup(Long forumId);

    public Long getForumThreadCount(Long threadId);

    public ForumDTO getForum(Long id, int startThreadIndex, int threadTotal);

}
