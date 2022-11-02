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
import kh.com.vo.GalleryVO;

/**
 * Servlet implementation class GalleryRegServlet
 */
@WebServlet("/GalleryRegServlet")
public class GalleryRegServlet extends HttpServlet {
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
	
		
		String getTItle = (String)jsonObj.get("title");
		String getContent = (String)jsonObj.get("content");
		String getUrl = (String)jsonObj.get("image_url");
		
		
		GalleryDAO dao = new GalleryDAO();
		boolean rstComplete = dao.galleryRegister(getTItle, getContent, getUrl);
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstComplete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
		
	}
}
