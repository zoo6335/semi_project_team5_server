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
//				Date upDate = rs.getDate("COMMENT_U_DATE");
				
				CommentVO vo = new CommentVO();
				vo.setId(id);
				vo.setPostId(postId);
				vo.setContent(content);
				vo.setBoardId(boardId);
				vo.setPostDate(postDate);
//				vo.setUpDate(upDate);
				
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

	
}