package oobbs.presentation.action.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumGroupDTO;
import oobbs.application.service.forum.ForumGroupService;
import oobbs.domainmodel.forum.ForumGroup;
import oobbs.presentation.action.BaseAction;

import org.apache.log4j.Logger;

public class ForumGroupAction extends BaseAction{

	private static final Logger logger = Logger.getLogger(ForumGroupAction.class);

	private static final long serialVersionUID = -8559799398947575804L;
	
	private ForumGroupService forumGroupService;
	
	private Long id;

	private ForumGroup forumGroup;
	
	private List<ForumGroupDTO> forumGroups;
	
	/*----------------------------------     Action Methods     ----------------------------------*/
	
	public String getAllForumGroups(){
		forumGroups = forumGroupService.getAllForumGroups();
		return SUCCESS;
	}
	
	public String delete(){
		return SUCCESS;
	}
	
	public String edit(){
		return SUCCESS;
	}
	
	public String save(){
		return SUCCESS;
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ForumGroup getForumGroup() {
		return forumGroup;
	}

	public void setForumGroup(ForumGroup forumGroup) {
		this.forumGroup = forumGroup;
	}

	public List<ForumGroupDTO> getForumGroups() {
		return forumGroups;
	}

	public void setForumGroups(List<ForumGroupDTO> forumGroups) {
		this.forumGroups = forumGroups;
	}

	public ForumGroupService getForumGroupService() {
		return forumGroupService;
	}

	public void setForumGroupService(ForumGroupService forumGroupService) {
		this.forumGroupService = forumGroupService;
	}

}
