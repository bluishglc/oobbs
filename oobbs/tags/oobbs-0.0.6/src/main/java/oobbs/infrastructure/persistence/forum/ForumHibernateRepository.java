package oobbs.infrastructure.persistence.forum;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oobbs.Constants;
import oobbs.domainmodel.DomainTask;
import oobbs.domainmodel.ResultCollector;
import oobbs.domainmodel.forum.FindForumLatestPostEvent;
import oobbs.domainmodel.forum.CountForumPostEvent;
import oobbs.domainmodel.forum.CountForumThreadEvent;
import oobbs.domainmodel.forum.FindForumThreadsEvent;
import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.Thread;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.ForumRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ForumHibernateRepository extends AbstractHibernateRepository<Forum, Long> implements ForumRepository{

	private static final Logger logger = Logger.getLogger(ForumHibernateRepository.class);
	
	public ForumHibernateRepository() {}

	@Override
	public String getName() {
		return Constants.FORUM_REPO_AS_FORUM_LISTENER;
	}

	public Forum getForumWithModeratorsAndThreads(final long forumId){
		return (Forum) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				//Get forum.
				String getForuumHql= "from Forum as forum where forum.id=:forumId";
				Forum forum = (Forum) session.createQuery(getForuumHql).setCacheable(true).setParameter("forumId", forumId).uniqueResult();
				//Fetch threads.
				String fetchForumThreadsHql = "from Forum as forum left join fetch forum.threads as thread left join fetch thread.subject where forum=:forum";
				session.createQuery(fetchForumThreadsHql).setCacheable(true).setParameter("forum", forum).list();
				//Fetch moderators.
				String fetchForumMorderatorsHql = "from Forum as forum left join fetch forum.moderators as moderator left join fetch moderator.roles where forum=:forum";
				session.createQuery(fetchForumMorderatorsHql).setCacheable(true).setParameter("forum", forum).list();
				logger.info("The Forum (ID="+forumId+") has loaded with moderators and threads");
				return forum;
			}
		});
	}

	public Forum getForum(final Long forumId,final int firstThreadIndex,final int threadCount) {
		return (Forum) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				logger.info("Start to load forum (id="+forumId+") with moderators and threads.");
				//Get forum.
				String getForuumHql= "from Forum as forum where forum.id=:forumId";
				Forum forum = (Forum) session.createQuery(getForuumHql).setCacheable(true).setParameter("forumId", forumId).uniqueResult();
				//Fetch threads.
				String fetchForumThreadsHql = "from Forum as forum left join fetch forum.threads as thread left join fetch thread.subject where forum=:forum";
				session.createQuery(fetchForumThreadsHql).setCacheable(true).setParameter("forum", forum).list();
				//Fetch moderators.
				String fetchForumMorderatorsHql = "from Forum as forum left join fetch forum.moderators as moderator left join fetch moderator.roles where forum=:forum";
				session.createQuery(fetchForumMorderatorsHql).setCacheable(true).setParameter("forum", forum).list();
				logger.info("The forum (id="+forumId+") has loaded with moderators and threads");
				return forum;
			}
		});
	}

	public int getForumThreadCount(final Long forumId) {
		return (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
//				Long forumThreadCount = (Long) session.createQuery("select count(*) from Thread as thread left outer join thread.forum as forum where forum.id=:forumId").setParameter("forumId", forumId).uniqueResult();
//				return forumThreadCount.intValue();
				Integer forumThreadCount = (Integer) session.createCriteria(Thread.class).add(Restrictions.eq("forum.id", forumId)).setProjection(Projections.rowCount()).uniqueResult();
				return forumThreadCount;
				//return (Integer)session.createCriteria(Thread.class).add(Restrictions.eq("forum.id", forumId)).set.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
	}
	
	public Long getForumPostsCount(final Forum forum){
		Long postsCount = (Long) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {			
			public Long doInHibernate(Session session) throws HibernateException,	SQLException {
				logger.info("Start to query forum post count.");
				//Get forum.
				String getForumPostsCountHql= "select count(*) from Post as p where p.thread.forum=:forum";
				Long count  = (Long) session.createQuery(getForumPostsCountHql).setCacheable(true).setParameter("forum", forum).uniqueResult();
				return count;
			}
		});	
		return postsCount;
	}

	public Forum getForumWithThreadByFetch(final Long forumId) {
		return (Forum) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				String hql = "select distinct forum from Forum as forum left join fetch forum.threads where forum.id=:forumId";
				Forum forum = (Forum) session.createQuery(hql).setParameter("forumId", forumId).uniqueResult();
				return forum;
			}
		});

	}

	@Override
	public void getForumById(final Long forumId) {
		Forum f1 = (Forum) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Forum>() {			
			public Forum doInHibernate(Session session) throws HibernateException,	SQLException {
				//String hql = "from Forum as forum left join fetch forum.threads where forum.id=:forumId";
				String hql = "from Forum as forum where forum.id=:forumId";
				Forum forum = (Forum) session.createQuery(hql).setParameter("forumId", forumId).uniqueResult();
				return forum;
			}
		});
		logger.debug("Forum 1 has loaded!");
