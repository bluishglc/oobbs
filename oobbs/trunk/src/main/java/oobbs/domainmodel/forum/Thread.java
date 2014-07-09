package oobbs.domainmodel.forum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import oobbs.domainmodel.Collection;
import oobbs.domainmodel.ResultCollector;
import oobbs.domainmodel.threadpublish.ThreadPublishService;
import oobbs.domainmodel.user.User;
import oobbs.domainmodel.util.TimeAutoStamped;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * When there are any update,notify ThreadSubscriptionService so as it can publish
 * messages to all subscribers.
 * 
 * @author laurence.geng
 */
@Configurable
@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Thread implements TimeAutoStamped,Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2987534613331518441L;

	/** The id. */
	@Id @GeneratedValue
	private Long id;
	
	/** 
	 * There is cyclic dependencies between thread and post: 
	 * Thread -> Post (As Subject)
	 * Post (As Thread's Posts) <-> Thread (As Post's Thread)
	 * So, one of these dependencies have to be nullable, otherwise both of them can't be persisted.
	 * We define: Thread's subject is nullable so as we can save thread first! 
	 */
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="subjectId",unique=true)
	@org.hibernate.annotations.ForeignKey(name="fk_Thread_subjectId_Post")	
	private Post subject;
	
	@Transient
	private Collection<Thread,Post> posts;
	
	/** The forum. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="forumId",nullable=false)
	@org.hibernate.annotations.ForeignKey(name="fk_Thread_forumId_Forum")
	private Forum forum;
	
	/** The subscribers. */
	@ManyToMany(mappedBy="subscribedThreads")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<User> subscribers;
	
	/** The thread publish service. */
	@Transient 
	private ThreadPublishService threadPublishService;

	/** The creation time. */
	@Column(nullable=false,insertable=true,updatable=false)
	private Date creationTime;
	
	/** The modified time. */
	@Column(nullable=false,insertable=true,updatable=true)
	private Date modifiedTime;

	/**
	 * You can't create subject in default constructor.
	 * When hibernate recreate a thread, its subject will populated automatically.
	 */
	public Thread(){
//		subject =  createPost();
	}
	
	public Thread(Forum forum){
		this();
		this.forum = forum;
	}
	

	/*---------------------------------    Main Logic Methods    ---------------------------------*/
	
	public Post createPost(){
		Post post = new Post(this);
		return post;
	}
	
	/**
	 * Adds the subscriber.
	 * 
	 * @param subscriber the subscriber
	 */
	public void addSubscriber(User subscriber){
		subscribers.add(subscriber);
	}
	
	/**
	 * Removes the subscriber.
	 * 
	 * @param subscriber the subscriber
	 */
	public void removeSubscriber(User subscriber){
		subscribers.remove(subscriber);
	}
	
	/**
	 * Removes the reply.
	 * 
	 * @param post the post
	 */
	public void removeReply(Post post){
		
	}
	
//	public List<Post> getPosts(int startIndex, int count){
//		FindThreadPostsEvent event = new FindThreadPostsEvent(this, startIndex, count);
//		ResultCollector<List<Post>> result = new ResultCollector<List<Post>>();
//		threadEventDispatcher.dispatch(event, result);
//		return result.getUniqueResult();
//	}

	/**
	 * Gets the reply count.
	 * 
	 * @return the reply count
	 */
	public long getReplyCount(){
		CountThreadReplyEvent event = new CountThreadReplyEvent();
//		return threadPosts.size()-1;
//		return replies.size();
		return 2L;
	}

	/**
	 * Gets the view count.
	 * 
	 * @return the view count
	 */
	public int getViewCount(){
		return 0;
	}

	/**
	 * Gets the last post author.
	 * 
	 * @return the last post author
	 */
	public User getLastPostAuthor(){
		return null;
	}	
	
	
	
	/**
	 * @see oobbs.domainmodel.forum.ForumNode#getName()
	 */
	public String getName() {
		return getTitle();
	}

	/*----------------------------------    Common Methods    ----------------------------------*/
	
	/**
	 * Equals.
	 * 
	 * @param object the object
	 * 
	 * @return true, if equals
	 * 
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Thread)) {
			return false;
		}
		Thread rhs = (Thread) object;
		return new EqualsBuilder().append(this.subject, rhs.subject).isEquals();
	}

	/**
	 * Hash code.
	 * 
	 * @return the int
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-143469929, -324675305).append(this.subject).toHashCode();
	}	

	/*----------------------------------    Accessor Methods    ----------------------------------*/

	
	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 */
	public Post getSubject() {
		return subject;
	}

	public Collection<Thread,Post> getPosts() {
		return posts;
	}

	@Autowired
	public void setPosts(@Qualifier("threadPosts")Collection<Thread,Post> posts) {
		posts.setOwnerAlias("thread");
		posts.setOwner(this);
		this.posts = posts;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject the new subject
	 */
	public void setSubject(Post subject) {
		this.subject = subject;
	}

	/* (non-Javadoc)
	 * @see oobbs.domainmodel.forum.ForumNode#getId()
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see oobbs.domainmodel.util.TimeAutoStamped#getCreationTime()
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/* (non-Javadoc)
	 * @see oobbs.domainmodel.util.TimeAutoStamped#getModifiedTime()
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}
	
	/**
	 * Sets the creation time.
	 * 
	 * @param creationTime the new creation time
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * Sets the modified time.
	 * 
	 * @param modifiedTime the new modified time
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/**
	 * Gets the forum.
	 * 
	 * @return the forum
	 */
	public Forum getForum() {
		return forum;
	}
//
//	/**
//	 * Sets the forum.
//	 * 
//	 * @param forum the new forum
//	 */
//	public void setForum(Forum forum) {
//		this.forum = forum;
//	}

	/**
	 * Gets the subscribers.
	 * 
	 * @return the subscribers
	 */
	public Set<User> getSubscribers() {
		return subscribers;
	}

	/**
	 * Gets the thread publish service.
	 * 
	 * @return the thread publish service
	 */
	public ThreadPublishService getThreadPublishService() {
		return threadPublishService;
	}

	/**
	 * Sets the thread publish service.
	 * 
	 * @param threadPublishService the new thread publish service
	 */
	public void setThreadPublishService(ThreadPublishService threadPublishService) {
		this.threadPublishService = threadPublishService;
	}
	
	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return subject.getTitle();
	}

	/**
	 * Sets the title.
	 * 
	 * @param title the new title
	 */
	public void setTitle(String title) {
		subject.setTitle(title);
	}

	/**
	 * Gets the message body.
	 * 
	 * @return the message body
	 */
	public String getMessageBody() {
		return subject.getMessageBody();
	}

	/**
	 * Sets the message body.
	 * 
	 * @param messageBody the new message body
	 */
	public void setMessageBody(String messageBody) {
		subject.setMessageBody(messageBody);
	}	
	
	/**
	 * Gets the author.
	 * 
	 * @return the author
	 */
	public User getAuthor() {
		return subject.getAuthor();
	}

	/**
	 * Sets the author.
	 * 
	 * @param author the new author
	 */
	public void setAuthor(User author) {
		subject.setAuthor(author);
	}

}