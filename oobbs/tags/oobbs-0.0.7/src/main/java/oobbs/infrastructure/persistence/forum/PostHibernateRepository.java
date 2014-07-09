package oobbs.infrastructure.persistence.forum;

import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.PostRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;

public class PostHibernateRepository extends AbstractHibernateRepository<Post, Long> implements PostRepository{

}
