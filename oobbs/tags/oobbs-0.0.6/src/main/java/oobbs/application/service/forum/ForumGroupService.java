package oobbs.application.service.forum;

import java.util.List;

import oobbs.application.dto.forum.ForumGroupDTO;

/**
 * An Application Service exposes a finer-grained interface than a service
 * facade and a coarser-grained interface than the underlying Business Objects
 * and other services. Application Services provide the background infrastructure for service facades .
 * 
 * What's the ApplicationService oriented?
 * ServiceFacade is use case oriented, how about ApplicationService? Business-Oriented?
 * I think so! We actrually need a role who choose right business objects to delegate business to them,
 * and load or persist them by repository. I'm a little confused about "application service" and "domain service".
 * I think the difference between them is clear, but the "ApplicationService" layer is not only for "application service". 
 * I think it's for "application service" & "domain service" and "business object"! It's an API for?
 * 
 * @author Laurence Geng
 * 
 */
public interface ForumGroupService {
	public List<ForumGroupDTO> getAllForumGroups();
}
