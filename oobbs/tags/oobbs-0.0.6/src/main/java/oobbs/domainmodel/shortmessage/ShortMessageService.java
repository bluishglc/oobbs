package oobbs.domainmodel.shortmessage;

import java.util.List;

import oobbs.domainmodel.user.User;
import oobbs.domainmodel.user.UserRepository;

public class ShortMessageService {	
	
//	private UserRepository userRepository;
	
//	private ShortMessageRepository shortMessageRepository;
	
	public void sendShortMessage(ShortMessage shortMessage){
		System.out.println("Short Message:"+shortMessage.toString());
//		List<User>receiptants = shortMessage.getReceiptants();
//		for (User receiptant : receiptants) {
//			System.out.println(receiptant.getUsername()+":"+shortMessage.toString());
//		}		
	}

	public void markAsRead(User user,ShortMessage shorMessage){
		
	}

	public ShortMessage createShortMessage(String title, String body, User sender,
			List<User> receiptants) {
		return new ShortMessage(title,body,sender,receiptants);
	}
	
	public ShortMessage createSystemShortMessage(String title, String body,List<User> receiptants) {
		return new ShortMessage(title,body,new User(),receiptants);
	}
//	
//	public ShortMessage createSystemBroadcastShortMessage(String title, String body){
//		return new ShortMessage(title,body,userRepository.getAdministrator(),userRepository.getAll());
//	}
//
//	public void setUserRepository(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	public void setShortMessageRepository(
//			ShortMessageRepository shortMessageRepository) {
//		this.shortMessageRepository = shortMessageRepository;
//	}
	
}
