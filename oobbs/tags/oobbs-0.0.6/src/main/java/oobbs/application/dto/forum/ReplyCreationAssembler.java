package oobbs.application.dto.forum;

import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.ThreadRepository;

public class ReplyCreationAssembler {
	
	private ThreadRepository threadRepository;
	
	public void populateRelpy( Post reply, ReplyCreationDTO replyData){
		reply.setTitle(replyData.getTitle());
		reply.setMessageBody(replyData.getMessageBody());
		//reply.setAuthor(replyData.getAuthor());
		//reply.setThread(threadRepository.load(replyData.getThreadId()));		
	}

	public void setThreadRepository(ThreadRepository threadRepository) {
		this.threadRepository = threadRepository;
	}
}
