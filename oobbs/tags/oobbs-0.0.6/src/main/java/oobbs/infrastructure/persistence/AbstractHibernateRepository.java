package oobbs.infrastructure.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import oobbs.domainmodel.Repository;

/**
 *
 * @author Laurence Geng
 * @param <Entity> a type variable
 * @param <PK> the primary key for that type
 */
public abstract class AbstractHibernateRepository<Entity, PK extends Serializable> extends HibernateBasedDataAccessSupport<Entity> implements Repository<Entity, PK> {

	 /**
	  * Constructor that takes in a class to see which type of entity to persist
	  * @param entityClass the class type you'd like to persist
	  */
	 @SuppressWarnings("unchecked")
	 public AbstractHibernateRepository() {
	     entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	 }
    /*----------------------------------    Business Methods    ----------------------------------*/
    
    public Entity get(PK id) {
    	return (Entity) getHibernateTemplate().get(entityClass, id);
    }

    public Entity load(PK id) {
    	return (Entity) getHibernateTemplate().load(entityClass, id);
	}
    
	public List<Entity> loadAll() {
    	return getHibernateTemplate().loadAll(entityClass);
    }   
   
    public boolean contains(PK id) {
    	return get(id)!=null?true:false;
    } 
   
    public boolean contains(Entity entity) {
    	return getHibernateTemplate().contains(entity);
	}
    
    public void persist(Entity entity) {
        getHibernateTemplate().persist(entity);
    }
       
    public Entity merge(Entity entity) {
    	return (Entity) getHibernateTemplate().merge(entity);
    }    

    public void delete(PK id) {
    	getHibernateTemplate().delete(get(id));
    }    
    
    public void delete(Entity object) {
    	getHibernateTemplate().delete(object);
	}

	public void flush() {
		getSession().flush();
	}

	public void refresh(Entity entity) {
		getHibernateTemplate().refresh(entity);
	}
    
//    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
//        Criteria crit = getHibernateTemplate().findby.createCriteria(getPersistentClass());
//        Example example =  Example.create(exampleInstance);
//        for (String exclude : excludeProperty) {
//            example.excludeProperty(exclude);
//        }
//        crit.add(example);
//        return crit.list();
//    }
   
//    protected List<T> findByCriteria(Criterion... criterion) {
//        Criteria crit = getHibernateTemplate().createCriteria(getPersistentClass());
//        for (Criterion c : criterion) {
//            crit.add(c);
//        }
//        return crit.list();
//    }    
//    public T merge(T entity){
//    	return (T) getHibernateTemplate().merge(entity);
//    }
//    
//    public void persist(T entity){
//    	getHibernateTemplate().persist(entity);
//    }
   
}
