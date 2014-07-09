package oobbs.application.dto.forum;

import oobbs.application.dto.user.UserAuthenticationDTO;
import oobbs.domainmodel.forum.ForumRepository;
import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.Thread;
import oobbs.domainmodel.forum.ThreadRepository;
import oobbs.domainmodel.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class PostAssembler {
	
	private ForumRepository forumRepository;
	
	private ThreadRepository threadRepository;
	
	private UserRepository userRepository;
	
	public PostDTO extractPostData(Post post){
		PostDTO postData = new PostDTO();
		//postData.setId(post.getId());
		postData.setTitle(post.getTitle());
		postData.setMessageBody(post.getMessageBody());
//		postData.setAuthorId(post.getAuthor().getId());
		return postData;
	}
	
//	public void populateThread(Thread thread, Long forumId, PostDTO postData){
//		thread.setForum(forumRepository.load(forumId));
//		Post subjectPost = thread.getSubject();
//		populatePost(subjectPost,postData);
//	}
	
	public void populateReply(Post reply, Long threadId, PostDTO postData){
		//reply.setThread(threadRepository.load(threadId));
		populatePost(reply,postData);
	}
	
	public void populatePost(Post post,PostDTO postData){
		post.setTitle(postData.getTitle());
		post.setMessageBody(postData.getMessageBody());
		UserAuthenticationDTO userAuthenticationData = 
			(UserAuthenticationDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setAuthor(userRepository.load(userAuthenticationData.getUserId()));
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setForumRepository(ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}

	@Autowired
	public void setThreadRepository(ThreadRepository threadRepository) {
		this.threadRepository = threadRepository;
	}
}
