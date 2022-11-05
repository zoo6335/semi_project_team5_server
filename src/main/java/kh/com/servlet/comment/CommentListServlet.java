package kh.com.servlet.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@WebServlet("/CommentListServlet")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CommentListServlet() {
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
		String reqCmd = (String)jsonObj.get("cmd");
		String boardId= (String)jsonObj.get("id");
		PrintWriter out = response.getWriter();
		
		if(!reqCmd.equals("CommentList")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "Not Ok");
			out.print(resJson);
			return;
		}
		CommentDAO dao = new CommentDAO(); 
		List<CommentVO> list = dao.commentList(boardId); // 게시판 번호를 받아서 전달
		
		JSONArray commentArray = new JSONArray();
		for(CommentVO e : list) {
			JSONObject commentList = new JSONObject();
			commentList.put("id", e.getId());
			commentList.put("postId", e.getPostId());
			commentList.put("content", e.getContent());
			commentList.put("boardId", e.getBoardId());
			
			DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
			String dateToStr = dateFormat.format(e.getPostDate());
			commentList.put("postDate", dateToStr); 
			
//			String dateToStr2 = dateFormat.format(e.getUpDate());
//			commentList.put("upDate", dateToStr2);
			commentArray.add(commentList);
		}
		System.out.println(commentArray);
		out.print(commentArray);
	}
}
