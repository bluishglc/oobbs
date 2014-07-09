package oobbs.presentation.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import oobbs.Constants;
import oobbs.presentation.action.NavigationPathProvider;
import oobbs.presentation.util.NavigationNode;
import oobbs.presentation.util.NavigationPathBuilder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Auto intercept request and set navigation path to request so as jsp page show it.
 * 
 * @author laurence.geng
 */
public class NavigationGenerationInterceptor implements Interceptor {

	private static final long serialVersionUID = -4170035488561436087L;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object action = invocation.getAction();
		String actionName = invocation.getInvocationContext().getName();
		if (action instanceof NavigationPathProvider) {
			NavigationPathProvider provider = (NavigationPathProvider) action;
			request.setAttribute(Constants.REQ_ATTRIB_NAVIGATION_PATH, provider.getNavigationPath(actionName));
		}else{
			NavigationPathBuilder builder = new NavigationPathBuilder();
			builder.buildIndexNode();
			request.setAttribute(Constants.REQ_ATTRIB_NAVIGATION_PATH, builder.getNavigationPath());
		}
		return invocation.invoke();
	}

}
