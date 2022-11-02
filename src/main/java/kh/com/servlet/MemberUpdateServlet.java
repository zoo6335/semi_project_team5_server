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

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		response = Common.corsResSet(response);
		
		StringBuffer sb = Common.reqStringBuff(request); // request를 common.reqStringBuff에 담아서 "문자열"로 변환한 다음 sb에 담는다. -> front와 backend는 json으로 통신한다.
		JSONObject jsonObj = Common.getJsonObj(sb); // 문자열로 변환한 sb를를 JsonObj으로 만들어서 담는다.  -> 왜냐면 통신은 json으로 이루어지기 떄문에
		
		
		String getId = (String)jsonObj.get("id");
		String getPwd = (String)jsonObj.get("pwd");
		String getName = (String)jsonObj.get("name");
		String getMail = (String)jsonObj.get("mail");
		
		MemberDAO dao = new MemberDAO();
		boolean rstComplete = dao.memberUpdate(getId, getPwd, getName, getMail);
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
	}

}
