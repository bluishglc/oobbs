package oobbs.presentation.action;

import java.util.List;

import oobbs.application.util.NavigationNode;

public interface NavigationPathProvider {
	public List<NavigationNode> getNavigationPath();
}
