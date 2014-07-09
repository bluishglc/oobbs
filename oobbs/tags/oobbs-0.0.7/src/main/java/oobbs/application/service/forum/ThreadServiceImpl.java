package oobbs.application.service.forum;

import java.util.List;

import oobbs.application.dto.forum.PostDTO;
import oobbs.application.dto.forum.ThreadAssembler;
import oobbs.application.dto.forum.ThreadDTO;
import oobbs.application.util.NavigationNode;
import oobbs.application.util.NavigationPathBuilder;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumRepository;
import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.PostRepository;
import oobbs.domainmodel.forum.Thread;
import oobbs.domainmodel.forum.ThreadRepository;
import oobbs.domainmodel.user.UserRepository;

import org.apache.log4j.Logger;

public class ThreadServiceImpl implements ThreadService{

	private static final Logger logger = Logger.getLogger(ThreadServiceImpl.class);

	private ThreadRepository threadRepository;	
	
	private PostRepository postRepository;
	
	private UserRepository userRepository;
	
	private ForumRepository forumRepository;
	
	private ThreadAssembler threadAssembler;
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/

	public int getThreadPostCount(Long threadId) {
		return 1;
	}
	
	public ThreadDTO getThread(Long threadId, int startPostIndex, int postTotal){
		Thread thread = threadRepository.load(threadId);
		ThreadDTO threadDTO = threadAssembler.extractThreadData(thread, startPostIndex, postTotal);
		return threadDTO;
	}

	public List<NavigationNode> getThreadNavigationPath(Long threadId) {
		Thread thread = threadRepository.get(threadId);
		NavigationPathBuilder builder = new NavigationPathBuilder();
		builder.buildIndexNode();
		builder.buildForumGroupNode(thread.getForum().getGroup().getName(), thread.getForum().getGroup().getId());
		builder.buildForumNode(thread.getForum().getName(), thread.getForum().getId());
		builder.buildThreadNode(thread.getName(), thread.getId());
		return builder.getNavigationPath();
	}
	
	/**
	 * Creates a thread and persist. 
	 * NOTE:Thread and Subject Post are cyclic dependencies, so we persist
	 * Thread without subject post first, then set subject post to it and update.
	 */
	public void createThread(Long forumId,PostDTO subjectPostDTO) {
		//Persist thread without subject post first.
		Forum forum = forumRepository.load(forumId);
		Thread thread = forum.createThread();
		threadRepository.persist(thread);
		
		//Set subject post then update thread.
		Post subject = thread.createPost();
		threadAssembler.populatePost(subject,subjectPostDTO);
		thread.setSubject(subject);
		threadRepository.merge(thread);
	}

	public void createReply(Long threadId, PostDTO replyPostDTO){
		Thread thread = threadRepository.load(threadId);
		Post reply = thread.createPost();
		threadAssembler.populatePost(reply, replyPostDTO);
		postRepository.persist(reply);
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public void setThreadRepository(ThreadRepository threadRepository) {
		this.threadRepository = threadRepository;
	}

	public void setForumRepository(ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}

	public void setThreadAssembler(ThreadAssembler threadAssembler) {
		this.threadAssembler = threadAssembler;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

}
