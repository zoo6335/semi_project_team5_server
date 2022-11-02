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
import kh.com.dao.GalleryDAO;
import kh.com.dao.MemberDAO;
import kh.com.vo.GalleryVO;
import kh.com.vo.MemberVO;

/**
 * Servlet implementation class GalleryServlet
 */
@WebServlet("/GalleryServlet")
public class GalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		
		if(!reqCmd.equals("GalleryInfo")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "NOK");
			out.print(resJson);
		}
		
		GalleryDAO dao = new GalleryDAO();
		List<GalleryVO> list = dao.gallerySelect(reqId);
		
		JSONArray galleryArray = new JSONArray();
		
		for(GalleryVO e : list) {
			JSONObject galleryInfo = new JSONObject();
			galleryInfo.put("id", e.getGal_id());
			galleryInfo.put("title", e.getTitle());
			galleryInfo.put("content", e.getContent());
			DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
			String createDateToStr = dateFormat.format(e.getCreate_date());
			String updateDateToStr = dateFormat.format(e.getUpdate_date());
			galleryInfo.put("create_date", createDateToStr);
			galleryInfo.put("update_date", updateDateToStr);
			galleryArray.add(galleryInfo);
		}	
		out.print(galleryArray);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}
}
