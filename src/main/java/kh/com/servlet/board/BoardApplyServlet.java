package kh.com.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kh.com.common.Common;
import kh.com.dao.BoardDAO;
import kh.com.vo.BoardVO;


@WebServlet("/BoardApplyServlet")
public class BoardApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Common.corsResSet(response);
	}

@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Common.corsResSet(response);
		StringBuffer sb = Common.reqStringBuff(request); // request를 reqStringBuff에 담아서 "문자열"로 변환한 다음 sb에 담는다. -> front와 back은 json으로 통신
		JSONObject jsonObj = Common.getJsonObj(sb); // 문자열로 변환한 sb를 JsonObj으로 만들어서 담는다.  -> 왜냐면 통신은 json으로 이루어지기 떄문에
		
		String getGmb_id = String.valueOf(jsonObj.get("gmb_id"));
		// JSON 데이터는 String 이므로 -> 원하는 Int 형으로 변환
		int intId = Integer.parseInt(getGmb_id);
		System.out.println("전달 받은 ID : " + intId);
		String getGmb_apply = String.valueOf(jsonObj.get("gmb_apply"));
		int intGmb_apply = Integer.parseInt(getGmb_apply);
	
		BoardDAO dao = new BoardDAO();
		boolean rstComplete = dao.boardApply(intId, intGmb_apply);
		
		if(rstComplete == true) {
			List<BoardVO> list = dao.DetailBoard(intId);
			
			if(list.get(0).getGmb_apply() == list.get(0).getGmb_apply_total()) {
				dao.boardApplyComp(intId);
			}
		}
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
	}
}
