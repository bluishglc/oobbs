package oobbs.domainmodel.forum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import oobbs.domainmodel.DbBasedTest;
import oobbs.infrastructure.persistence.forum.ForumThreadHibernateCollection;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ForumThreadHibernateCollectionTest extends DbBasedTest{
	
	@Autowired
	private ForumThreadHibernateCollection forumThreads; 
	
	@Autowired
	private ForumRepository forumRepository;
	
	@Autowired
	private ThreadRepository threadRepository;

	@Before
	public void setUp() throws Exception {
		Forum forum = forumRepository.get(1L);
		//forumThreads.setForum(forum);
	}

	@Test @Transactional
	public void testSize() {
		assertEquals(forumThreads.size(), 2L);
	}

	@Test @Transactional
	public void testToList() {
		List<Thread> threads = forumThreads.toList(1,100);
		assertEquals(threads.size(), 2);
	}

	@Test @Transactional
	public void testToListIntInt() {
		List<Thread> threads1 = forumThreads.toList(0,1);
		assertEquals(threads1.size(), 1);
		List<Thread> threads2 = forumThreads.toList(1,2);
		assertEquals(threads2.size(), 1);
	}

//	@Test
//	public void testAdd() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddAll() {
//		fail("Not yet implemented");
//	}

	@Test @Transactional
	public void testContains() {
		Thread thread1 = threadRepository.get(1L);
		Thread thread2 = threadRepository.get(2L);
		assertTrue(forumThreads.contains(thread1));
		assertTrue(forumThreads.contains(thread2));
	}

//	@Test
//	public void testIsEmpty() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testRemove() {
//		Thread thread1 = threadRepository.get(1L);
//		forumThreads.remove(thread1);
//		forumThreads.flush();
//		assertEquals(forumThreads.size(), 1);
//		Thread thread2 = threadRepository.get(2L);
//		forumThreads.remove(thread2);
//		forumThreads.flush();
//		assertEquals(forumThreads.size(), 0);
	}

	
}
