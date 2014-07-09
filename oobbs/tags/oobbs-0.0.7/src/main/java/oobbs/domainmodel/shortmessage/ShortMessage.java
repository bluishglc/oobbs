package oobbs.domainmodel.shortmessage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import oobbs.domainmodel.user.User;


public class ShortMessage implements Serializable{

	private static final long serialVersionUID = -8475783970799541835L;
	
	private Long id;
	private String title;
	private String body;
	private User sender;
	private List<User> receiptants;
	private Date sendTime;
	
	public ShortMessage(){}
	
	public ShortMessage(String title, String body, User sender,
			List<User> receiptants) {
		super();
		this.title = title;
		this.body = body;
		this.sender = sender;
		this.receiptants = receiptants;
	}
	
//	public static ShortMessage createShortMessage(String title, String body, User sender,
//			List<User> receiptants) {
//		return new ShortMessage(title,body,sender,receiptants);
//	}
//	
//	public static ShortMessage createSystemShortMessage(String title, String body,List<User> receiptants) {
//		return new ShortMessage(title,body,null,receiptants);
//	}
//	
//	public static ShortMessage createSystemBroadcastShortMessage(String title, String body){
//		return new ShortMessage(title,body,null,null);
//	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public List<User> getReceiptants() {
		return receiptants;
	}
	public void setReceiptants(List<User> receiptants) {
		this.receiptants = receiptants;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public Long getId() {
		return id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return title+":"+body;
	}	
}
