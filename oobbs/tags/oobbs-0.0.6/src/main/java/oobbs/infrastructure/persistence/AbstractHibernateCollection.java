package oobbs.infrastructure.persistence;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import oobbs.domainmodel.Collection;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * The hibernate implementation of domain collection inferface.
 * All collection's methods is implemented by hibernate, they all all database access operations.
 * @author laurence.geng
 */
public abstract class AbstractHibernateCollection<Owner,Element> extends HibernateBasedDataAccessSupport<Element> implements Collection<Owner,Element> {

	protected Owner owner;
	
	protected String ownerAlias;
	
  /**
  * Constructor that takes in a class to see which type of entity to persist
  * @param entityClass the class type you'd like to persist
  */
	@SuppressWarnings("unchecked")
	public AbstractHibernateCollection() {
	    entityClass = (Class<Element>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}	
	
	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}

	public boolean contains(Element object) {
		return getHibernateTemplate().contains(object);
	}

	public boolean isEmpty() {
		return size()>0?false:true;
	}
	
	public int size() {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Integer>() {
					@Override
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.add(Restrictions.eq(ownerAlias, owner));
						criteria.setProjection(Projections.rowCount());
						return (Integer) criteria.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Element> toList(final int startIndex, final int offset) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List<Element>>() {
					@Override
					public List<Element> doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.add(Restrictions.eq(ownerAlias, owner));
						criteria.setFirstResult(startIndex);
						criteria.setMaxResults(offset);
						return criteria.list();
					}

				});
	}
}
