package oobbs.presentation.action.user;

import oobbs.domainmodel.user.User;
import oobbs.presentation.action.BaseActionTestCase;

import org.apache.struts2.ServletActionContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.subethamail.wiser.Wiser;

public class SignupActionTest extends BaseActionTestCase {
    private SignupAction action;

    public void setSignupAction(SignupAction action) {
        this.action = action;
    }
    
    public void testDisplayForm() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest(null, "GET", "/signup.html");
        ServletActionContext.setRequest(request);
        assertEquals("input", action.execute());
    }  
    
    public void testExecute() throws Exception {
        User user = new User();
        user.setUsername("self-registered");
        user.setPassword("Password1");
        user.setConfirmPassword("Password1");
    
        
        user.setEmail("self-registered@raibledesigns.com");

        action.setUser(user);

        // set mock response so setting cookies doesn't fail
        ServletActionContext.setResponse(new MockHttpServletResponse());
        
        // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(getSmtpPort());
        wiser.start();
        
        assertEquals("success", action.save());
        assertFalse(action.hasActionErrors());
        
        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the session
//        assertNotNull(action.getSession().getAttribute(Constants.REGISTERED));

        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
