package kh.com.servlet.freeboard;

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


import kh.com.dao.FreeBoardDAO;
import kh.com.vo.FreeBoardVO;

@WebServlet("/DetailBoardServlet")
public class BoardDetailServlet extends HttpServlet {
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
			fBoardlist.put("fb_id", e.getFb_id());
			fBoardlist.put("fb_category", e.getFb_category());
			fBoardlist.put("fb_user_id", e.getFb_user_id());
			fBoardlist.put("fb_title", e.getFb_title());
			DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
			String dateToStr1 = dateFormat.format(e.getFb_c_date());
			fBoardlist.put("fb_c_date", dateToStr1);
			fBoardlist.put("fb_hit", e.getFb_hit());
			fBoardlist.put("fb_content", e.getFb_content());
			boardArray.add(fBoardlist);
		}
		System.out.println(boardArray);
		out.print(boardArray);
	}
}