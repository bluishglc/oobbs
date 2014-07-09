package oobbs.infrastructure.persistence.user;

import java.util.List;

import oobbs.domainmodel.user.Role;
import oobbs.domainmodel.user.RoleRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;


/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> 
 */
@Repository("roleRepository")
public class RoleHibernateRepository extends AbstractHibernateRepository<Role, Long> implements RoleRepository {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public RoleHibernateRepository() {}

    /**
     * {@inheritDoc}
     */
    public Role getRoleByName(final String rolename) {
		return (Role) getHibernateTemplate().executeWithNativeSession(
			new HibernateCallback() {
				public Object doInHibernate(Session session){
					List roles = session.getNamedQuery("findRoleByName").setParameter("name", rolename).list();
					if (roles.isEmpty()) {
						return null;
					} else {
						return (Role) roles.get(0);
					}
				}
			}
		);
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename) {
        Object role = getRoleByName(rolename);
        getHibernateTemplate().delete(role);
    }
}
