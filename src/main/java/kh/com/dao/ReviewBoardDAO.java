package kh.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import kh.com.common.Common;
import kh.com.vo.ReviewBoardVO;


@SuppressWarnings("serial")
public class ReviewBoardDAO extends HttpServlet {
	private Connection conn = null;
	private Statement stmt = null; //표준 SQL문을 수행하기 위한 Statement 객체 얻기
	private ResultSet rs = null; // Statement의 수행 결과를 여러행으로 받음
	// SQL문을 미리 컴파일해서 재 사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
	private PreparedStatement pstmt = null;


public List<ReviewBoardVO> BoardSelect() {
	List<ReviewBoardVO> list = new ArrayList<>();
	
	try {
		conn = Common.getConnection();
		stmt = conn.createStatement();
		String sql = "SELECT * FROM REVIEW";
		rs = stmt.executeQuery(sql);
	
	while(rs.next()) {
		String title = rs.getString("RV_TITLE");
		String content = rs.getString("RV_CONTENT");
		

		ReviewBoardVO vo = new ReviewBoardVO();		
		vo.setTitle(title);
		vo.setContent(content);
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

public boolean WriteBoard(String title,String content) {
	int result = 0;
	String sql = "INSERT INTO REVIEW(RV_TITLE, RV_CONTENT) VALUES(?, ?)";
	try {
		conn = Common.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
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

public ReviewBoardVO DetailBoard(String id) {
	ReviewBoardVO vo = new ReviewBoardVO();
	
	try {
		conn = Common.getConnection();
		stmt = conn.createStatement();
		String sql = "SELECT ID, RV_TITLE, RV_CONTENT, WRITEDATE, COMMENT_NUM FROM REVIEW WHERE ID = " + "'" + id + "'";
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			
			String title = rs.getString("RV_TITLE");
			String content = rs.getString("RV_CONTENT");
			
		
			vo.setTitle(title);
			vo.setContent(content);
			
		}
		Common.close(rs);
		Common.close(stmt);
		Common.close(conn);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return vo;
}




















}













