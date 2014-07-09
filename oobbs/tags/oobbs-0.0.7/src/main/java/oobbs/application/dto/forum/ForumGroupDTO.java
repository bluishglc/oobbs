package oobbs.application.dto.forum;

import java.io.Serializable;
import java.util.List;

public class ForumGroupDTO implements Serializable {

	private static final long serialVersionUID = -1436805632683857529L;

	private Long id;

	private String name;

	private String description;

	private List<InnerForumDTO> forums;

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

	public List<InnerForumDTO> getForums() {
		return forums;
	}

	public void setForums(List<InnerForumDTO> forums) {
		this.forums = forums;
	}

	/*----------------------------------      Inner Classes     ----------------------------------*/

	public static class InnerForumDTO implements Serializable {

		private static final long serialVersionUID = 7564838188662669901L;

		private Long id;

		private String name;

		private String description;

		private long threadCount;

		private long postCount;
		
		private String latestPostTitle;
		
		private String latestPostAuthor;
		
		private String latestPostCreationTime;

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

		public long getThreadCount() {
			return threadCount;
		}

		public void setThreadCount(long threadCount) {
			this.threadCount = threadCount;
		}

		public long getPostCount() {
			return postCount;
		}

		public void setPostCount(long postCount) {
			this.postCount = postCount;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getLatestPostTitle() {
			return latestPostTitle;
		}

		public void setLatestPostTitle(String latestPostTitle) {
			this.latestPostTitle = latestPostTitle;
		}

		public String getLatestPostAuthor() {
			return latestPostAuthor;
		}

		public void setLatestPostAuthor(String latestPostAuthor) {
			this.latestPostAuthor = latestPostAuthor;
		}

		public String getLatestPostCreationTime() {
			return latestPostCreationTime;
		}

		public void setLatestPostCreationTime(String latestPostCreationTime) {
			this.latestPostCreationTime = latestPostCreationTime;
		}

	}
}
