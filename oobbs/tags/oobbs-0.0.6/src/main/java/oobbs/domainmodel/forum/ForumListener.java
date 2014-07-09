package oobbs.domainmodel.forum;

import oobbs.domainmodel.DomainObejctListener;
import oobbs.domainmodel.ResultCollector;

/**
 * The forum listener. It handles all events from Forum object.
 *
 * @see ForumEvent
 * @author laurence.geng
 */
public interface ForumListener extends DomainObejctListener {
	
	/**
	 * Handle the event that a forum requests to get its threads.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void handleGetForumThreadEvent(FindForumThreadsEvent event, ResultCollector result);
	
	/**
	 * Handle the event that a forum requests to get its last post.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void handleGetForumLatestPostEvent(FindForumLatestPostEvent event, ResultCollector result);
	
	/**
	 * Handle the event that a forum requests to get its post count.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void handleGetFroumPostCountEvent(CountForumPostEvent event, ResultCollector result);

	/**
	 * Handle the event that a forum requests to get its thread count.
	 *
	 * @param event the event
	 * @param result the result
	 */
	public void handleGetForumThreadCountEvent(CountForumThreadEvent event,	ResultCollector result);

}
