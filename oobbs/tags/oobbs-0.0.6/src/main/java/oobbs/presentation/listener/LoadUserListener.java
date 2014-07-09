package oobbs.presentation.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import oobbs.domainmodel.user.User;
import oobbs.domainmodel.user.UserRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * This listener is used for loading logged in user to HttpSession.
 * Because the spring-security encapsulate the authentication process, we have no cut point
 * to add user to session manually. but spring-security will add user name to session, this listener
 * will listen this operation, if user name is set, add user instance to session.
 * @author laurence.geng
 *
 */
public class LoadUserListener implements HttpSessionAttributeListener{

	public void attributeAdded(HttpSessionBindingEvent se) {
		if("SPRING_SECURITY_LAST_USERNAME".equals(se.getName())){
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(se.getSession().getServletContext());
			UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
			User user = (User)userRepository.getUserByName((String)se.getValue());
			se.getSession().setAttribute("user", user);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		if("SPRING_SECURITY_LAST_USERNAME".equals(se.getName())){
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(se.getSession().getServletContext());
			UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
			User user = (User)userRepository.getUserByName((String)se.getValue());
			se.getSession().setAttribute("user", user);
		}
	}

}
