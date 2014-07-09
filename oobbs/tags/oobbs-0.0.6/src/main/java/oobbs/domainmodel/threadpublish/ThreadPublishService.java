package oobbs.domainmodel.threadpublish;

import oobbs.domainmodel.forum.Thread;
import oobbs.domainmodel.shortmessage.ShortMessage;
import oobbs.domainmodel.shortmessage.ShortMessageService;

/**
 * Managed by Spring IOC!!
 * 
 * @author laurence.geng
 *
 */
public class ThreadPublishService {
	
	private ShortMessageService shortMessageService;
	
//	private ThreadRepository threadRepository;
	
//	private UserRepository userRepository;

	/**
	 * Process thread subscription request, make establish relationship between
	 * a user and a thread.
	 * 
	 * @param request The thread subscription request
	 */
//	public void subscribe(ThreadSubscriptionRequest request){
//		userRepository.saveSubscription
//		System.out.println("subscribe");
//	}
	
//	public void unsubscribe(ThreadUnsubscriptionRequest request){
//		System.out.println("unsubscribe");
//	}
	
	/**
	 * If there are any update for a thread, The ThreadPublishService will
	 * notify all subscribers.
	 */
	public void publish(Thread thread){
		System.out.println("Publishing.......");
		String messageTitle = "Thead Update Notice";
		String messageBody = "Thread:"+thread.getTitle()+" updated!";
		//List<User> subscribers = new ArrayList<User>(thread.getSubscribers());
		ShortMessage shortMessage = shortMessageService.createSystemShortMessage(messageTitle, messageBody, null);		
		shortMessageService.sendShortMessage(shortMessage);
	}
	
	public void setShortMessageService(ShortMessageService shortMessageService) {
		this.shortMessageService = shortMessageService;
	}

//	public void setThreadRepository(ThreadRepository threadRepository) {
//		this.threadRepository = threadRepository;
//	}
	
}
