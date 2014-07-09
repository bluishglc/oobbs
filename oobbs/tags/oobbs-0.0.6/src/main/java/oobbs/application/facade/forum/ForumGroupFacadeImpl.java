package oobbs.application.facade.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumGroupDTO;
import oobbs.application.service.forum.ForumGroupService;

public class ForumGroupFacadeImpl implements ForumGroupFacade{
	
	private ForumGroupService forumGroupService;
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/

	public List<ForumGroupDTO> getAllForumGroups() {
		List<ForumGroupDTO> forumGroupDTOs = forumGroupService.getAllForumGroups();
		return forumGroupDTOs;
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public void setForumGroupService(ForumGroupService forumGroupService) {
		this.forumGroupService = forumGroupService;
	}

}
