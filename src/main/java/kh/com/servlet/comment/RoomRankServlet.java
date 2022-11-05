package kh.com.servlet.comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import kh.com.common.Common;
import kh.com.dao.RoomRankingDAO;
import kh.com.vo.RoomRankVO;

@WebServlet("/RoomRankServlet") 
public class RoomRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RoomRankServlet() {
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
		Common.corsResSet(response);
		
		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		String reqCmd = (String)jsonObj.get("cmd");
		
		PrintWriter out = response.getWriter();
		if(!reqCmd.equals("RoomRank")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "Not Ok");
			out.print(resJson);
			return;
		}
		
		RoomRankingDAO dao = new RoomRankingDAO(); 
		List<RoomRankVO> list = dao.roomSelect(); 
		
		JSONArray rankingArray = new JSONArray(); 
		for(RoomRankVO e : list) { 
			JSONObject roomRank = new JSONObject();
			roomRank.put("postId", e.getPostId());
			roomRank.put("rank", e.getRank());
			roomRank.put("category", e.getCategory());
			roomRank.put("title", e.getTitle());
			roomRank.put("like", e.getLike());
			rankingArray.add(roomRank);
		}
		System.out.println(rankingArray);
		out.print(rankingArray);
	}
}
