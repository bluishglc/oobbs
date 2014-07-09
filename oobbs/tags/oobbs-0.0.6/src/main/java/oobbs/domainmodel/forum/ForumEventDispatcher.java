package oobbs.domainmodel.forum;

import oobbs.Constants;
import oobbs.domainmodel.DomainEventDispatcher;
import oobbs.domainmodel.ResultCollector;

/**
 * The Class ForumEventDispatcher.
 */
public class ForumEventDispatcher extends DomainEventDispatcher{
	
	/**
	 * Dispatch.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void dispatch(FindForumThreadsEvent event, ResultCollector result){
		ForumListener forumListener = (ForumListener) listeners.get(Constants.FORUM_REPO_AS_FORUM_LISTENER);
		forumListener.handleGetForumThreadEvent(event,result);
	}
	
	public void dispatch(FindForumLatestPostEvent event, ResultCollector result){
		ForumListener forumListener = (ForumListener) listeners.get(Constants.FORUM_REPO_AS_FORUM_LISTENER);
		forumListener.handleGetForumLatestPostEvent(event, result);
	}
	
	/**
	 * Dispatch.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void dispatch(CountForumPostEvent event,ResultCollector result){
		ForumListener forumListener = (ForumListener) listeners.get(Constants.FORUM_REPO_AS_FORUM_LISTENER);
		forumListener.handleGetFroumPostCountEvent(event, result);
	}
	
	/**
	 * Dispatch.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void dispatch(CountForumThreadEvent event, ResultCollector result){
		ForumListener forumListener = (ForumListener) listeners.get(Constants.FORUM_REPO_AS_FORUM_LISTENER);
		forumListener.handleGetForumThreadCountEvent(event,result);
	}

}
