package oobbs.infrastructure.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class HibernateBasedDataAccessSupport<Entity> extends HibernateDaoSupport {

    protected final Log log = LogFactory.getLog(getClass());
    
    protected Class<Entity> entityClass;

//    /**
//     * Constructor that takes in a class to see which type of entity to persist
//     * @param entityClass the class type you'd like to persist
//     */
//    @SuppressWarnings("unchecked")
//    public HibernateBasedDataAccessSupport() {
//        entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//     }
    
    /*----------------------------------    Accessor Methods    ----------------------------------*/
    
//    /**
//     * Factory method for return entity class polymorphic.
//     */
//    public abstract Class<Entity> getEntityClass();

}
