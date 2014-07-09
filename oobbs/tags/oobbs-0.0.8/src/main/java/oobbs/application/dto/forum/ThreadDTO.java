package oobbs.application.dto.forum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ThreadDTO implements Serializable {

	private static final long serialVersionUID = -4478538998147448878L;
	
	private Long id;
	
	private String title;
	
	private List<InnerPostDTO> posts;
	
	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<InnerPostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<InnerPostDTO> posts) {
		this.posts = posts;
	}

	/*----------------------------------      Inner Classes     ----------------------------------*/

	public static class InnerPostDTO implements Serializable {
		
		private static final long serialVersionUID = 790057737477897602L;

		private Long id;

		private String title;

		private String messageBody;
		
		private String author;
		
		private Date creationTime;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

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

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public Date getCreationTime() {
			return creationTime;
		}

		public void setCreationTime(Date creationTime) {
			this.creationTime = creationTime;
		}
		
	}

}
