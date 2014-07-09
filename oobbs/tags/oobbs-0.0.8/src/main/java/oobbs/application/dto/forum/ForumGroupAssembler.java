package oobbs.application.dto.forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import oobbs.application.dto.forum.ForumGroupDTO.InnerForumDTO;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumGroup;
import oobbs.domainmodel.forum.Post;
@Component
public class ForumGroupAssembler {
	
	public ForumGroupDTO extractForumGroupDataWithoutForums(ForumGroup forumGroup){
		ForumGroupDTO forumGroupDTO = new ForumGroupDTO();
		forumGroupDTO.setId(forumGroup.getId());
		forumGroupDTO.setName(forumGroup.getName());
		forumGroupDTO.setDescription(forumGroup.getDescription());
		return forumGroupDTO;
	}
	
	public ForumGroupDTO extractForumGroupData(ForumGroup forumGroup){
		ForumGroupDTO forumGroupDTO = extractForumGroupDataWithoutForums(forumGroup);
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
		forumData.setThreadCount(forum.getThreads().size());
		forumData.setPostCount(forum.getPostCount());
		Post lastPost = forum.getLatestPost();
		if(lastPost!=null){
			forumData.setLatestPostAuthor(forum.getLatestPost().getAuthor().getUsername());
			forumData.setLatestPostTitle(forum.getLatestPost().getTitle());
			forumData.setLatestPostCreationTime(forum.getLatestPost().getCreationTime().toString());
		}
		return forumData;
	}
	
}
