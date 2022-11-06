package kh.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kh.com.common.Common;
import kh.com.vo.CommentVO;

public class CommentDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null; 
	private PreparedStatement pstmt = null;
	
	// 게시글 번호와 일치하는 댓글 조회
	public List<CommentVO> commentList(String reqId) { 
		int intId = Integer.parseInt(reqId);
		List<CommentVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql= "SELECT * FROM B_COMMENT WHERE COMMENT_BOARD_ID = " + intId ; // reqId 는 댓글이 달린 게시물의 번호를 뜻함.
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String id = rs.getString("COMMENT_USER_ID");
				int boardId = rs.getInt("COMMENT_BOARD_ID");
				int postId= rs.getInt("COMMENT_ID");
				String content = rs.getString("COMMENT_CONTENT");
				Date postDate = rs.getDate("COMMENT_C_DATE");
				
				CommentVO vo = new CommentVO();
				vo.setId(id);
				vo.setPostId(postId);
				vo.setContent(content);
				vo.setBoardId(boardId);
				vo.setPostDate(postDate);				
				list.add(vo);		
			}
			Common.close(rs);
			Common.close(stmt);
			Common.close(conn);
			 
		} catch(Exception e) {
				e.printStackTrace();
		}
		return list;
	}
	// 댓글 작성하기
	public boolean insertComment(String id, String content, String boardId) { 
		int intId = Integer.parseInt(boardId); // boardId 는 int 이지만 String으로 들어오기 때문에 형변환
		int result = 0;
		String sql= "INSERT INTO B_COMMENT VALUES(?, CM_ID_SEQ.NEXTVAL, ?, DEFAULT, NULL, ?) " ;
		try {			
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,  content);
			pstmt.setInt(3,  intId);
			result = pstmt.executeUpdate();
			System.out.println("댓글 작성 결과 확인" + result);
			 
		} catch(Exception e) {
				e.printStackTrace();
		}
		Common.close(rs);
		Common.close(pstmt);
		Common.close(conn);

		if(result == 1) return true;
		else return false;
	}

	
}