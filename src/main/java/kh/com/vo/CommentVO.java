package kh.com.vo;

import java.sql.Date;

public class CommentVO {
	private String id;		// 유저id
	private int postId;		// 댓글 번호
	private String content;	// 댓글 내용
	private Date postDate;	// 댓글 작성일
	private Date upDate;	// 댓글 수정일
	private int boardId;	// (댓글이 달린)게시물 아이디
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
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

}