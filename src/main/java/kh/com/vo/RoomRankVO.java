package kh.com.vo;
import java.sql.Date;

public class RoomRankVO {
	private int rank;		// 좋아요 순위(ROWNUM)
	private String category;// 카테고리
	private int postId;		// 소개글 아이디(PK)
	private String title;	// 방탈출 제목
	private String content; // 방탈출 소개글
	private String img;		// 소개 사진
	private Date postDate;	// 게시 일자 
	private Date upDate;	// 수정 일자
	private int like; 		// 좋아요 수
	private int view;		// 조회수
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
