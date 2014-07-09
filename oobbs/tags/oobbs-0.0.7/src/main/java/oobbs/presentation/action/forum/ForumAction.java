package oobbs.presentation.action.forum;

import java.util.List;

import oobbs.Constants;
import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.service.forum.ForumService;
import oobbs.application.util.NavigationNode;
import oobbs.application.util.Pager;
import oobbs.domainmodel.forum.Forum;
import oobbs.presentation.action.BaseAction;
import oobbs.presentation.action.NavigationPathProvider;

import org.apache.log4j.Logger;

public class ForumAction extends BaseAction implements NavigationPathProvider{

	private static final Logger logger = Logger.getLogger(ForumAction.class);

	private static final long serialVersionUID = -8715835037638137396L;
	
	private ForumService forumService;

	private List<Forum> forums;
	
	private ForumDTO forum;
	
	private Long forumId;
	
	private Integer page;
	
	/*----------------------------------     Action Methods     ----------------------------------*/
	
	public String getForumById(){
		try {
			Pager threadListPager = createThreadListPager();
			int startPostIndex = threadListPager.getStartRecordIndex();
			int postTotal = threadListPager.getPageSize();
			forum = forumService.getForum(forumId,0,5);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Abstract to an common factory method?
	 * @return
	 */
	private Pager createThreadListPager(){
		Pager threadListPager = new Pager(forumService.getForumThreadCount(forumId),Constants.THREAD_PAGING_SIZE);
		//If page == null, this means the thread page request come from other
		//pages not thread paging-page, at this moment, we should create pager instance for paging. 
		if(page == null){
			threadListPager.navigateTo(1);
		}else{
			threadListPager.navigateTo(page);
		}
		//Sets pager to request so as PagingTag can access it.
		getRequest().setAttribute(Constants.PAGER, threadListPager);
		return threadListPager;
	}
	
	public List<NavigationNode> getNavigationPath() {
		return forumService.getForumPath(forumId);
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public List<Forum> getForums() {
		return forums;
	}

	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public ForumDTO getForum() {
		return forum;
	}

	public void setForum(ForumDTO forum) {
		this.forum = forum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

}
