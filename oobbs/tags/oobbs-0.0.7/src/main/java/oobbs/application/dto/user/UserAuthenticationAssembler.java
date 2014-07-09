package oobbs.application.dto.user;

import oobbs.domainmodel.user.User;

public class UserAuthenticationAssembler {

	public UserAuthenticationDTO extractUserAuthenticationData(User user){
		UserAuthenticationDTO userAuthenticationData = new UserAuthenticationDTO();
		userAuthenticationData.setUserId(user.getId());
		userAuthenticationData.setUsername(user.getUsername());
		userAuthenticationData.setPassword(user.getPassword());
		userAuthenticationData.setEnabled(user.isEnabled());
		userAuthenticationData.setAccountNonExpired(user.isAccountNonExpired());
		userAuthenticationData.setAccountNonLocked(user.isAccountNonLocked());
		userAuthenticationData.setCredentialsNonExpired(user.isCredentialsNonExpired());
		userAuthenticationData.setAuthorities(user.getAuthorities());
		return userAuthenticationData;
	}
}
