package work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dao.BoardSearchDao;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);
		
		switch(action) {
			case "searchBoard":
				searchBoard(request, response);
				break;
			case "showMain":
				showMain(request, response);
				break;
			default:	
		}	
		
	}
    
    private BoardSearchDao boardSearchDao = new BoardSearchDao();
	protected void searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## action : searchBoard()");
		// 2. 요청 데이터 추출 : join.jsp
		String searchTag = request.getParameter("searchTag");
		
		if (searchTag != null && searchTag.length() > 0) {

			String[] tagArray = searchTag.split("#");
			
			ArrayList<String> inputList = new ArrayList<String>();
			
			for(int index = 0; index < tagArray.length; index++) {
				inputList.add(tagArray[index]);
			}
			
			ArrayList<NoticeBoards> boardList = boardSearchDao.searchBoardWithTag(inputList);

			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/boardViewAfterSearch.jsp").forward(request, response);
		}
		
	}
	
	protected void showMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			ArrayList<String> searchHistory = boardSearchDao.getSearchHistory(memberNo);
			
			ArrayList<NoticeBoards> matchBoard = null;
			if(searchHistory == null || searchHistory.size() == 0) {
				matchBoard = boardSearchDao.getLargeBoard();
			} else {
				matchBoard = boardSearchDao.searchBoardWithTag(searchHistory);
			}
			
			ArrayList<NoticeBoards> subscribeBoard = boardSearchDao.getSubscribeBoard(memberNo);
			
			request.setAttribute("subscribeBoard", subscribeBoard);
			request.setAttribute("matchBoard", matchBoard);
			request.getRequestDispatcher("/memberPageAfterLogin.jsp").forward(request, response);
			
		} else {
			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		doGet(request, response);
	}

}
