package oobbs.application.dto.forum;

import java.util.ArrayList;
import java.util.List;

import oobbs.application.dto.forum.ForumGroupDTO.InnerForumDTO;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumGroup;
import oobbs.domainmodel.forum.Post;

public class ForumGroupAssembler {
	
	public ForumGroupDTO extractForumGroupData(ForumGroup forumGroup){
		ForumGroupDTO forumGroupDTO = new ForumGroupDTO();
		forumGroupDTO.setId(forumGroup.getId());
		forumGroupDTO.setName(forumGroup.getName());
		forumGroupDTO.setDescription(forumGroup.getDescription());

		List<InnerForumDTO> forumDTOs = new ArrayList<InnerForumDTO>();
		List<Forum> fourms = forumGroup.getForums();
		if (fourms != null && !fourms.isEmpty()) {
			for (Forum forum : fourms) {
				forumDTOs.add(extractInnerForumData(forum));
			}
		}
		forumGroupDTO.setForums(forumDTOs);
		return forumGroupDTO;
	}
	
	private InnerForumDTO extractInnerForumData(Forum forum){
		InnerForumDTO forumData = new InnerForumDTO();
		forumData.setId(forum.getId());
		forumData.setName(forum.getName());
		forumData.setDescription(forum.getDescription());
		forumData.setThreadCount(forum.getThreadCount());
		forumData.setPostCount(3L);
		Post lastPost = forum.getLatestPost();
		if(lastPost!=null){
			forumData.setLatestPostAuthor(forum.getLatestPost().getAuthor().getUsername());
			forumData.setLatestPostTitle(forum.getLatestPost().getTitle());
			forumData.setLatestPostCreationTime(forum.getLatestPost().getCreationTime().toString());
		}
		return forumData;
	}
	
}
