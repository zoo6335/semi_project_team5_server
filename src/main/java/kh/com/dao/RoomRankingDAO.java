package kh.com.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kh.com.common.Common;
import kh.com.vo.RoomRankVO;

public class RoomRankingDAO {
	private Connection conn = null;
	private Statement stmt = null; //표준 SQL문을 수행하기 위한 Statement 객체 얻기
	private ResultSet rs = null; // Statement의 수행 결과를 여러행으로 받음
	
	public List<RoomRankVO> roomSelect() {
		List<RoomRankVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql= "SELECT ROWNUM, INT_RECOMMEND, INT_TITLE, CATEGORY, INT_BOARD_ID "	// 대소문자 구분이 vsCode랑 같아야해!!!!!
					+ "FROM (SELECT * FROM INTRODUCE ORDER BY INT_RECOMMEND DESC) "
					+ "WHERE ROWNUM <= 5";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int rank = rs.getInt("ROWNUM");				
				int postId = rs.getInt("INT_BOARD_ID");
				String category = rs.getString("CATEGORY");
				String title = rs.getString("INT_TITLE");
				int like = rs.getInt("INT_RECOMMEND");
				
				RoomRankVO vo = new RoomRankVO();
				vo.setRank(rank);
				vo.setPostId(postId);
				vo.setCategory(category);
				vo.setTitle(title);
				vo.setLike(like);
				list.add(vo);				
			}
			Common.close(rs);
			Common.close(stmt);
			Common.close(conn);
			
		} catch(Exception e) {
				e.printStackTrace(); // 호출된 스텍을 다 출력해라
		}
		return list;
	}
}