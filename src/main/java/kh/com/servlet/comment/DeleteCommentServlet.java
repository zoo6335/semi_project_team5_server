package kh.com.servlet.comment;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import kh.com.common.Common;
import kh.com.dao.CommentDAO;

@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteCommentServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		response = Common.corsResSet(response);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Common.corsResSet(response);
		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		System.out.println(jsonObj);
		
		String postId= (String) jsonObj.get("postId"); // 사용자 ID
		System.out.println("댓글 번호 : " + postId);
		
		CommentDAO dao = new CommentDAO();
		boolean rstComplete = dao.deleteComment(postId); // 게시판 번호를 받아서 전달
		System.out.println("댓글 삭제 결과 : " + rstComplete);
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
		return;
	}		
}