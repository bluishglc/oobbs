package oobbs.application.facade.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumDTO;
import oobbs.presentation.util.NavigationNode;

public interface ForumFacade {
    public Long getForumThreadCount(Long threadId);
    public ForumDTO getForum(Long id, int startThreadIndex, int threadTotal);
    //public List<NavigationNode> getThreadNavPath(Long forumId);
}
