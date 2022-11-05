package kh.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kh.com.common.Common;
import kh.com.vo.BoardVO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
@WebServlet("/BoardDAO")
public class BoardDAO extends HttpServlet {
	private Connection conn = null;
	private Statement stmt = null; //표준 SQL문을 수행하기 위한 Statement 객체 얻기
	private ResultSet rs = null; // Statement의 수행 결과를 여러행으로 받음
	// SQL문을 미리 컴파일해서 재 사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
	private PreparedStatement pstmt = null;
	
	
	// 게시판 내용 불러오기
	public List<BoardVO> BoardSelect() {
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM GET_MEMBER_BOARD ORDER BY GMB_ID DESC";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int gmb_id = rs.getInt("GMB_ID");
				String gmb_user_id = rs.getString("GMB_USER_ID");
				String gmb_title = rs.getString("GMB_TITLE");
				String gmb_content = rs.getString("GMB_CONTENT");
				Date gmb_c_date = rs.getDate("GMB_C_DATE");
				Date gmb_u_date = rs.getDate("GMB_U_DATE");
				int gmb_recommend = rs.getInt("GMB_RECOMMEND");
				String gmb_done = rs.getString("GMB_DONE");
				int gmb_apply = rs.getInt("GMB_APPLY");
				int gmb_hit = rs.getInt("GMB_HIT");
				
				BoardVO vo = new BoardVO();
				vo.setGmb_id(gmb_id);
				vo.setGmb_title(gmb_title);
				vo.setGmb_content(gmb_content);
				vo.setGmb_user_id(gmb_user_id);
				vo.setGmb_c_date(gmb_c_date);
				vo.setGmb_u_date(gmb_u_date);
				vo.setGmb_recommend(gmb_recommend);
				vo.setGmb_done(gmb_done);
				vo.setGmb_apply(gmb_apply);
				vo.setGmb_hit(gmb_hit);
				list.add(vo);
			}
			Common.close(rs);
			Common.close(stmt);
			Common.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
//	게시판 새글 작성하기
	public boolean WriteBoard(String getUserId, String getTitle, String getContent) {
		int result = 0;
		String sql = "INSERT INTO GET_MEMBER_BOARD(GMB_ID, GMB_USER_ID, GMB_TITLE, GMB_CONTENT, GMB_C_DATE, GMB_U_DATE, GMB_RECOMMEND, GMB_DONE, GMB_APPLY, GMB_HIT)"
				+ " VALUES(GMB_ID_SEQ.NEXTVAL, ? , ? , ? , DEFAULT, NULL, 1, 0, DEFAULT, 0)";
		try {
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getUserId);
			pstmt.setString(2, getTitle);
			pstmt.setString(3, getContent);
			result = pstmt.executeUpdate();	
			System.out.println("게시물 작성 DB 결과 확인 : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Common.close(pstmt);
		Common.close(conn);
		
		if(result == 1) return true;
		else return false;
}

//	게시판 상세내용 불러오기
	public List<BoardVO> DetailBoard(int intId) {
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM GET_MEMBER_BOARD WHERE GMB_ID = " + intId ;
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int gmb_id = rs.getInt("GMB_ID");
				String gmb_user_id = rs.getString("GMB_USER_ID");
				String gmb_title = rs.getString("GMB_TITLE");
				String gmb_content = rs.getString("GMB_CONTENT");
				Date gmb_c_date = rs.getDate("GMB_C_DATE");
				Date gmb_u_date = rs.getDate("GMB_U_DATE");
				int gmb_recommend = rs.getInt("GMB_RECOMMEND");
				String gmb_done = rs.getString("GMB_DONE");
				int gmb_apply = rs.getInt("GMB_APPLY");
				int gmb_hit = rs.getInt("GMB_HIT");
				
				BoardVO vo = new BoardVO();
				vo.setGmb_id(gmb_id);
				vo.setGmb_title(gmb_title);
				vo.setGmb_content(gmb_content);
				vo.setGmb_user_id(gmb_user_id);
				vo.setGmb_c_date(gmb_c_date);
				vo.setGmb_u_date(gmb_u_date);
				vo.setGmb_recommend(gmb_recommend);
				vo.setGmb_done(gmb_done);
				vo.setGmb_apply(gmb_apply);
				vo.setGmb_hit(gmb_hit);
				list.add(vo);
			}
			Common.close(rs);
			Common.close(stmt);
			Common.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//게시물 수정
	public boolean boardUpdate(int intId, String gmb_title, String gmb_content) {
		int result = 0;
		String sql = "UPDATE GET_MEMBER_BOARD SET GMB_TITLE = ? , GMB_CONTENT = ? , GMB_U_DATE = SYSDATE WHERE GMB_ID = " + intId;
		
		try {
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gmb_title);
			pstmt.setString(2, gmb_content);
			
			result = pstmt.executeUpdate();	
			System.out.println("게시물 작성 DB 결과 확인 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Common.close(pstmt);
		Common.close(conn);
		if(result == 1) return true;
		else return false;
	}

	
//게시물 삭제 
	public boolean boardDelete(int intId) {
		int result = 0;
		String sql = "DELETE FROM GET_MEMBER_BOARD WHERE GMB_ID = ?";
		
		try {
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, intId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Common.close(pstmt);
		Common.close(conn);
		if(result == 1) return true;
		else return false;
	}
	

}


	
       
    