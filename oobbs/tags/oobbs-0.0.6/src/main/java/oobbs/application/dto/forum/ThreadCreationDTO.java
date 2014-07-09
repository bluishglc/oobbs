package oobbs.application.dto.forum;

import java.io.Serializable;

import oobbs.domainmodel.user.User;


/**
 * There no subjectDto in ThreadDto, that's because the DTO is coarse grain!
 * @author Laurence Geng
 *
 */
public class ThreadCreationDTO implements Serializable {

	private static final long serialVersionUID = -8823813305288778226L;
	
	private String title;
	
	private String messageBody;
	
//	private User author;
	
	public String getTitle() {
		return title;
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
	
//	public User getAuthor() {
//		return author;
//	}
//	
//	public void setAuthor(User author) {
//		this.author = author;
//	}

}
