package oobbs.application.service.user;

import oobbs.domainmodel.user.Role;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleService{
    /**
     * {@inheritDoc}
     */
    List getRoles(Role role);

    /**
     * {@inheritDoc}
     */
    Role getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    void saveRole(Role role);

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
}
