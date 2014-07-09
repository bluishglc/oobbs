package oobbs.application.service.user;

import oobbs.application.dto.user.UserAuthenticationAssembler;
import oobbs.domainmodel.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service("userAuthenticationService")
public class UserAuthenticationServiceImpl implements UserAuthenticationService{
	
	private UserRepository userRepository;
	
	private UserAuthenticationAssembler userAuthenticationAssembler;
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/
	
	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		return userAuthenticationAssembler.extractUserAuthenticationData(userRepository.getUserByName(username));
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Autowired
	public void setUserAuthenticationAssembler(UserAuthenticationAssembler userAuthenticationAssembler) {
		this.userAuthenticationAssembler = userAuthenticationAssembler;
	}

}
