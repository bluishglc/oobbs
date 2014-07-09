package oobbs.infrastructure.persistence.user;

import java.util.List;

import javax.persistence.Table;

import oobbs.domainmodel.user.User;
import oobbs.domainmodel.user.UserRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with 
 *   the new BaseDaoHibernate implementation that uses generics.
*/
public class UserHibernateRepository extends AbstractHibernateRepository<User, Long> implements UserRepository {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserHibernateRepository() {}


    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        log.debug("user's id: " + user.getId());
        merge(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return user;
    }

    /** 
     * {@inheritDoc}
    */
    public User getUserByName(final String username) throws UsernameNotFoundException {
    	return (User) getHibernateTemplate().executeWithNativeSession(
    			new HibernateCallback() {
    				public Object doInHibernate(Session session){
    					return session.createCriteria(entityClass)
    					.add(Restrictions.eq("username", username))
    					.setFetchMode("roles", FetchMode.JOIN)
    					.uniqueResult();
    				}
    			}
    	);
    }

    /** 
     * {@inheritDoc}
    */
    public String getUserPassword(String username) {
        SimpleJdbcTemplate jdbcTemplate =
                new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where username=?", String.class, username);

    }

	public User getAdministrator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
