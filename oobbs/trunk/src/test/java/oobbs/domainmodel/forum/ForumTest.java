package oobbs.domainmodel.forum;

import org.apache.log4j.Logger;

import static org.junit.Assert.*;

import oobbs.domainmodel.DbBasedTest;
import oobbs.domainmodel.user.UserRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ForumTest extends DbBasedTest{

	private static final Logger logger = Logger.getLogger(ForumTest.class);
	
	@Autowired
	private ForumRepository forumRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ThreadRepository threadRepository;
	
	//@Test @Transactional
	public void testJoinAndFetch(){
		Forum forum = forumRepository.getForumWithThreadByJoin(1L);
		System.out.println("***************************************");
		forum.getThreads();
	}
	
	//@Test @Transactional
	public void testGetForumById(){
		//The hql is: from Thread
		threadRepository.loadAll();
		logger.debug("ALL");
		threadRepository.load(1L);
		logger.debug("1 L");
		threadRepository.load(2L);
		logger.debug("2 L");
//		threadRepository.get(2L);
//		logger.debug("thread 2");
		
		//forumRepository.getForumById(1l);

	}
	
//	@Test @Transactional
//	public void testGetThreadCount(){
//		//Forum f = forumRepository.get(1L);
//		//assertEquals(2L,f.getThreadCount());
//	}
	@Test @Transactional
	public void testgetForumWithModeratorsAndThreads(){
		Forum forum = forumRepository.get(1L);
		System.out.println("****************************************************");
		System.out.println(forum.getModerators().size());
		System.out.println(forum.getThreads().size());
	}
	
	//@Test @Transactional
	public void testSaveThread(){

		
//		Forum forum = forumRepository.get(1L);
//		forumRepository.loadAll().get(0).getThreads().get(0);
//		forumRepository.loadAll().get(0).getModerators().size();
//		System.out.println(f.getThreads().size());
//		f.getThreads().get(0);
//		f.addThread(new Thread());
		//f.removeThread(threadRepository.load(1L));
//		Post subject = new Post();
//		subject.setAuthor(userRepository.get(-1L));
//		subject.setTitle("asdfasd");
//		subject.setMessageBody("SDFSDF");
//		subject.setSubject(true);
//		Thread thread = new Thread();
//		thread.setSubject(subject);
//		thread.setForum(f);
//		f.addThread(thread);
//		forumRepository.update(f);
//		System.out.println(threadRepository.loadAll().size());
		
		//assertEquals(2L,f.getThreadCount());
	}
}
