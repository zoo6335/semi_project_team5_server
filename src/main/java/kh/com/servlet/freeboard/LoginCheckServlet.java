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

@WebServlet("/CheckLoginServlet")
public class LoginCheckServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
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
		
		String getFb_user_id = (String)jsonObj.get("fb_user_id");
		FreeBoardDAO dao = new FreeBoardDAO();
		boolean isNotReg = dao.regIdCheck(getFb_user_id); //isNotReg = TRUE 가입안된 경우
	
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		if(isNotReg) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
	}

}
