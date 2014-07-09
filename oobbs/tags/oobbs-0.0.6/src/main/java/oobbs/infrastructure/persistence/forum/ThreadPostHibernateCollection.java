package oobbs.infrastructure.persistence.forum;

import oobbs.infrastructure.persistence.AbstractHibernateCollection;
import oobbs.domainmodel.forum.Post;

public class ThreadPostHibernateCollection extends AbstractHibernateCollection<Thread,Post>{

//	@Override
//	public int size() {
//		return (Integer) getHibernateTemplate().executeWithNativeSession(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session){
//						Criteria criteria = session.createCriteria(entityClass);
//						criteria.add(Restrictions.eq(ownerName, owner));
//						criteria.setProjection(Projections.rowCount());
//						return (Integer)criteria.uniqueResult();
//					}
//				}
//			);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Post> toList() {
//		return (List<Post>)getHibernateTemplate().executeWithNativeSession(
//					new HibernateCallback() {
//						public Object doInHibernate(Session session){
//							Criteria criteria = session.createCriteria(entityClass);
//							criteria.add(Restrictions.eq(ownerName, owner));
//							return criteria.list();
//						}
//					}
//				);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Post> toList(final int startIndex, final int offset) {
//		return (List<Post>)getHibernateTemplate().executeWithNativeSession(
//					new HibernateCallback() {
//						public Object doInHibernate(Session session){
//							Criteria criteria = session.createCriteria(entityClass);
//							criteria.add(Restrictions.eq(ownerName, owner));
//							criteria.add(Restrictions.eq("isSubject",false));
//							criteria.setFirstResult(startIndex);
//							criteria.setMaxResults(offset);
//							return criteria.list();
//						}
//					}
//				);
//	}
	
}
