package oobbs.application.util;

import java.util.ArrayList;
import java.util.List;

import oobbs.Constants;

/**
 * Builder+Flyweight
 * 
 * @author Laurence Geng
 */
public class NavigationPathBuilder {
	
	private List<NavigationNode> navigationPath = new ArrayList<NavigationNode>();
	
	private String forumGroupName;
	
	private Long forumGroupId;
	
	private String forumName;
	
	private Long forumId;
	
	private String threadName;
	
	private Long threadId;
		
	public List<NavigationNode> getNavigationPath() {
		return navigationPath;
	}

	public void buildIndexNode(){
		NavigationNode indexNode = new NavigationNode();
		indexNode.setName("Index");
		indexNode.setAction(Constants.ACT_GET_ALL_FORUM_GROUPS);
		navigationPath.add(indexNode);
	}
	
	public void buildForumGroupNode(String name,Long id){
		forumGroupName = name;
		forumGroupId = id;
		NavigationNode forumGroupNode = new NavigationNode();
		forumGroupNode.setName(forumGroupName);
		forumGroupNode.addParam(Constants.REQ_PARAM_FORUM_GROUP_ID, forumGroupId);
		forumGroupNode.setAction(Constants.ACT_GET_FORUM_GROUP_BY_ID);		
		navigationPath.add(forumGroupNode);
	}
	
	public void buildForumNode(String name,Long id){
		forumName = name;
		forumId = id;
		NavigationNode forumNode = new NavigationNode();
		forumNode.setName(forumName);
		forumNode.addParam(Constants.REQ_PARAM_FORUM_GROUP_ID, forumGroupId);
		forumNode.addParam(Constants.REQ_PARAM_FORUM_ID, forumId);
		forumNode.setAction(Constants.ACT_GET_FORUM_BY_ID);		
		navigationPath.add(forumNode);
	}
	
	public void buildThreadNode(String name,Long id){
		NavigationNode threadNode = new NavigationNode();
		threadNode.setName(name);
		threadNode.addParam(Constants.REQ_PARAM_FORUM_GROUP_ID, forumGroupId);
		threadNode.addParam(Constants.REQ_PARAM_FORUM_ID, forumId);
		threadNode.addParam(Constants.REQ_PARAM_THREAD_ID, id);
		threadNode.setAction(Constants.ACT_GET_THREAD_BY_ID);		
		navigationPath.add(threadNode);
	}
}
