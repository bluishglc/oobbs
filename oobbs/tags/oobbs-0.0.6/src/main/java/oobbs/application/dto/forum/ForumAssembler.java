package oobbs.application.dto.forum;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import oobbs.domainmodel.forum.Thread;

import oobbs.application.dto.forum.ForumDTO.InnerThreadDTO;
import oobbs.domainmodel.forum.Forum;

public class ForumAssembler {
	
	public ForumDTO extractForumData(Forum forum, int startThreadIndex, int threadTotal){
		ForumDTO forumData = new ForumDTO();
		forumData.setId(forum.getId());
		forumData.setName(forum.getName());
		forumData.setDescription(forum.getDescription());
		List<InnerThreadDTO> threadDataList = new ArrayList<InnerThreadDTO>();
		List<Thread> threads = forum.getThreads().toList(startThreadIndex,threadTotal);
//		List<Thread> threads = forum.getThreads(startThreadIndex, threadTotal);
//		Set<Thread> threads = forum.getThreads();
		for (Thread thread : threads) {
			threadDataList.add(extractInnerThreadData(thread));
		}
		forumData.setThreads(threadDataList);
		return forumData;
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
