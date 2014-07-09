package oobbs.application.service.forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oobbs.application.dto.forum.ForumGroupAssembler;
import oobbs.application.dto.forum.ForumGroupDTO;
import oobbs.domainmodel.forum.ForumGroup;
import oobbs.domainmodel.forum.ForumGroupRepository;

@Transactional
@Service("forumGroupService")
public class ForumGroupServiceImpl implements ForumGroupService{
	
	private ForumGroupRepository forumGroupRepository;
	
	private ForumGroupAssembler forumGroupAssembler;
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/
	
	public List<ForumGroupDTO> getAllForumGroups() {
		List<ForumGroupDTO> forumGroupDTOs = new ArrayList<ForumGroupDTO>();
//		List<ForumGroup> forumGroups = forumGroupRepository.loadAll();
		List<ForumGroup> forumGroups = forumGroupRepository.getAllForumGroups();
		for (ForumGroup forumGroup : forumGroups) {
			forumGroupDTOs.add(forumGroupAssembler.extractForumGroupData(forumGroup));
		}
		return forumGroupDTOs;
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/

	@Autowired
	public void setForumGroupRepository(ForumGroupRepository forumGroupRepository) {
		this.forumGroupRepository = forumGroupRepository;
	}
	@Autowired
	public void setForumGroupAssembler(ForumGroupAssembler forumGroupAssembler) {
		this.forumGroupAssembler = forumGroupAssembler;
	}
	
}
