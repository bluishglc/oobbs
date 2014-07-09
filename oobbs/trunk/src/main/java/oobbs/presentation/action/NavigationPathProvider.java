package oobbs.presentation.action;

import java.util.List;

import oobbs.presentation.util.NavigationNode;

public interface NavigationPathProvider {
	public List<NavigationNode> getNavigationPath(String actionName);
}
