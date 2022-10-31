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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

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
		
		String getId = (String)jsonObj.get("id"); // 프론로부터 들어온 id
		String getPwd = (String)jsonObj.get("pwd");
		MemberDAO dao = new MemberDAO(); // database와 통신하기 위해 DAO를 만들어둔다.
		boolean isRegister = dao.loginCheck(getId, getPwd); // 아이디 비밀번호가 있는지 체크
		
		PrintWriter out = response.getWriter(); // 출력하기위한 print 객체 생성
		JSONObject resJson = new JSONObject(); // 프론트로 출력하기 위해 response json object 생성
		if(isRegister) resJson.put("result", "OK"); 
		else resJson.put("result", "NOK");
		out.print(resJson);		
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}
}