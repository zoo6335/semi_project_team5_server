//package kh.com.servlet.comment;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import kh.com.common.Common;
//import kh.com.dao.CommentDAO;
//import kh.com.vo.CommentVO;
//
//@WebServlet("/InsertCommentServlet")
//public class InsertCommentServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    public InsertCommentServlet() {
//        super();
//    }
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
//		    throws ServletException, IOException {
//		response = Common.corsResSet(response);
//	}
//	
//	@SuppressWarnings("unchecked")
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		Common.corsResSet(response);
//		
//		StringBuffer sb = Common.reqStringBuff(request);
//		JSONObject jsonObj = Common.getJsonObj(sb);
//		
//		String reqCmd = (String)jsonObj.get("cmd");
//		String id= (String)jsonObj.get("id");
//		String content= (String)jsonObj.get("content");
//		String boardId= (String)jsonObj.get("boardId");
//
//		CommentDAO dao = new CommentDAO(); 
//		boolean insertCheck = dao.insertComment(id, content, boardId); // 게시판 번호를 받아서 전달
//		
//		PrintWriter out = response.getWriter();
//		JSONObject resJson = new JSONObject();
//		
//		if(!reqCmd.equals("InsertComment")) {
//			resJson.put("result", "Not Ok");
//			out.print(resJson);
//			return;
//		}
//			}
//}