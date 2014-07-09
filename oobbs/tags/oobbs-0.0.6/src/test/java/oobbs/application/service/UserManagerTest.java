package oobbs.application.service;

import oobbs.Constants;
import oobbs.application.service.user.RoleService;
import oobbs.application.service.user.UserService;
import oobbs.domainmodel.user.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserManagerTest extends BaseManagerTestCase {
    //~ Instance fields ========================================================

    private UserService mgr = null;
    private RoleService roleManager = null;
    private Log log = LogFactory.getLog(UserManagerTest.class);
    private User user;
    
    public void setUserManager(UserService userManager) {
        this.mgr = userManager;
    }
    
    public void setRoleManager(RoleService roleManager) {
        this.roleManager = roleManager;
    }

    public void testGetUser() throws Exception {
        user = mgr.getUserByUsername("user");
        assertNotNull(user);
        
        log.debug(user);
        assertEquals(1, user.getRoles().size());
    }

    public void testSaveUser() throws Exception {
        user = mgr.getUserByUsername("user");

        log.debug("saving user with updated phone number: " + user);

        user = mgr.saveUser(user);
        assertEquals(1, user.getRoles().size());
    }

    public void testAddAndRemoveUser() throws Exception {
        user = new User();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        user = (User) populate(user);

        user.addRole(roleManager.getRole(Constants.USER_ROLE));

        user = mgr.saveUser(user);
        assertEquals("john", user.getUsername());
        assertEquals(1, user.getRoles().size());

        log.debug("removing user...");

        mgr.removeUser(user.getId().toString());

        try {
            user = mgr.getUserByUsername("john");
            fail("Expected 'Exception' not thrown");
        } catch (Exception e) {
            log.debug(e);
            assertNotNull(e);
        }
    }
}
