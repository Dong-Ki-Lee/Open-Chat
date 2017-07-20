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
import work.model.dao.MembersDao;
import work.model.dto.BoardsInfo;
import work.model.dto.Comments;
import work.model.dto.Members;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;
import work.model.dto.PostInfo;
import work.model.service.AdminService;

import work.model.dto.Posts;
import work.model.dto.PostsPreference;
import work.model.dao.NoticesDao;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminsv = new AdminService();
	private MembersDao memDao = new MembersDao();

	private MembersDao membersDao = new MembersDao();
	private NoticesDao noticesDao = new NoticesDao();
	
	private BoardSearchDao boardSearchDao = new BoardSearchDao();

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청파악 : action=0000 : getParameter("key") : String
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);

		switch(action) {
		case "login":	// 로그인 서비스
			login(request, response);
			break;
		case "selectPost": // 게시글 전체 조회
			selectPost(request,response);
			break;
		case "selectInternalPost": // 게시글조회
			selectInternalPost(request, response);
			break;
		case "adminMember":
			adminMember(request, response);
			break;
		case "adminBoard":
			adminBoard(request, response);
			break;
		default:	
		}	

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}

	/**
	 * 1. 로그인 서비스 메소드
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String memberEmail = request.getParameter("memberEmail");
 		String memberPw = request.getParameter("memberPw");
 
 		if (memberEmail.length() > 0 && memberPw.length() > 0) {
 			int memberNo = adminsv.selectOneLogin(memberEmail, memberPw);
 
 			if (memberNo != 0) {
 				HttpSession session = request.getSession(true);
 				session.setAttribute("memberEmail", memberEmail);
 				session.setAttribute("memberNo", memberNo);
 
 				if (memberNo >= 1 && memberNo <= 1000) {
 					ArrayList<MembersInfo> list = adminsv.getMemberList();
 					request.setAttribute("membersInfolist", list);
 					RequestDispatcher dispatcher = request.getRequestDispatcher("/adminMember.jsp");
 					dispatcher.forward(request, response);
 				} else {
 					// 회원 로그인 직후 
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
 					RequestDispatcher dispatcher = request.getRequestDispatcher("/memberPage.jsp");
 					dispatcher.forward(request, response);
 				}
 
 			} else {
 				request.setAttribute("Message", "아이디, 비밀번호를 다시 확인해주시기 바랍니다.");
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
 				dispatcher.forward(request, response);
 			}
 		} else {
 			request.setAttribute("Message", "로그인 정보를 입력하시기 바랍니다.");
 			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
 			dispatcher.forward(request, response);
 		}
 	}

	// 게시글 전체 보기
	protected void selectPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("memberNo") != null ) {
			int memberNo = (int) session.getAttribute("memberNo");
			int boardNo = (int) request.getAttribute("boardNo");
			
			ArrayList<Posts> posts = noticesDao.selectPosts(boardNo);
			if (posts != null)  {
				request.setAttribute("posts", posts);
				request.getRequestDispatcher("/postsView.jsp").forward(request, response);
			} else {
				//    			response.sendRedirect("result.jsp");
			}
		}
		else {
			// 인증되지 않은 사용자 처리
			//    		request.setAttribute("message", "회원전용 서비스 입니다. 로그인 후 사용하시기 바랍니다.");
			//			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	// 게시글 내부 보기
	private void selectInternalPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		Posts posts = (Posts)request.getAttribute("posts");
		
		if(session != null && session.getAttribute("memberNo") != null && request.getAttribute("posts") != null)
		{
			int memberNo = (int) session.getAttribute("memberNo");
			String memberNickname = noticesDao.selectNickname(memberNo);
			ArrayList<Comments> comments = noticesDao.selectComments(posts.getBoardNo(), posts.getBoardNo());
			
			if (posts != null && comments != null) {
				request.setAttribute("postContent", posts.getPostContent());
				request.setAttribute("posts", posts);
				request.setAttribute("memberNickname", memberNickname);
				request.setAttribute("comments", comments);
				
				request.getRequestDispatcher("/postsInternalView.jsp").forward(request, response);
			} else {
				
			}

		} else {
		}
	}

	protected void adminMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			if (memberNo >= 1 && memberNo <= 1000) {
				ArrayList<MembersInfo> list = adminsv.getMemberList();
				request.setAttribute("membersInfolist", list);
				request.getRequestDispatcher("/adminMember.jsp").forward(request, response);
			} else {
				request.setAttribute("Message", "해당 서비스에 대한 권한이 필요합니다.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void adminBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			if (memberNo >= 1 && memberNo <= 1000) {
				ArrayList<BoardsInfo> list = adminsv.getBoardList();
				ArrayList<PostInfo> list2 = adminsv.getDisPostsList();
				request.setAttribute("boardsInfolist", list);
				request.setAttribute("disPostsInfolist", list2);
				request.getRequestDispatcher("/adminNotice.jsp").forward(request, response);
			} else {
				request.setAttribute("Message", "해당 서비스에 대한 권한이 필요합니다.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}

}
