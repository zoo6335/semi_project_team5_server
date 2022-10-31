package kh.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kh.com.common.Common;
import kh.com.vo.MemberVO;

public class MemberDAO {

	private Connection conn = null; // 연결하기
	private Statement stmt = null; // 표준 SQL 문을 수행하기 위한 Statement 객체 얻기
	private ResultSet rs = null; // Statement 의 수행 결과를 여러행으로 받음
	//SQL문을 미리 컴파일해서 재 사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
	private PreparedStatement pstmt = null;
	
	public boolean loginCheck(String user_id, String pwd) {
		boolean isReg = false;
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement(); // Statement 객체를 얻어냄
			String sql = "SELECT * from B_MEMBER WHERE USER_ID = " + "'" + user_id + "'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String sqlId = rs.getString("USER_ID");
				String sqlPwd = rs.getString("PWD");
				System.out.println("ID  : " + sqlId);
				System.out.println("password : " + sqlPwd);
				if(user_id.equals(sqlId) && pwd.equals(sqlPwd)) {
					Common.close(rs);
					Common.close(stmt);
					Common.close(conn);
					return true;
				}
			} 
			
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public boolean regIdCheck(String user_id) {
		boolean isNotReg = false;
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM B_MEMBER WHERE USER_ID = " + "'" + user_id + "'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) isNotReg = false;
			else isNotReg = true;
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		Common.close(rs);
		Common.close(stmt);
		Common.close(conn);
		return isNotReg;
	}
	
	
	public List<MemberVO> memberSelect(String reqId) {
		List<MemberVO> list = new ArrayList<>();
		try {
			conn = Common.getConnection();
			stmt = conn.createStatement();
			String sql = null;
			if(reqId.equals("ALL")) sql = "SELECT * FROM B_MEMBER";
			else sql = "SELECT * FROM B_MEMBER WHREE ID = " + "'" + reqId + "'";
			
			rs = stmt.executeQuery(sql);
			
			// 로그인 과는 다르게 rs.next에는 회원정보를 전부 긁어오기 때문에 next에는 true 다음 바로 false가 아닌 값이 들어온다.
			while(rs.next()) {
				
				String id = rs.getString("USER_ID");
				String pwd = rs.getString("PWD");
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				Date join = rs.getDate("JOIN");
				
				MemberVO vo = new MemberVO();
				vo.setUser_id(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoin(join);
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
	
	public boolean memberRegister(String user_id, String pwd, String name, String email) {
		int result = 0;
		
		String sql = "INSERT INTO B_MEMBER(USER_ID, PWD, NAME, EMAIL, JOIN) VALUES(?,?,?,?, SYSDATE)";
		
		
		try {
			conn = Common.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
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
	
}
