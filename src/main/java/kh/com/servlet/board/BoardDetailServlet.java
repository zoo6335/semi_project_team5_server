
package kh.com.servlet.board;

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
import kh.com.dao.BoardDAO;
import kh.com.vo.BoardVO;

// 게시판 내용 상세보기
@WebServlet("/BoardDetailServlet")
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

	@SuppressWarnings({ "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Common.corsResSet(response);
		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		PrintWriter out = response.getWriter();
		
		String getGmb_id = (String)jsonObj.get("gmb_id");
		// JSON 데이터는 String 이므로 -> 원하는 Int 형으로 변환
		int intId = Integer.parseInt(getGmb_id);
		System.out.println("전달 받은 ID : " + intId);
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.DetailBoard(intId);
		
		JSONArray boardArray = new JSONArray();
		for (BoardVO e : list) {
			JSONObject boardList = new JSONObject();
			boardList.put("gmb_id", e.getGmb_id());
			boardList.put("gmb_title", e.getGmb_title());
			boardList.put("gmb_user_id", e.getGmb_user_id());
			boardList.put("gmb_content", e.getGmb_content());
			DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
			String dateToStr = dateFormat.format(e.getGmb_c_date());
			boardList.put("gmb_c_date", dateToStr);
			boardList.put("gmb_apply", e.getGmb_apply());
			boardList.put("gmb_done", e.getGmb_done());
			boardList.put("gmb_apply_total", e.getGmb_apply_total());
			boardList.put("gmb_hit", e.getGmb_hit());
			boardArray.add(boardList);
		}
		System.out.println(boardArray);
		out.print(boardArray);
	}
}
