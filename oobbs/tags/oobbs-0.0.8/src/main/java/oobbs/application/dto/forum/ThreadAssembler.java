package oobbs.application.dto.forum;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oobbs.application.dto.forum.ThreadDTO.InnerPostDTO;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumRepository;
import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.Thread;
import oobbs.domainmodel.forum.ThreadRepository;
import oobbs.domainmodel.user.UserRepository;
@Component
public class ThreadAssembler{
	
	private ForumRepository forumRepository;
	
	private ThreadRepository threadRepository;
	
	private UserRepository userRepository;
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/
	
	public ThreadDTO extractThreadData(Thread thread, int startPostIndex, int postTotal){
		ThreadDTO threadDTO = new ThreadDTO();
		threadDTO.setId(thread.getId());
		threadDTO.setTitle(thread.getTitle());
		List<InnerPostDTO> postDTOs = new ArrayList<InnerPostDTO>();
//		Post subjectPost = thread.getSubject();
//		postDTOs.add(extractInnerPostData(subjectPost));
//		Set<Post> posts = thread.getReplies();
//		List<Post> posts = thread.getPosts(startPostIndex,postTotal);
		List<Post> posts = thread.getPosts().toList(startPostIndex,postTotal);
		for (Post post : posts) {
			postDTOs.add(extractInnerPostData(post));
		}
		threadDTO.setPosts(postDTOs);
		return threadDTO;
	}
	
	private InnerPostDTO extractInnerPostData(Post post){
		InnerPostDTO postData = new InnerPostDTO();
		postData.setId(post.getId());
		postData.setTitle(post.getTitle());
		postData.setMessageBody(post.getMessageBody());
		postData.setAuthor(post.getAuthor().getUsername());
		postData.setCreationTime(post.getCreationTime());
		return postData;
	}
	
	public void populatePost(Post post,PostDTO postDTO){
		post.setTitle(postDTO.getTitle());
		post.setMessageBody(postDTO.getMessageBody());
		post.setAuthor(userRepository.load(postDTO.getAuthorId()));
	}
	
//	public void populateRelpy( Post reply, PostDTO replyDTO){
//		reply.setTitle(replyDTO.getTitle());
//		reply.setMessageBody(replyDTO.getMessageBody());
//		reply.setAuthor(userRepository.load(replyDTO.getAuthorId()));	
//	}
	
	public void populateThread(Thread thread, PostDTO subjectPostDTO) {
		Post subject = thread.getSubject();
		populatePost(subject,subjectPostDTO);
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/
	@Autowired
	public void setForumRepository(ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}
	@Autowired
	public void setThreadRepository(ThreadRepository threadRepository) {
		this.threadRepository = threadRepository;
	}
	@Autowired
	public UserRepository getUserRepository() {
		return userRepository;
	}
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
