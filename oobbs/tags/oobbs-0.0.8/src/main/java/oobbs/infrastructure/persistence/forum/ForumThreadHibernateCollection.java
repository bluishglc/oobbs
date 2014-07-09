package oobbs.infrastructure.persistence.forum;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import oobbs.infrastructure.persistence.AbstractHibernateCollection;
import oobbs.domainmodel.forum.Forum;
import oobbs.domainmodel.forum.Thread;
@Repository("forumThreads")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ForumThreadHibernateCollection extends AbstractHibernateCollection<Forum,Thread>{

}
