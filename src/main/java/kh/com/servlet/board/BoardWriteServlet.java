package kh.com.servlet.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;


import org.json.simple.JSONObject;

import kh.com.common.Common;
import kh.com.dao.BoardDAO;

// 게시물 작성하기
@WebServlet("/BoardWriteServlet")
public class BoardWriteServlet extends HttpServlet {
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
		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		String getGmb_user_id = (String)jsonObj.get("gmb_user_id");
		String getGmb_title = (String)jsonObj.get("gmb_title");
		String getGmb_content = (String)jsonObj.get("gmb_content");
		String getGmb_apply_total = (String)jsonObj.get("gmb_apply_total");
		int intApplyTotal = Integer.parseInt(getGmb_apply_total);
		
		BoardDAO dao = new BoardDAO();
		boolean rstComplete = dao.WriteBoard(getGmb_user_id, getGmb_title, getGmb_content, intApplyTotal);
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
		return;
	}
}