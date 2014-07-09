package oobbs.domainmodel.forum;

import static org.junit.Assert.assertEquals;
import oobbs.domainmodel.DbBasedTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ForumGroupHibernateRepositoryTest extends DbBasedTest{
	
	@Autowired
	private ForumGroupRepository forumGroupRepository;

	@Test
	public void testLoadAll() {
		assertEquals(2, forumGroupRepository.loadAll().size());
	}

	@Test
	public void testGet() {
		ForumGroup fg = forumGroupRepository.get(1L);
		assertEquals(fg.getName(), "Group_1");
	}

	@Test
	public void testContians() {
		assertEquals(forumGroupRepository.contains(2L),true);
	}

	@Test
	public void testSave() {
		ForumGroup fg = new ForumGroup();
		fg.setName("Group_3");
		forumGroupRepository.persist(fg);
		assertEquals(forumGroupRepository.loadAll().size(), 3);
	}

	@Test
	public void testRemove() {
		//fail("Not yet implemented");
	}

	public void setForumGroupRepository(ForumGroupRepository forumGroupRepository) {
		this.forumGroupRepository = forumGroupRepository;
	}
	
}
