package oobbs.domainmodel.forum;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import oobbs.domainmodel.user.User;
import oobbs.domainmodel.util.TimeAutoStamped;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Post implements Serializable,TimeAutoStamped{

	private static final long serialVersionUID = 6216050987031581506L;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(length=255,nullable=false)
	private String title;
	
	@Column
	private String messageBody;
	
	@Column(nullable = false,updatable=false)
	private boolean isSubject = false;
	
	@ManyToOne(fetch=FetchType.LAZY) //Unidirectional!
	@JoinColumn(name="authorId",nullable=false)
	@org.hibernate.annotations.ForeignKey(name = "fk_Post_authorId_User")
	private User author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="quotedPostId")
	@org.hibernate.annotations.ForeignKey(name = "fk_Post_quotedPostId_Post")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Post quotedPost;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="threadId",nullable=false,insertable=true,updatable=false)
	@org.hibernate.annotations.ForeignKey(name = "fk_Post_threadId_Thread")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Thread thread;
	
	@Column(nullable=false,insertable=true,updatable=false)
	private Date creationTime;
	
	@Column(nullable=false,insertable=true,updatable=true)
	private Date modifiedTime;

	public Post(){}
	
	public Post(Thread thread){
		this.thread = thread;
	}
	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public String getTitle() {
		return title;
	}

	public boolean isSubject() {
		return isSubject;
	}

	public void setSubject(boolean isSubject) {
		this.isSubject = isSubject;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Post getQuotedPost() {
		return quotedPost;
	}

	public void setQuotedPost(Post quotedPost) {
		this.quotedPost = quotedPost;
	}

	public Long getId() {
		return id;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Thread getThread() {
		return thread;
	}

//	public void setThread(Thread thread) {
//		this.thread = thread;
//	}
	
	/*----------------------------------    Common Methods    ----------------------------------*/

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Post)) {
			return false;
		}
		Post rhs = (Post) object;
		return new EqualsBuilder().append(this.title, rhs.title).append(
				this.messageBody, rhs.messageBody).append(this.creationTime, rhs.creationTime).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1105658581, 497653257).append(this.messageBody)
				.append(this.title).append(this.creationTime).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("title", this.title).append(
				"body", this.messageBody).append("creationTime", this.creationTime)
				.append("modifiedTime", this.modifiedTime).toString();
	}
	
}