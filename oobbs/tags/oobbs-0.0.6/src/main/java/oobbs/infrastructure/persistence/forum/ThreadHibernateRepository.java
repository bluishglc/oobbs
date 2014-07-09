package oobbs.infrastructure.persistence.forum;

import java.sql.SQLException;
import java.util.List;

import oobbs.Constants;
import oobbs.domainmodel.ResultCollector;
import oobbs.domainmodel.forum.FindThreadPostsEvent;
import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.Thread;
import oobbs.domainmodel.forum.ThreadRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ThreadHibernateRepository extends AbstractHibernateRepository<Thread, Long> implements ThreadRepository{

	public ThreadHibernateRepository() {}	

	@Override
	public String getName() {
		return Constants.THREAD_REPO_AS_THREAD_LISTENER;
	}

	public int getThreadPostCount() {
		return 0;
	}

	public void saveThread(final Thread thread) {
		getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session){
						try {
							//As there is cyclic dependence between Thread and Post
							//So, we have to set thread's subject null then save it.
							//After subject saved, reset it to thread and update thread.
							Post subject = thread.getSubject();
							thread.setSubject(null);
							session.save(thread);
							//session.save(subject);
							thread.setSubject(subject);
							session.update(thread);							
						} catch (HibernateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
				}
			);
	}

	@Override
	public void handleGetThreadPostEvent(final FindThreadPostsEvent event, ResultCollector result) {
		List<Post> posts = (List<Post>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {			
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				logger.info("Start to load posts of thread.");
				//Get forum.
				String getThreadPostHql= "from Post as post where post.thread=:thread";
				List<Post> posts = (List<Post>) session.createQuery(getThreadPostHql)
															 .setCacheable(true)
															 .setParameter("thread", event.getSource())
										                     .setFirstResult(event.getStartIndex())
										                     .setMaxResults(event.getCount())
										                     .list();
				return posts;
			}
		});	
		result.add(posts);
	}
	
	

}
