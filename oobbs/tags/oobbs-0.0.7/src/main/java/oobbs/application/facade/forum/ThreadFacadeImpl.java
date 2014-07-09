package oobbs.application.facade.forum;

import java.util.List;

import oobbs.application.dto.forum.ReplyCreationDTO;
import oobbs.application.dto.forum.ThreadCreationDTO;
import oobbs.application.dto.forum.ThreadDTO;
import oobbs.application.service.forum.ThreadService;
import oobbs.application.util.NavigationNode;

public class ThreadFacadeImpl implements ThreadFacade{
	
	private ThreadService threadService;


	public List<NavigationNode> getThreadNavigationPath(Long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getThreadPostCount(Long threadId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void createReply(Long threadId, ReplyCreationDTO replyCreationDTO) {
		// TODO Auto-generated method stub
		
	}

	public void createThread(Long forumId, ThreadCreationDTO threadCreationDTO) {
		// TODO Auto-generated method stub
		
	}

	public ThreadDTO getThread(Long threadId, int startPostIndex, int postTotal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
