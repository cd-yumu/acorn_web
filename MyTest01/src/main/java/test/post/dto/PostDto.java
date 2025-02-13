package test.post.dto;

public class PostDto {
	// fields
	private long num;			// 글 번호
	private String writer;		// 글 작성자
	private String title;		// 글 제목
	private String content;		// 글 내용
	private int viewCount;		// 글 조회수
	private String createdAt;	// 글 생성일
	private String updatedAt;	// 글 수정일
	
	private int startRowNum;	// 한 페이지에 보여줄 글의 시작 row 번호
	private int endRowNum;		// 한 페이지에 보여줄 글의 끝 row 번호
	
	private String condition; 	//검색 조건  writer 또는 title 또는 title+content 
	private String keyword; 	//검색 키워드
	
	private long prevNum; 		//이전글의 글번호
	private long nextNum; 		//다음글의 글번호 
	
	
	// getter , setter
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
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
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public long getPrevNum() {
		return prevNum;
	}
	public void setPrevNum(long prevNum) {
		this.prevNum = prevNum;
	}
	public long getNextNum() {
		return nextNum;
	}
	public void setNextNum(long nextNum) {
		this.nextNum = nextNum;
	}
