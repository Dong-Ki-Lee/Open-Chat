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
import work.model.dto.Members;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;
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

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ��û�ľ� : action=0000 : getParameter("key") : String
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);

		switch(action) {
		case "login":	// �α��� ����
			login(request, response);
			break;
		case "selectPost": // �Խñ� ��ȸ
			selectPost(request,response);
			break;
		case "selectInternalPost":
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
	 * 1. �α��� ���� �޼ҵ�
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
 					ArrayList<MembersInfo> list = memDao.selectList();
 					request.setAttribute("memInfolist", list);
 					RequestDispatcher dispatcher = request.getRequestDispatcher("/adminMember.jsp");
 					dispatcher.forward(request, response);
 				} else {
 					// ȸ�� �α��� ���� 
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
 				request.setAttribute("Message", "���̵�, ��й�ȣ�� �ٽ� Ȯ�����ֽñ� �ٶ��ϴ�.");
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
 				dispatcher.forward(request, response);
 			}
 		} else {
 			request.setAttribute("Message", "�α��� ������ �Է��Ͻñ� �ٶ��ϴ�.");
 			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
 			dispatcher.forward(request, response);
 		}
 	}

	// �Խñ� ��ü ����
	private void selectPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("member_no") != null &&
				session.getAttribute("board_no") != null) {
			int memberNo = (int) session.getAttribute("member_no");
			int boardNo = (int) session.getAttribute("board_no");

			ArrayList<Posts> posts = noticesDao.selectPosts(boardNo);
			ArrayList<PostsPreference> recommend = noticesDao.selectRecommend(boardNo);
			ArrayList<Members> memberNickname = membersDao.selectNickname();
			if (posts != null && recommend != null && memberNickname != null) {
				request.setAttribute("posts", posts);
				request.setAttribute("recommend", recommend);
				request.setAttribute("memberNickname", memberNickname);
				request.getRequestDispatcher("/postsView.jsp").forward(request, response);
			} else {
				//    			response.sendRedirect("result.jsp");
			}
		}
		else {
			// �������� ���� ����� ó��
			//    		request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			//			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	// �Խñ� ���� ����
	private void selectInternalPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String postTitle = (String)session.getAttribute("postTitle");
		String memberNickname = (String)session.getAttribute("memberNickname");
		int postViews = (int)session.getAttribute("postViews");
		String createTime = (String)session.getAttribute("createTime");
		int recommend = (int)session.getAttribute("recommend");
		if(session != null && session.getAttribute("postTitle") != null &&
				session.getAttribute("memberNicnkname") != null &&
				session.getAttribute("postViews") != null &&
				session.getAttribute("createTime") != null &&
				session.getAttribute("recommend") != null )
		{
			request.setAttribute("postTitle", postTitle);
			request.setAttribute("memberNickname", memberNickname);
			request.setAttribute("postViews", postViews);
			request.setAttribute("createTime", createTime);
			request.setAttribute("recommend", recommend);
			request.getRequestDispatcher("/postsInternalView.jsp").forward(request, response);

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
				request.setAttribute("Message", "�ش� ���񽺿� ���� ������ �ʿ��մϴ�.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void adminBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			if (memberNo >= 1 && memberNo <= 1000) {
				ArrayList<BoardsInfo> list = adminsv.getBoardList();
				ArrayList<Posts> list2 = adminsv.getDisPostsList();
				request.setAttribute("boardsInfolist", list);
				request.setAttribute("disPostsInfolist", list2);
				request.getRequestDispatcher("/adminNotice.jsp").forward(request, response);
			} else {
				request.setAttribute("Message", "�ش� ���񽺿� ���� ������ �ʿ��մϴ�.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
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
