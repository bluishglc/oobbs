package oobbs.application.facade.forum;

import java.util.List;

import oobbs.application.dto.forum.ReplyCreationDTO;
import oobbs.application.dto.forum.ThreadCreationDTO;
import oobbs.application.dto.forum.ThreadDTO;
import oobbs.application.util.NavigationNode;

public interface ThreadFacade {
	
	public int getThreadPostCount(Long threadId);
	
	public List<NavigationNode> getThreadNavigationPath(Long threadId);
	
	public ThreadDTO getThread(Long threadId,int startPostIndex, int postTotal);
	
	/**
	 * Create new thread and save it.
	 * @param newThread The thread data user input.
	 */
	public void createThread(Long forumId,ThreadCreationDTO threadCreationDTO);
	
	public void createReply(Long threadId,ReplyCreationDTO replyCreationDTO);
}
