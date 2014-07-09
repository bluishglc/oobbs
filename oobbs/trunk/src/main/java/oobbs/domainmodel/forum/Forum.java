package oobbs.domainmodel.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import oobbs.domainmodel.Collection;
import oobbs.domainmodel.DomainEventDispatcher;
import oobbs.domainmodel.DomainObjectSupport;
import oobbs.domainmodel.DomainResponse;
import oobbs.domainmodel.SupportRequest;
import oobbs.domainmodel.ResultCollector;
import oobbs.domainmodel.SupportResponse;
import oobbs.domainmodel.user.User;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

@Configurable
@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Forum implements Serializable{

	private static final Logger logger = Logger.getLogger(Forum.class);

	private static final long serialVersionUID = 7953759118489901062L;
	
	@Transient 
	private DomainObjectSupport domainObjectSupport;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(length=255,nullable=false,unique=true)
	private String name;
	
	@Column
	private String description;
	
	@ManyToMany
	@JoinTable(name="Forum_Moderator",joinColumns=@JoinColumn(name="forumId"),inverseJoinColumns=@JoinColumn(name="moderatorId"))
	@org.hibernate.annotations.ForeignKey(name="fk_Forum_Moderator_forumId_Forum",inverseName="fk_Forum_Moderator_moderatorId_User")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<User> moderators;
	
	//No cascade with forum's threads!
//	@OneToMany(mappedBy="forum")
//	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.EXTRA) //IMPORTANT!
//	@OrderBy
//	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//	private Set<Thread> threads = new LinkedHashSet<Thread>();
	
	@Transient 
	private Collection<Forum,Thread> threads;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupId",nullable=false)
	@org.hibernate.annotations.ForeignKey(name="fk_Forum_groupId_ForumGroup")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private ForumGroup group;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,insertable=false,updatable=false)
	@org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.INSERT)
	private Date creationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,insertable=false,updatable=false)
	@org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
	private Date modifiedTime;
	
	public Forum(){}
	
	public Forum(ForumGroup group){
		this.group = group;
	}
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/
	
	
//	/**
//	 * If you don't provide data access based collection, you can provide this method
//	 * directly. It's cleaner and easier.
//	 * @param startIndex
//	 * @param count
//	 * @return
//	 */
//	public List<Thread> getThreads(int startIndex, int count){
//		FindForumThreadsEvent event = new FindForumThreadsEvent(this, startIndex, count);
//		ResultCollector<List<Thread>> result = new ResultCollector<List<Thread>>();
//		forumEventDispatcher.dispatch(event,result);
//		return result.getUniqueResult();
//	}
	
	
//	public Long getThreadCount(){
//		CountForumThreadEvent event = new CountForumThreadEvent(this);
//		ResultCollector<Long> result = new ResultCollector<Long>();
//		forumEventDispatcher.dispatch(event,result);
//		return result.getUniqueResult();
//	}
	
//	public int getThreadCount(){
//		return threads.size();
//	}
	
	/**
	 * Get last post is an application request, Is it Forum's responsibility?
	 * We should not get last post from collection, actually, the last post is a type of
	 * search by element's feature. Typically, we iterate a collection to find the one we need.
	 * but for database based data, the "iteration" process have to be an database inner process!
	 * That's difference.
	 * 
	 * 2013-3-23
	 * 
	 * Getting latest post is really a domain event? it's more like a domain inner navigation/search behavior.
	 * 
	 * True domain event must make domain sense!
	 * 
	 * Specification! Declarative Design!
	 * @return
	 */
	public Post getLatestPost(){
		SupportRequest request = new SupportRequest(SupportRequest.GET_FORUM_LATEST_POST);
		request.setParam("forum", this);
		SupportResponse response = new SupportResponse();
		domainObjectSupport.dispatch(request,response);
		return (Post) response.getResult();
	}
	
	/**
	 * Is counting post Thread's responsibility? It seems like no!
	 *  
	 * AOP?Hibernate Interceptor?Collection?If collection, how to design interface?Or No such method?
	 * Let service process?
	 * No matter which way, the count and last post must be from query and statics, not a filed in db!
	 * So, the Forum also have no fields!
	 * @return
	 */ 
	public Long getPostCount(){
		SupportRequest supportRequest = new SupportRequest(SupportRequest.COUNT_FORUM_POSTS);
		supportRequest.setParam("forum", this);
		SupportResponse supportResponse = new SupportResponse();
		domainObjectSupport.dispatch(supportRequest,supportResponse);
		return (Long) supportResponse.getResult();
	}
	
	public void addModerator(User user){
		moderators.add(user);
	}
	
	public void removeModerator(User moderator){
		if(moderators.contains(moderator)){
			moderators.remove(moderator);
		}
	}
	
	public Thread createThread(){
		return new Thread(this);
	}
	
	public void topThread(){
		
	}

	public void highlightThread(){
		
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public String getName() {
		return name;
	}

	public Collection<Forum,Thread> getThreads() {
		return threads;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public Set<User> getModerators() {
		return moderators;
	}	
	
	public void setModerators(Set<User> moderators) {
		this.moderators = moderators;
	}

	public ForumGroup getGroup() {
		return group;
	}

	public void setGroup(ForumGroup group) {
		this.group = group;
	}

	public Thread getThread(Long threadId) {
		return null;
	}
	@Autowired
	public void setDomainObjectSupport(DomainObjectSupport domainObjectSupport) {
		this.domainObjectSupport = domainObjectSupport;
	}

	@Autowired
	public void setThreads(@Qualifier("forumThreads")Collection<Forum,Thread> threads) {
		this.threads = threads;
		this.threads.setOwner(this);
		this.threads.setOwnerAlias("forum");
	}
	
	
	/*----------------------------------    Common Methods    ----------------------------------*/

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Forum)) {
			return false;
		}
		Forum rhs = (Forum) object;
		return new EqualsBuilder().append(this.name, rhs.name).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-277455867, 237823191).append(this.name).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append(
				"description", this.description).append("creationTime",
				this.creationTime).toString();
	}

}