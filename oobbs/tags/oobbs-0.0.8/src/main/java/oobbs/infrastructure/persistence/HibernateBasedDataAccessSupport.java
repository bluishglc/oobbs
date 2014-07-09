package oobbs.infrastructure.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class HibernateBasedDataAccessSupport<Entity> {

    protected final Log logger = LogFactory.getLog(getClass());
    
    protected Class<Entity> entityClass;
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;
    
    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

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
