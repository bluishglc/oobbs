package oobbs.presentation.action.forum;

import java.util.List;

import oobbs.Constants;
import oobbs.application.dto.forum.PostDTO;
import oobbs.application.dto.forum.ThreadDTO;
import oobbs.application.service.forum.ForumService;
import oobbs.application.service.forum.ThreadService;
import oobbs.application.util.NavigationNode;
import oobbs.application.util.Pager;
import oobbs.presentation.action.BaseAction;
import oobbs.presentation.action.NavigationPathProvider;

import org.apache.log4j.Logger;

public class ThreadAction extends BaseAction implements NavigationPathProvider {

	private static final Logger logger = Logger.getLogger(ThreadAction.class);

	private static final long serialVersionUID = 8362915179353821721L;	
	
	private Long postId;
	
	private Long threadId;
	
	private Long forumId;
	
	private Integer page;
	
	private ThreadDTO threadDTO;
	
	private PostDTO subjectPostDTO;
	
	private PostDTO replyPostDTO;
	
	private ThreadService threadService;
	
	private ForumService forumService;


	/*----------------------------------     Action Methods     ----------------------------------*/
	
	public String getThreadById(){	
		Pager postListPager = createPostListPager();
		int startPostIndex = postListPager.getStartRecordIndex();
		int postTotal = postListPager.getPageSize();
		threadDTO = threadService.getThread(threadId,startPostIndex,postTotal);
		return SUCCESS;
	}
	
	/**
	 * NOTE: Paging is application logic! Is it better to make service create a pager which contains
	 * a record list of a page?
	 * @return
	 */
	private Pager createPostListPager(){
		Pager postListPager = new Pager(threadService.getThreadPostCount(threadId),Constants.POST_PAGING_SIZE);
		//If page == null, this means the thread page request come from other
		//pages not thread paging-page, at this moment, we should create pager instance for paging. 
		if(page == null){
			postListPager.navigateTo(1);
		}else{
			postListPager.navigateTo(page);
		}
		//Sets pager to request so as PagingTag can access it.
		getRequest().setAttribute(Constants.PAGER, postListPager);
		return postListPager;
	}
	
	public String navToCreateThread(){	
		getRequest().setAttribute(Constants.POST_MODE, Constants.CREATE_THREAD);
		return SUCCESS;
	}
	
	public String navToCreateReply(){
		getRequest().setAttribute(Constants.POST_MODE, Constants.CREATE_REPLY);
		return SUCCESS;
	}
	
	public String navToUpdatePost(){
		getRequest().setAttribute(Constants.POST_MODE, Constants.UPDATE_POST);
		return SUCCESS;
	}
	
	/**
	 * For every action, there will always be such step:
	 * Collect inputed data and incapsulate them into domain object.
	 * @return
	 */
	public String createThread(){
		Long authorId = getCurrentAuthenticationData().getUserId();
		subjectPostDTO.setAuthorId(authorId);
		threadService.createThread(forumId,subjectPostDTO);
		return SUCCESS;
	}
	
	public String createReply(){
		Long authorId = getCurrentAuthenticationData().getUserId();
		replyPostDTO.setAuthorId(authorId);
		threadService.createReply(threadId,replyPostDTO);
		return SUCCESS;
	}
	
	public String updatePost(){
		return SUCCESS;
	}

	public List<NavigationNode> getNavigationPath() {
		return threadService.getThreadNavigationPath(threadId);
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public Long getThreadId() {
		return threadId;
	}

	public ThreadDTO getThreadDTO() {
		return threadDTO;
	}

	public void setThreadDTO(ThreadDTO threadDTO) {
		this.threadDTO = threadDTO;
	}

	public PostDTO getSubjectPostDTO() {
		return subjectPostDTO;
	}

	public void setSubjectPostDTO(PostDTO subjectPostDTO) {
		this.subjectPostDTO = subjectPostDTO;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	public ThreadService getThreadService() {
		return threadService;
	}

	public void setThreadService(ThreadService threadService) {
		this.threadService = threadService;
	}
	
	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public PostDTO getReplyPostDTO() {
		return replyPostDTO;
	}

	public void setReplyPostDTO(PostDTO replyPostDTO) {
		this.replyPostDTO = replyPostDTO;
	}

}
