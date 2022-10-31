package kh.com.servlet;
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
import kh.com.dao.MemberDAO;
import kh.com.vo.MemberVO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		response = Common.corsResSet(response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response = Common.corsResSet(response); //응답할 response를 cors 처리를 해서 담아둔다.

		StringBuffer sb = Common.reqStringBuff(request);  // 요청받은 data를 문자열로 stringbuff로 변환해서 sb에 담아둔다
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		System.out.println("Command : " + (String)jsonObj.get("cmd"));
		String reqCmd = (String)jsonObj.get("cmd");
		String reqId = (String)jsonObj.get("id");
		
		PrintWriter out = response.getWriter();
		
		if(!reqCmd.equals("MemberInfo")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "NOK");
			out.print(resJson);
		}
		
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberSelect(reqId);
		
		JSONArray memberArray = new JSONArray();
		
		for(MemberVO e : list) {
			JSONObject memberInfo = new JSONObject();
			memberInfo.put("id", e.getUser_id());
			memberInfo.put("pwd", e.getPwd());
			memberInfo.put("name", e.getName());
			memberInfo.put("email", e.getEmail());
			DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
			String dateToStr = dateFormat.format(e.getJoin());
			memberInfo.put("join", dateToStr);
			memberArray.add(memberInfo);
		}	
		out.print(memberArray);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}
}