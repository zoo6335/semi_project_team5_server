package kh.com.servlet.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import kh.com.common.Common;
import kh.com.dao.CommentDAO;
import kh.com.vo.CommentVO;

@WebServlet("/InsertCommentServlet")
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertCommentServlet() {
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
		
		String id= (String)jsonObj.get("id"); // 사용자 ID
		String content= (String)jsonObj.get("content"); // 댓글 내용
		String boardId= (String)jsonObj.get("boardId"); // 자유게시판 게시글 ID
		
		System.out.println(id);
		System.out.println(content);
		System.out.println(boardId);

		CommentDAO dao = new CommentDAO(); 
		boolean rstComplete = dao.insertComment(id, content, boardId); // 게시판 번호를 받아서 전달
		System.out.println("확인 결과 !! : " + rstComplete);
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
		return;
	}		
}