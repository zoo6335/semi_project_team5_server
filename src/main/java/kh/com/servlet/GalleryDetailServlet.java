
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
import kh.com.dao.BoardDAO;
import kh.com.dao.GalleryDAO;
import kh.com.vo.BoardVO;
import kh.com.vo.GalleryVO;

// 게시판 내용 상세보기
@WebServlet("/GalleryDetailServlet")
public class GalleryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	// CORS 처리
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Common.corsResSet(response);
	}

	@SuppressWarnings({ "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Common.corsResSet(response);
		
		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		PrintWriter out = response.getWriter();
		
		String getGal_id = (String)jsonObj.get("gal_id");
		// JSON 데이터는 String 이므로 -> 원하는 Int 형으로 변환
		int intId = Integer.parseInt(getGal_id);
		System.out.println("전달 받은 ID : " + intId);

		GalleryDAO dao = new GalleryDAO();
		List<GalleryVO> list = dao.galleryDetailSelect(intId);

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
	
}
