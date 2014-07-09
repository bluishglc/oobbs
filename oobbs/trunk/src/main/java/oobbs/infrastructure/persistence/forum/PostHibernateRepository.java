package oobbs.infrastructure.persistence.forum;

import org.springframework.stereotype.Repository;

import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.PostRepository;
import oobbs.infrastructure.persistence.AbstractHibernateRepository;
@Repository("postRepository")
public class PostHibernateRepository extends AbstractHibernateRepository<Post, Long> implements PostRepository{

}
