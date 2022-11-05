package kh.com.servlet.freeboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kh.com.common.Common;
import kh.com.dao.FreeBoardDAO;


@WebServlet("/FBoardCreateServlet")
public class BoardCreateServlet extends HttpServlet {
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
		
		String getFb_category = (String)jsonObj.get("fb_category");
		String getFb_user_id = (String)jsonObj.get("fb_user_id");
		String getFb_title = (String)jsonObj.get("fb_title");
		String getFb_content = (String)jsonObj.get("fb_content");
		
		FreeBoardDAO dao = new FreeBoardDAO();
		boolean isRegister = dao.boardWrite(getFb_category, getFb_user_id, getFb_title, getFb_content);
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		if(isRegister) resJson.put("result", "OK"); 
		else resJson.put("result", "NOK");
		out.print(resJson);
	}

}
