package oobbs.presentation.util;

import java.util.ArrayList;
import java.util.List;

import oobbs.Constants;
import oobbs.application.dto.forum.ForumDTO;
import oobbs.application.dto.forum.ForumGroupDTO;
import oobbs.application.dto.forum.ThreadDTO;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.Thread;

/**
 * Both NavigationNode and NavigationPathBuilder are presentation layer artifacts!
 * No services dependent them!
 *
 * In a way, NavigationPathBuilder is a type of assembler.
 */
public class NavigationPathBuilder {

    private List<NavigationNode> navigationPath = new ArrayList<NavigationNode>();

//	private String forumGroupName;
//	
//	private Long forumGroupId;
//	
//	private String forumName;
//	
//	private Long forumId;
//	
//	private String threadName;
//	
//	private Long threadId;

    public List<NavigationNode> getNavigationPath() {
        return navigationPath;
    }

    public void buildThreadPath(Thread thread){
        buildIndexNode();
        buildForumGroupNode(thread.getForum().getGroup().getName(), thread.getForum().getGroup().getId());
        buildForumNode(thread.getForum().getName(), thread.getForum().getId());
        buildThreadNode(thread.getName(), thread.getId());
    }

    public void buildForumPath(String forumGroupName, Long forumGroupId, String forumName, Long forumId){
        buildIndexNode();
        buildForumGroupNode(forumGroupName,forumGroupId);
        buildForumNode(forumName,forumId);
    }

    public void buildIndexNode(){
        NavigationNode indexNode = new NavigationNode();
        indexNode.setName("Index");
        indexNode.setAction(Constants.ACT_GET_ALL_FORUM_GROUPS);
        navigationPath.add(indexNode);
    }

    public void buildForumGroupNode(String forumGroupName,Long forumGroupId){
        NavigationNode forumGroupNode = new NavigationNode();
        forumGroupNode.setName(forumGroupName);
        forumGroupNode.addParam(Constants.REQ_PARAM_FORUM_GROUP_ID, forumGroupId);
        forumGroupNode.setAction(Constants.ACT_GET_FORUM_GROUP_BY_ID);
        navigationPath.add(forumGroupNode);
    }

    public void buildForumNode(String forumName, Long forumId){
//		forumName = name;
//		forumId = id;
        NavigationNode forumNode = new NavigationNode();
        forumNode.setName(forumName);
//		forumNode.addParam(Constants.REQ_PARAM_FORUM_GROUP_ID, forumGroupId);
        forumNode.addParam(Constants.REQ_PARAM_FORUM_ID, forumId);
        forumNode.setAction(Constants.ACT_GET_FORUM_BY_ID);
        navigationPath.add(forumNode);
    }

    public void buildThreadNode(String threadTitle, Long threadId){
        NavigationNode threadNode = new NavigationNode();
        threadNode.setName(threadTitle);
//		threadNode.addParam(Constants.REQ_PARAM_FORUM_GROUP_ID, forumGroupId);
//		threadNode.addParam(Constants.REQ_PARAM_FORUM_ID, forumId);
        threadNode.addParam(Constants.REQ_PARAM_THREAD_ID, threadId);
        threadNode.setAction(Constants.ACT_GET_THREAD_BY_ID);
        navigationPath.add(threadNode);
    }

    public void buildTextNode(String name){
        NavigationNode textNode = new NavigationNode();
        textNode.setName(name);
        navigationPath.add(textNode);
    }
}
