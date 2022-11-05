package kh.com.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kh.com.common.Common;
import kh.com.vo.BoardRankVO;

public class BoardRankingDAO {
	private Connection conn = null;
	private Statement stmt = null; 
	private ResultSet rs = null; 
	public List<BoardRankVO> boardSelect() { 
		List<BoardRankVO> list = new ArrayList<>(); 
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql= "SELECT ROWNUM, FB_CATEGORY, FB_TITLE, FB_HIT, FB_ID "
					+ "FROM (SELECT * FROM FREE_BOARD ORDER BY FB_HIT DESC) "
					+ "WHERE ROWNUM <= 5"; 
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int rank = rs.getInt("ROWNUM");
				String category = rs.getString("FB_CATEGORY");
				String title = rs.getNString("FB_TITLE");
				int view = rs.getInt("FB_HIT");
				int postId = rs.getInt("FB_ID");
				
				BoardRankVO vo = new BoardRankVO();
				vo.setRank(rank);
				vo.setCategory(category);
				vo.setTitle(title);
				vo.setView(view);
				vo.setPostId(postId);
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