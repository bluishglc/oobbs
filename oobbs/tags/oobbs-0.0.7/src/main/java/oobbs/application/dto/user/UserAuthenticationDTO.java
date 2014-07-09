package oobbs.application.dto.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthenticationDTO implements UserDetails {
	
	private static final long serialVersionUID = -6253941982567186560L;
	
	private Long userId;

	private String username;

    private String password; 
	
    private boolean enabled;
    
    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;
    
    private Collection<GrantedAuthority> authorities;
    
    /*---------------------------------    Main Logic Methods    ---------------------------------*/
    
	/**
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
	public String getUsername() {
		return username;
	}
	
	/**
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
	public String getPassword() {
		return password;
	}

	/**
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
	public boolean isEnabled() {
		return enabled;
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

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}   

}
