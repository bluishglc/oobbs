package oobbs.application.service.user;

import java.util.List;

import oobbs.domainmodel.user.Role;
import oobbs.domainmodel.user.RoleRepository;

/**
 * Implementation of RoleManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
public class RoleManagerImpl implements RoleService {
	
	public RoleManagerImpl(){}

	private RoleRepository roleRepository;

    public void setRoleDao(RoleRepository dao) {
        this.roleRepository = dao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> getRoles(Role role) {
        return roleRepository.loadAll();
    }

    /**
     * {@inheritDoc}
     */
    public Role getRole(String rolename) {
        return roleRepository.getRoleByName(rolename);
    }

    /**
     * {@inheritDoc}
     */
    public void saveRole(Role role) {
        roleRepository.persist(role);
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename) {
        roleRepository.removeRole(rolename);
    }

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	
}