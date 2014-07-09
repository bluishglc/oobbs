package oobbs.infrastructure.persistence.forum;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import oobbs.domainmodel.forum.ForumGroup;
import oobbs.domainmodel.forum.ForumGroupRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.cache.StandardQueryCache;
import org.hibernate.cache.StandardQueryCacheFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ForumGroupHibernateRepository extends AbstractHibernateRepository<ForumGroup, Long> implements ForumGroupRepository{

	private static final Logger logger = Logger.getLogger(ForumGroupHibernateRepository.class);

	public ForumGroupHibernateRepository() {}

	@SuppressWarnings("unchecked")
	public List<ForumGroup> getAllForumGroups() {
		return (List<ForumGroup>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session){
						logger.debug("Starts to load all forum groups with their forums.");
						String getAllForumGroupsWithForumsHql = "select distinct forumGroup from ForumGroup as forumGroup left join fetch forumGroup.forums";
						List<ForumGroup> forumGroups = session.createQuery(getAllForumGroupsWithForumsHql).setCacheable(true).list();
						logger.debug("Ends to load all forum groups with their forums.");
//						Map cacheEntries = session.getSessionFactory().getStatistics()
//						.getQueryStatistics("org.hibernate.cache.StandardQueryCache")
//						.getEntries();
//						logger.debug(session.getSessionFactory().getStatistics()
//								.getQueryStatistics("org.hibernate.cache.StandardQueryCache"));
//						logger.debug(session.getSessionFactory().getStatistics());
						String[] names = CacheManager.getInstance().getCacheNames();
						for (int i = 0; i < names.length; i++) {
							logger.debug(names[i]);
						}
						
						return forumGroups;
					}
				}
		);		
	}
	
}
