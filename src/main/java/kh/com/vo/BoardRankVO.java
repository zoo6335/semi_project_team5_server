package kh.com.vo;
import java.sql.Date;

public class BoardRankVO {
	private int rank; 		// 조회 순위(ROWNUM)
	private int postId;		// 게시글 아이디(PK)
	private String category;// 게시글 카테고리
	private String Id;		// 회원 아이디
	private String title; 	// 게시글 제목
	private String content; // 게시글 내용
	private Date postDate;	// 게시 일자 
	private Date upDate;	// 수정 일자
	private int like;		// 추천수
	private int view; 		// 조회수
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}	
}
