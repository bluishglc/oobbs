package oobbs.domainmodel.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import oobbs.domainmodel.forum.Post;
import oobbs.domainmodel.forum.Thread;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * This class represents the basic "user" object in AppFuse that allows for authentication
 * and user management.  It implements Acegi Security's UserDetails interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Updated by Dan Kibler (dan@getrolling.com)
 *         Extended to implement Acegi UserDetails interface
 *         by David Carter david@carter.net
 */
@NamedQueries ({
    @NamedQuery(
        name = "findUserByName",
        query = "select u from User u where u.username = :username"
        )
})
@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable, UserDetails {
   
	private static final long serialVersionUID = 3832626162173359411L;
   
	@Id @GeneratedValue
    private Long id;
    
    @Column(nullable=false,length=50,unique=true)    
    private String username;
    
    @Column(nullable=false)
    private String password; 
    
    @Transient
    private String confirmPassword;
    
    @Column(nullable=false,unique=true)
    private String email;
    
    @Version
    private Integer version;
    
    @ManyToMany 
    @JoinTable(name="User_Role",joinColumns = {@JoinColumn(name="userId")},inverseJoinColumns = @JoinColumn( name="roleId"))
    @org.hibernate.annotations.ForeignKey(name="fk_User_Role_userId_User",inverseName="fk_User_Role_roleId_Role")
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles = new HashSet<Role>();
    
    @ManyToMany
    @JoinTable(name="Subscriber_Thread",joinColumns={@JoinColumn(name="subscriberId")},inverseJoinColumns={@JoinColumn(name="threadId")})
    @org.hibernate.annotations.ForeignKey(name="fk_Subscriber_Thread_subscriberId_User",inverseName="fk_Subscriber_Thread_threadId_Thread")
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Thread> subscribedThreads;
    
    @Column
    private boolean enabled;
    
    @Column(nullable=false)    
    private boolean accountNonExpired;

    @Column(nullable=false)
    private boolean accountNonLocked;

    @Column(nullable=false)
    private boolean credentialsNonExpired;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public User() {}

    /**
     * Create a new instance and set the username.
     * @param username login name for user.
     */
    public User(final String username) {
        this.username = username;
    }

    /*----------------------------------    Business Methods    ----------------------------------*/
   
    /**
     * GOOD WAY!
     * User is subscription's sponsor!S/He,however,does not process subscription request!It's
     * ThreadSubscriptionService's work!
     * The model object should simulate real world's running.
     * User can apply subscription not process the application.The process work
     * belong to ThreadSubscriptionService.
     */
    public void subscribeThread(Thread thread){
    	subscribedThreads.add(thread);
    	//The user should be the association's controller. 
    	thread.addSubscriber(this);
    }
    
    /**
     * The same to subscribeThread.
     * @param thread
     * @return
     */
    public void unsubscribeThread(Thread thread){
    	subscribedThreads.remove(thread);
    	thread.removeSubscriber(this);
    }
    
    public void postThread(Thread thread){
    	thread.setAuthor(this);
    	//myThreads.add(thread);
    }
    
    public void postReply(Post message){
    	message.setAuthor(this);
    }
    
    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * @return GrantedAuthority[] an array of roles.
     */
    public Collection<GrantedAuthority> getAuthorities() {
    	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size());
    	for (Role role : roles) {
    		authorities.add(role);
		}
        return authorities;
    }
    
    /**
     * Adds a role for the user
     * @param role the fully instantiated role
     */
    public void addRole(Role role) {
        getRoles().add(role);
    }    
    
    /*----------------------------------    Accessor Methods    ----------------------------------*/
   
    public Long getId() {
        return id;
    }

    public String getUsername() {
		return username;
	}

	public String getPassword() {
        return password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
   
    public Integer getVersion() {
        return version;
    }
    
    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
	public boolean isEnabled() {
		return enabled;
	}

	public void setAccountEnabled(boolean accountEnabled) {
		this.enabled = accountEnabled;
	}
    
    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
   
    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    
    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

	public void setUsername(String username) {
		this.username = username;
	}
	
    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public Set<Thread> getSubscribedThreads() {
		return subscribedThreads;
	}

	public void setSubscribedThreads(Set<Thread> subscribedThreads) {
		this.subscribedThreads = subscribedThreads;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
    
    /*----------------------------------    Common Methods    ----------------------------------*/

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("username", this.username)
                .append("enabled", this.enabled)
                .append("accountExpired", this.accountNonExpired)
                .append("credentialsExpired", this.credentialsNonExpired)
                .append("accountLocked", this.accountNonLocked);

        Collection<GrantedAuthority> auths = this.getAuthorities();
        if (auths != null) {
            sb.append("Granted Authorities: ");
            

//            for (int i = 0; i < auths.size; i++) {
//                if (i > 0) {
//                    sb.append(", ");
//                }
//                sb.append(auths[i].toString());
//            }
        } else {
            sb.append("No Granted Authorities");
        }
        return sb.toString();
    }

}
