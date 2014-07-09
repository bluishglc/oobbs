package oobbs.application.dto.forum;

import java.io.Serializable;

import oobbs.domainmodel.user.User;

public class ReplyCreationDTO implements Serializable {

	private static final long serialVersionUID = 8567951751535511889L;
	
//	private Long threadId;
	
	private String title;
	
	private String messageBody;
	
//	private User author;

//	public Long getThreadId() {
//		return threadId;
//	}
//
//	public void setThreadId(Long threadId) {
//		this.threadId = threadId;
//	}

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
