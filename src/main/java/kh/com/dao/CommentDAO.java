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
	
	public List<CommentVO> commentList() { 
		List<CommentVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql= "SELECT * FROM B_COMMENT ";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String id = rs.getString("COMMENT_USER_ID");
				int postId= rs.getInt("COMMENT_ID");
				String content = rs.getString("COMMENT_CONTENT");
				Date postDate = rs.getDate("COMMENT_C_DATE");
				Date upDate = rs.getDate("COMMENT_U_DATE");
				
				CommentVO vo = new CommentVO();
				vo.setId(id);
				vo.setPostId(postId);
				vo.setContent(content);
				vo.setPostDate(postDate);
				vo.setUpDate(upDate);
				
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