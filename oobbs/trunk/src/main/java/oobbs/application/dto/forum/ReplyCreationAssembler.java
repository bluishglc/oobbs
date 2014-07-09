package oobbs.application.dto.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.ThreadRepository;
@Component
public class ReplyCreationAssembler {
	
	private ThreadRepository threadRepository;
	
	public void populateRelpy( Post reply, ReplyCreationDTO replyData){
		reply.setTitle(replyData.getTitle());
		reply.setMessageBody(replyData.getMessageBody());
		//reply.setAuthor(replyData.getAuthor());
		//reply.setThread(threadRepository.load(replyData.getThreadId()));		
	}

	@Autowired
	public void setThreadRepository(ThreadRepository threadRepository) {
		this.threadRepository = threadRepository;
	}
}
