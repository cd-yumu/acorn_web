package dto;

public class CommentDto {
	private int num;
	private int parentNum;
	private int postNum;
	private String targetWriter;
	private String writer;
	private String content;
	private String deleted;
	private String createdAt;
	private String updatedAt;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getParentNum() {
		return parentNum;
	}
	public void setParentNum(int parentNum) {
		this.parentNum = parentNum;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getTargetWriter() {
		return targetWriter;
	}
	public void setTargetWriter(String targetWriter) {
		this.targetWriter = targetWriter;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
