package oobbs.domainmodel.forum;

import static org.junit.Assert.*;

import oobbs.domainmodel.DbBasedTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ThreadPostHibernateCollectionTest extends DbBasedTest{
	
	@Autowired
	private ThreadRepository threadRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test @Transactional
	public void testSize() {
		Thread thread = threadRepository.get(1L);
		//assertEquals(1L, thread.getThreadPosts().size());
	}

	//@Test
	public void testToList() {
		Thread thread = threadRepository.get(1L);
		//assertEquals(1L, thread.getThreadPosts().toList().size());
	}

	//@Test
	public void testToListIntInt() {
		Thread thread = threadRepository.get(1L);
		//assertEquals(1L, thread.getThreadPosts().toList(0,1).size());
		//assertEquals(1L, thread.getThreadPosts().toList(0,2).size());
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
//
//	@Test
//	public void testContains() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsEmpty() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemove() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFlush() {
//		fail("Not yet implemented");
//	}

}
