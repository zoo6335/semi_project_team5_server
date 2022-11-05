package kh.com.servlet.freeboard;

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
import kh.com.dao.FreeBoardDAO;
import kh.com.vo.FreeBoardVO;


@WebServlet("/UpdatePullBoardServlet")
public class BoardUpdatePullServlet extends HttpServlet {
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
		
		String reqFb_id = (String)jsonObj.get("fb_id");
		PrintWriter out = response.getWriter();
		int getFb_id = Integer.parseInt(reqFb_id);
		// String -> int로 형변환
		
		FreeBoardDAO dao = new FreeBoardDAO();
		List<FreeBoardVO> list = dao.boardDetail(getFb_id);
		
		JSONArray boardArray = new JSONArray();
		for (FreeBoardVO e : list) {
			JSONObject fBoardlist = new JSONObject();
			fBoardlist.put("fb_category", e.getFb_category());
			fBoardlist.put("fb_title", e.getFb_title());
			fBoardlist.put("fb_content", e.getFb_content());
			boardArray.add(fBoardlist);
		}
		System.out.println(boardArray);
		out.print(boardArray);
	}

}
