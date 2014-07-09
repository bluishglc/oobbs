package oobbs.application.service.forum;
import java.util.List;

import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.dto.forum.PostDTO;
import oobbs.application.dto.forum.ReplyCreationDTO;
import oobbs.application.dto.forum.ThreadCreationDTO;
import oobbs.application.dto.forum.ThreadDTO;
import oobbs.presentation.util.NavigationNode;

public interface ThreadService {

    public int getThreadPostCount(Long threadId);

    public List<NavigationNode> getThreadNavPath(Long threadId);

    public List<NavigationNode> getThreadCreationNavPath(Long forumId);

    public ThreadDTO getThread(Long threadId,int startPostIndex, int postTotal);

    public ForumDTO getForum(Long threadId);

    /**
     * Create new thread and save it.
     * @param newThread The thread data user input.
     */
    public void createThread(Long forumId,PostDTO subjectPostDTO);

    public void createReply(Long threadId, PostDTO replyPostDTO);

}
