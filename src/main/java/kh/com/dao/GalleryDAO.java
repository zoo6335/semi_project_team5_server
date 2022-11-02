package kh.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kh.com.common.Common;
import kh.com.vo.GalleryVO;

public class GalleryDAO {

	private Connection conn = null; // 연결하기
	private Statement stmt = null; // 표준 SQL 문을 수행하기 위한 Statement 객체 얻기
	private ResultSet rs = null; // Statement 의 수행 결과를 여러행으로 받음
	//SQL문을 미리 컴파일해서 재 사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
	private PreparedStatement pstmt = null;
	
	
	public List<GalleryVO> gallerySelect(String reqId) {
		List<GalleryVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql = null;
			
			if(reqId.equals("ALL")) sql = "SELECT * FROM GALLERY";
			else {
				int intId = Integer.parseInt(reqId);
				sql = "SELECT * FROM GALLERY WHERE GAL_ID = " +  intId;
			}
			
			rs = stmt.executeQuery(sql);
			
			// 로그인 과는 다르게 rs.next에는 회원정보를 전부 긁어오기 때문에 next에는 true 다음 바로 false가 아닌 값이 들어온다.
			while(rs.next()) {
				
				int gal_id = rs.getInt("GAL_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String image_url = rs.getString("IMAGE_URL");
				Date create_date = rs.getDate("CREATE_DATE");
				Date update_date = rs.getDate("UPDATE_DATE");

				
				GalleryVO vo = new GalleryVO();
				vo.setGal_id(gal_id);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setImage_url(image_url);
				vo.setCreate_date(create_date);
				vo.setUpdate_date(update_date);
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
	
	public boolean galleryRegister( String title, String content, String image_url) {
		int result = 0;
		
		String sql = "INSERT INTO GALLERY(GAL_ID, TITLE, CONTENT, IMAGE_URL, CREATE_DATE, UPDATE_DATE) VALUES(GALLERY_SEQUENCE.NEXTVAL,?,?,?, SYSDATE, SYSDATE)";
		
		
		try {
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, image_url);
			result = pstmt.executeUpdate(); // SELECT문은 executeQuery, executeUpdate는 INSERT, UPDATE, DELETE 일 떄!
			System.out.println("갤러리 글쓰기 DB 결과 확인 : " + result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Common.close(rs);
		Common.close(pstmt);
		Common.close(conn);
		
		if(result == 1) return true;
		else return false;
	}
	
	public boolean memberDelete(String user_id) {
		int result = 0;
		
		String sql = "DELETE FROM B_MEMBER WHERE USER_ID = ?";
		try {
			
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user_id);
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Common.close(rs);
		Common.close(pstmt);
		Common.close(conn);

		if(result == 1) return true;
		else return false;
	}
	
	public boolean memberUpdate(String user_id, String pwd, String name, String email) {
		int result = 0;
		
		String sql = "UPDATE B_MEMBER SET PWD = ?, NAME = ? , EMAIL = ? WHERE USER_ID = ?";
		
		
		try {
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, user_id);
			result = pstmt.executeUpdate(); // SELECT문은 executeQuery, executeUpdate는 INSERT, UPDATE, DELETE 일 떄!
			System.out.println("회원 가입 DB 결과 확인 : " + result);
			
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
