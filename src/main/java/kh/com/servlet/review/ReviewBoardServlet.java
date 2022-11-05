package kh.com.servlet.review;

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
import kh.com.vo.ReviewBoardVO;

import kh.com.dao.ReviewBoardDAO;

@WebServlet("/ReviewBoardServlet")
public class ReviewBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	// CORS 처리
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Common.corsResSet(response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Common.corsResSet(response);
		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		String reqCmd = (String)jsonObj.get("cmd");
		PrintWriter out = response.getWriter();
		
		if(!reqCmd.equals("RBoardList")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "NOK");
			out.print(resJson);
			return;
		} 
		
		ReviewBoardDAO dao = new ReviewBoardDAO();
		List<ReviewBoardVO> list = dao.BoardSelect();
		
		JSONArray boardArray = new JSONArray();
		for (ReviewBoardVO e : list) {
			JSONObject boardList = new JSONObject();
			
			boardList.put("title", e.getTitle());
			boardList.put("content", e.getContent());
			boardArray.add(boardList);
		}
		System.out.println(boardArray);
		out.print(boardArray);
	}
}