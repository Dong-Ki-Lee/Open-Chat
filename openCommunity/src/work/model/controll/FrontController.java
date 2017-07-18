package work.model.controll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import work.model.dao.BoardSearchDao;
import work.model.dto.NoticeBoards;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardSearchDao boardSearchDao = new BoardSearchDao();
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청파악 : action=0000 : getParameter("key") : String
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);
		
		switch(action) {
			case "login":
				//로그인 요청처리 메서드 호출
				break;
			case "joinSave":
				break;
			case "changePw":
				break;
			case "searchBoard":
				searchBoard(request, response);
			default:	
		}	
		
	}
	
	protected void searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## action : searchBoard()");
		// 2. 요청 데이터 추출 : join.jsp
		String searchTag = request.getParameter("searchTag");
		
		if (searchTag != null && searchTag.length() > 0) {
			ArrayList<NoticeBoards> boardList = boardSearchDao.searchBoardWithTag(searchTag);

			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/memberList.jsp").forward(request, response);
		}
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
