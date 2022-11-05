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
import kh.com.dao.BoardRankingDAO;
import kh.com.vo.BoardRankVO;

@WebServlet("/BoardRankSevlet") 
public class BoardRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardRankServlet() {
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
		if(!reqCmd.equals("BoardRank")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "Not Ok");
			out.print(resJson);
			return;
		}
		
		BoardRankingDAO dao = new BoardRankingDAO(); 
		List<BoardRankVO> list = dao.boardSelect(); 
		
		JSONArray rankingArray = new JSONArray();
		for(BoardRankVO e : list) { 
			JSONObject boardRank = new JSONObject();
			boardRank.put("rank", e.getRank());
			boardRank.put("category", e.getCategory());
			boardRank.put("title", e.getTitle());
			boardRank.put("view", e.getView());
			boardRank.put("postId", e.getPostId());

			rankingArray.add(boardRank);
		}
		System.out.println(rankingArray);
		out.print(rankingArray);
	}
}
