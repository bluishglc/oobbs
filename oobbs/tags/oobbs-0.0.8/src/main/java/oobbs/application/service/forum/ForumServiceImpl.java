package oobbs.application.service.forum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oobbs.application.dto.forum.ForumAssembler;
import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.dto.forum.ForumGroupAssembler;
import oobbs.application.dto.forum.ForumGroupDTO;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumGroup;
import oobbs.domainmodel.forum.ForumRepository;
import oobbs.presentation.util.NavigationNode;
import oobbs.presentation.util.NavigationPathBuilder;

@Service("forumService")
@Transactional
public class ForumServiceImpl implements ForumService{

    private ForumRepository forumRepository;

    private ForumAssembler forumAssembler;

    private ForumGroupAssembler forumGroupAssembler;

    /*---------------------------------    Main Logic Methods    ---------------------------------*/

    public Long getForumThreadCount(Long forumId) {
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
//
//	/**
//	 * Get thread's navigation path is typical application requirement!
//	 */
//	public List<NavigationNode> getThreadPath(Long forumId) {
//		Forum forum = forumRepository.get(forumId);
//		NavigationPathBuilder builder = new NavigationPathBuilder();
//		builder.buildIndexNode();
//		builder.buildForumGroupNode(forum.getGroup().getName(), forum.getGroup().getId());
//		builder.buildForumNode(forum.getName(), forum.getId());
//		List<NavigationNode> navigationPath = builder.getNavigationPath();
//		return navigationPath;
//	}

    @Override
    public ForumGroupDTO getForumGroup(Long forumId) {
        ForumGroup forumGroup = forumRepository.getForumGroup(forumId);
        ForumGroupDTO forumGroupDTO = forumGroupAssembler.extractForumGroupDataWithoutForums(forumGroup);
        return forumGroupDTO;
    }



    public ForumDTO getForum(Long forumId, int startThreadIndex, int threadCount) {
        Forum forum = forumRepository.load(forumId);
//		Forum forum = forumRepository.getForum(forumId,startThreadIndex,threadCount);
        ForumDTO forumDTO = forumAssembler.extractForumData(forum,startThreadIndex,threadCount);
        return forumDTO;
    }

    /*----------------------------------    Accessor Methods    ----------------------------------*/

    @Autowired
    public void setForumRepository(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }
    @Autowired
    public void setForumAssembler(ForumAssembler forumAssembler) {
        this.forumAssembler = forumAssembler;
    }

    @Autowired
    public void setForumGroupAssembler(ForumGroupAssembler forumGroupAssembler) {
        this.forumGroupAssembler = forumGroupAssembler;
    }


}
