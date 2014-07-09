package oobbs.application.service.forum;

import java.util.Set;

public interface MyThreadsManagementService {
	Set<Thread> getMyThreads(Long userId);
	Set<Thread> getMyDiscussedThreads(Long userId);
	Set<Thread> getMySubscribedThreads(Long userId);
}