//		get(forumId);
//		logger.debug("Forum 2 has loaded!");
	}

	@Override
	public void handleGetForumThreadEvent(final FindForumThreadsEvent event, ResultCollector result) {
		List<Thread> threads = (List<Thread>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Thread>>() {			
			@SuppressWarnings("unchecked")
			public List<Thread> doInHibernate(Session session) throws HibernateException,	SQLException {
				logger.info("Start to load threads of forum.");
				//Get forum.
				String getForuumThreadHql= "from Thread as thread where thread.forum=:forum";
				List<Thread> threads = (List<Thread>) session.createQuery(getForuumThreadHql)
															 .setCacheable(true)
															 .setParameter("forum", event.getSource())
										                     .setFirstResult(event.getStartIndex())
										                     .setMaxResults(event.getCount())
										                     .list();
				return threads;
			}
		});	
		result.add(threads);
	}
	

	@Override
	public void handleGetForumLatestPostEvent(final FindForumLatestPostEvent event, ResultCollector result) {
		Post lastPost = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Post>() {
			@Override
			public Post doInHibernate(Session session) throws HibernateException,SQLException {
				String getForumLatestPostHql = "from Post as post where post.thread.forum=:forum and post.creationTime = (select max(p.creationTime) from Post as p)";
				Post lastPost = (Post) session.createQuery(getForumLatestPostHql).setCacheable(true).setParameter("forum", event.getSource()).uniqueResult();
				return lastPost;
			}
		});		
		result.add(lastPost);
	}

	@Override
	public void handleGetFroumPostCountEvent(final CountForumPostEvent event, ResultCollector result) {
		Long count = (Long) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {			
			public Long doInHibernate(Session session) throws HibernateException,	SQLException {
				logger.info("Start to query forum post count.");
				//Get forum.
				String getForumPostCountHql= "select count(*) from Post as p where p.thread.forum=:forum";
				Long count  = (Long) session.createQuery(getForumPostCountHql).setCacheable(true).setParameter("forum", event.getSource()).uniqueResult();
				return count;
			}
		});	
		result.add(count);
	}
	
	@Override
	public void handleGetForumThreadCountEvent(final CountForumThreadEvent event, ResultCollector result) {
		Long count = (Long) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {			
			public Long doInHibernate(Session session) throws HibernateException,	SQLException {
				logger.info("Start to query forum thread count.");
				//Get forum.
				String getForumPostCountHql= "select count(*) from Thread as t where t.forum=:forum";
				Long count  = (Long) session.createQuery(getForumPostCountHql).setCacheable(true).setParameter("forum", event.getSource()).uniqueResult();
				return count;
			}
		});	
		result.add(count);
	}

	@Override
	public Forum getForumWithThreadByJoin(Long forumId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void perform(DomainTask task) {
		String taskName = task.getName();
		if(DomainTask.COUNT_FORUM_POSTS.equals(taskName)){
			Forum forum = (Forum)task.getProperty("forum");
			Long postsCount = getForumPostsCount(forum);
			task.setPerformResult(postsCount);
			task.setPerformed(true);
		}
	}
	
	
}
