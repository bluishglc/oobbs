package oobbs.application.dto.forum;

import java.io.Serializable;
import java.util.List;

public class ForumDTO implements Serializable {

	private static final long serialVersionUID = -3618788327229319747L;
	
	private Long id;
	
	private String name;

	private String description;
	
	private List<InnerThreadDTO> threads;

	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<InnerThreadDTO> getThreads() {
		return threads;
	}

	public void setThreads(List<InnerThreadDTO> threads) {
		this.threads = threads;
	}
	
	/*----------------------------------      Inner Classes     ----------------------------------*/

	public static class InnerThreadDTO implements Serializable {
		
		private static final long serialVersionUID = -4223885490654307827L;
		
		private Long id;
		
		private String title;
		
		private String author;
		
		private long replyCount;
		
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
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public long getReplyCount() {
			return replyCount;
		}
		public void setReplyCount(long replyCount) {
			this.replyCount = replyCount;
		}
		
	}
	
}
