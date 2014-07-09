package oobbs.domainmodel.forum;

import java.util.List;

import oobbs.domainmodel.Repository;

public interface ForumGroupRepository extends Repository<ForumGroup,Long>{
	List<ForumGroup> getAllForumGroups();
}
