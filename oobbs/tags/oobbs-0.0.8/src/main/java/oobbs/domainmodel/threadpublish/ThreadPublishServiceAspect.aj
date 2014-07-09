package oobbs.domainmodel.threadpublish;

import oobbs.domainmodel.DomainServiceLocator;
import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.Thread;


public aspect ThreadPublishServiceAspect {
		
	after(Thread thread) : call (public void Thread.addReply(Message)) && target(thread) {
		//((ThreadPublishService)DomainServiceLocator.getInstance().locate("threadPublishService")).publish(thread);
	}
}
