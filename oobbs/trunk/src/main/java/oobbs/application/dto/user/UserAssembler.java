package oobbs.application.dto.user;

import oobbs.domainmodel.user.User;

import org.springframework.stereotype.Component;
@Component
public class UserAssembler {
	
	public UserDTO extractUserData(User user){
		UserDTO userData = new UserDTO();
		userData.setId(user.getId());
		userData.setUsername(user.getUsername());
		userData.setPassword(user.getPassword());
		userData.setConfirmPassword(user.getConfirmPassword());
		return userData;
	}	
}
