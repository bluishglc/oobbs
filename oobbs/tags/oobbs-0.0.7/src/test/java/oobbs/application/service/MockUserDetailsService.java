package oobbs.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import oobbs.domainmodel.user.User;

import org.springframework.dao.DataAccessException;

public class MockUserDetailsService implements UserDetailsService {
    public UserDetails getUserByName(String username) throws UsernameNotFoundException, DataAccessException {
        return new User("testuser");
    }

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
