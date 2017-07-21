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
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;
import work.model.dto.PostInfo;
import work.model.service.AdminService;
import work.model.service.MemberService;
import work.model.dto.Posts;
import work.model.dao.NoticesDao;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminsv = new AdminService();
	private MemberService membersv = new MemberService();
	
	private MembersDao memDao = new MembersDao();
	private MembersDao membersDao = new MembersDao();
	private NoticesDao noticesDao = new NoticesDao();
	
	private BoardSearchDao boardSearchDao = new BoardSearchDao();

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ��û�ľ� : action=0000 : getParameter("key") : String
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);

		switch(action) {
		case "login":	// �α��� ����
			login(request, response);
			break;
		case "logout":	// �α׾ƿ� ����
			logout(request, response);
			break;
		case "selectPost": // �Խñ� ��ü ��ȸ
			selectPost(request,response);
			break;
		case "selectInternalPost": // �Խñ���ȸ
			selectInternalPost(request, response);
			break;
		case "adminMember":
			adminMember(request, response);
			break;
		case "adminBoard":
			adminBoard(request, response);
			break;
		case "deleteMember":
			deleteMember(request, response);
			break;
		case "deleteDisPost":
			deleteDisPost(request, response);
			break;
		case "myInfoPage":
			myInfoPage(request, response);
			break;
		case "createPost":
			createPost(request, response);
			break;
		case "createPostSave":
			createPostSave(request, response);
			break;
		case "deletePost":
			deletePost(request, response);
			break;
		case "createComments":
			createComments(request, response);
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

	/** �α��� (������, ȸ�� ����) */
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
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
 				dispatcher.forward(request, response);
 			}
 		} else {
 			request.setAttribute("Message", "�α��� ������ �Է��Ͻñ� �ٶ��ϴ�.");
 			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
 			dispatcher.forward(request, response);
 		}
 	}
	
	/** �α׾ƿ�  */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("memberNo") != null ) {
			session.removeAttribute("memberEmail");
			session.removeAttribute("memberNo");
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	/** ���������� (ȸ�� ���� ) */
	protected void myInfoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("memberNo") != null ) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			String memberNickname = membersv.getMemberNickname(memberNo);
			int mileage = membersv.getMileage(memberNo);
			int myPostCnt = membersv.getMyPostCnt(memberNo);
			int myCommentCnt = membersv.getMyCommentCnt(memberNo);
			
			request.setAttribute("memberNickname", memberNickname);
			request.setAttribute("mileage", mileage);
			request.setAttribute("myPostCnt", myPostCnt);
			request.setAttribute("myCommentCnt", myCommentCnt);
			
			request.getRequestDispatcher("/myPage.jsp").forward(request, response);
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
 	}

	// �Խñ� ��ü ����
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
			// �������� ���� ����� ó��
			//    		request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			//			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	// �Խñ� ���� ����
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

	/** ȸ�� ���� ������ (������ ����) */
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
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/** ȸ�� ���� Ż�� (������ ����) */
	protected void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			if (memberNo >= 1 && memberNo <= 1000) {
				int deleteMemberNo = Integer.parseInt(request.getParameter("memberNo"));
				adminsv.deleteMember(deleteMemberNo);
				adminMember(request, response);
			} else {
				request.setAttribute("Message", "�ش� ���񽺿� ���� ������ �ʿ��մϴ�.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	/** �Խ��� ���� ������ (������ ����) */
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
				request.setAttribute("Message", "�ش� ���񽺿� ���� ������ �ʿ��մϴ�.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	/** �Ű� �Խñ� ���� (������ ����) */
	protected void deleteDisPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			if (memberNo >= 1 && memberNo <= 1000) {
				int deletePostNo = Integer.parseInt(request.getParameter("postNo"));
				int deleteBoardNo = Integer.parseInt(request.getParameter("boardNo"));
				adminsv.deleteDisPost(deletePostNo, deleteBoardNo);
				adminBoard(request, response);
			} else {
				request.setAttribute("Message", "�ش� ���񽺿� ���� ������ �ʿ��մϴ�.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

protected void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.valueOf(boardNoString);
			String memberNickname = noticesDao.selectNickname(memberNo);
			if(memberNickname != null) {
				request.setAttribute("memberNo", memberNo);
				request.setAttribute("boardNo", boardNo);
				request.setAttribute("memberNickname", memberNickname);
				request.getRequestDispatcher("/postsCreate.jsp").forward(request, response);
			}
			
		} else {
			// �α��� �� ���
		}
	}
	
	protected void createPostSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.valueOf(boardNoString);
			String postTitle = request.getParameter("postTitle");
			String postContent = request.getParameter("postContent");
			System.out.println("#"+memberNo + ", " + boardNo +", " + postTitle +", " + postContent);
			//  !!!create_time, post_Views, postNo ����Ʈ
			int createPostCount = noticesDao.createPost(memberNo, boardNo, postTitle, postContent);
			System.out.println("#");
			if (createPostCount != 0 ) { // ���� ���� db �Ϸ�
				System.out.println("!");
				selectPost(request,response);
//				ArrayList<Posts> posts = noticesDao.selectPosts(boardNo);
//				request.setAttribute("posts", posts);
//				request.setAttribute("boardNo", boardNo);
//				response.sendRedirect("postsView.jsp");
//				request.getRequestDispatcher("/postsView.jsp").forward(request, response);
			}
			
			
		} else {
			// �α��� �� ���
		}
	}
	
	// �Խñ� ����
	protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// boardNo, postNo
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.valueOf(boardNoString);
			String postNoString = request.getParameter("postNo");
			int postNo = Integer.valueOf(postNoString);
			
			// �ۼ��� ȸ����ȣ
			System.out.println(request.getParameter("memberNo"));
			String createMemberNoString = request.getParameter("memberNo");
			int createMemberNo = Integer.valueOf(createMemberNoString);
			System.out.println("del " +memberNo + ", " + createMemberNo + ", " + boardNo + "," + postNo);
			int deletePostCount = noticesDao.deletePost(memberNo, createMemberNo, boardNo, postNo);
			if (deletePostCount != 0) {
				System.out.println("1");
				selectInternalPost(request,response);
				System.out.println("2");
			}
		} else {
			// �α��� �� ���
		}
	}
	
	protected void createComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.valueOf(boardNoString);
			String postNoString = request.getParameter("postNo");
			int postNo = Integer.valueOf(postNoString);
			String content = request.getParameter("content");
			
			int createCommentsCount = noticesDao.createComments(memberNo, boardNo, postNo, content);
			if (createCommentsCount != 0) {
				// Ȯ�� �ʿ�!!!!!!!!!!!!!!!!!!!!
				request.getRequestDispatcher("/postsInternalView.jsp").forward(request, response);
			}
		} else {
			// �α����� ���
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
