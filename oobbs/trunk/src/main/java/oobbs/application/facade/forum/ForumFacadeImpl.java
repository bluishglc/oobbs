package oobbs.application.facade.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.service.forum.ForumService;
import oobbs.presentation.util.NavigationNode;

public class ForumFacadeImpl implements ForumFacade{

    private ForumService forumService;

    /*---------------------------------    Main Logic Methods    ---------------------------------*/

    public ForumDTO getForum(Long id, int startThreadIndex, int threadTotal) {
        ForumDTO forumDTO = forumService.getForum(id, startThreadIndex, threadTotal);
        return forumDTO;
    }

    public Long getForumThreadCount(Long threadId) {
        Long forumThreadCount = forumService.getForumThreadCount(threadId);
        return forumThreadCount;
    }

//	public List<NavigationNode> getThreadNavPath(Long forumId) {
//		List<NavigationNode> threadNavigationPath = forumService.getThreadPath(forumId);
//		return threadNavigationPath;
//	}

    /*----------------------------------    Accessor Methods    ----------------------------------*/

    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

}
