package oobbs.infrastructure.persistence.forum;

import java.math.BigInteger;
import java.util.List;

import oobbs.infrastructure.persistence.AbstractHibernateCollection;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.Post;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ForumPostHibernateCollection extends AbstractHibernateCollection<Forum,Post>{

//	@Override
//	public int size() {
//		return (Integer) getHibernateTemplate().executeWithNativeSession(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session){
//						BigInteger result = (BigInteger)session.createSQLQuery("select count(*) from forum left join thread on forum.id = thread.forumId left join post on thread.id = post.threadId where forumId = "+owner.getId()).uniqueResult();
//						return  result.intValue();
//					}
//				}
//			);
//	}
//
//	@Override
//	public List<Post> toList() {
//		return null;
//	}
//
//	@Override
//	public List<Post> toList(int startIndex, int offset) {
//		return null;
//	}
	
}
