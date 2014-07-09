package oobbs.application.dto.forum;

import java.io.Serializable;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = -259327462993165297L;
	
//	private Long id;

	private String title;
	
	private String messageBody;
	
	private Long authorId;

	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
}
