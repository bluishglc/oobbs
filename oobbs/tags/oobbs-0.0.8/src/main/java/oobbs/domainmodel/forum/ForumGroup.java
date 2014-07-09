package oobbs.domainmodel.forum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import oobbs.domainmodel.util.TimeAutoStamped;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ForumGroup implements TimeAutoStamped,Serializable{

	private static final long serialVersionUID = 2833986177050501025L;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(length=255,nullable=false,unique=true)
	private String name;
	
	@Column
	private String description;
	
	//No cascade with forumGroup's forum!
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(mappedBy="group")
	private List<Forum> forums = new ArrayList<Forum>();
	
	@Column(nullable=false,insertable=true,updatable=false)
	private Date creationTime;
	
	@Column(nullable=false,insertable=true,updatable=true)
	private Date modifiedTime;

	public ForumGroup(){}
	
	public ForumGroup(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	/*----------------------------------    Business Methods    ----------------------------------*/

	public Forum createForum(){
		return new Forum(this);		
	}
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public List<Forum> getForums() {
		return forums;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/*----------------------------------    Common Methods    ----------------------------------*/

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ForumGroup)) {
			return false;
		}
		ForumGroup rhs = (ForumGroup) object;
		return new EqualsBuilder().append(this.name, rhs.name).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-995429911, 1152987405).append(this.name).toHashCode();
	}	
}