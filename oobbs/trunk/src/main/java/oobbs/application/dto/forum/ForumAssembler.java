package oobbs.application.dto.forum;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import oobbs.domainmodel.forum.Thread;

import oobbs.application.dto.forum.ForumDTO.InnerThreadDTO;
import oobbs.domainmodel.forum.Forum;

@Component
public class ForumAssembler {
	
	public ForumDTO extractForumDataWithoutThreads(Forum forum){
		ForumDTO forumDTO = new ForumDTO();
		forumDTO.setId(forum.getId());
		forumDTO.setName(forum.getName());
		forumDTO.setDescription(forum.getDescription());
		return forumDTO;
	}
	
	public ForumDTO extractForumData(Forum forum, int startThreadIndex, int threadTotal){
		ForumDTO forumDTO = extractForumDataWithoutThreads(forum);
		List<InnerThreadDTO> threadDataList = new ArrayList<InnerThreadDTO>();
		List<Thread> threads = forum.getThreads().toList(startThreadIndex,threadTotal);
//		List<Thread> threads = forum.getThreads(startThreadIndex, threadTotal);
//		Set<Thread> threads = forum.getThreads();
		for (Thread thread : threads) {
			threadDataList.add(extractInnerThreadData(thread));
		}
		forumDTO.setThreads(threadDataList);
		return forumDTO;
	}
	
	private InnerThreadDTO extractInnerThreadData(Thread thread){
		InnerThreadDTO threadData = new InnerThreadDTO();
		threadData.setId(thread.getId());
		threadData.setTitle(thread.getTitle());
		threadData.setAuthor(thread.getAuthor().getUsername());
		threadData.setReplyCount(thread.getPosts().size());
		return threadData;
	}
}
