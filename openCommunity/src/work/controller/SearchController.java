package work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dao.BoardSearchDao;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;


public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	    case "recommandExtend":
	        recommandExtend(request, response);
	        break;
	    case "subscribeExtend":
	        subscribeExtend(request, response);
	        break;
	    default:
	    }
	
	}
	
	private BoardSearchDao boardSearchDao = new BoardSearchDao();
	
	protected void subscribeExtend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	
	    if (session != null && session.getAttribute("memberNo") != null) {
	        int memberNo = (int)session.getAttribute("memberNo");
	
	        ArrayList<NoticeBoards> subscribeBoard = boardSearchDao.getSubscribeBoard(memberNo);
	
	        request.setAttribute("boardList", subscribeBoard);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/boardViewAfterSearch.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }
	}
	
	
	protected void recommandExtend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	        request.setAttribute("boardList", matchBoard);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/boardViewAfterSearch.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }
	}
	protected void searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## action : searchBoard()");
		// 2. 요청 데이터 추출 : join.jsp
		String searchTag = request.getParameter("searchTag");
		
		if (searchTag != null && searchTag.length() > 0) {

			String[] tagArray = searchTag.split("#");
			System.out.println(tagArray.length);
			ArrayList<String> inputList = new ArrayList<String>();
			
			for(int index = 0; index < tagArray.length; index++) {
				inputList.add(tagArray[index]);
			}
			System.out.println(inputList.size());
			
			ArrayList<NoticeBoards> boardList = boardSearchDao.searchBoardWithTag(inputList);

			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/boardViewAfterSearch.jsp").forward(request, response);
		} else {
			request.setAttribute("Message", "올바른 검색어를 입력해주세요");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
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
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request,response);
	}

}
