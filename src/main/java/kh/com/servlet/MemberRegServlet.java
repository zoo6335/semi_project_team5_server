package kh.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kh.com.common.Common;
import kh.com.dao.MemberDAO;

@WebServlet("/MemberRegServlet")
public class MemberRegServlet extends HttpServlet {
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
		Common.corsResSet(response); //응답할 response를 cors 처리를 해서 담아둔다.

		StringBuffer sb = Common.reqStringBuff(request);  // 요청받은 data를 문자열로 stringbuff로 변환해서 sb에 담아둔다
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		String getId = (String)jsonObj.get("id");
		String getPwd = (String)jsonObj.get("pwd");
		String getName = (String)jsonObj.get("name");
		String getMail = (String)jsonObj.get("mail");
		
		MemberDAO dao = new MemberDAO();
		boolean rstComplete = dao.memberRegister(getId, getPwd, getName, getMail);
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
		
	}
}
