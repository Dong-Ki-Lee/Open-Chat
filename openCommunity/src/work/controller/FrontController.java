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
import work.model.dto.Members;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;
import work.model.service.AdminService;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminsv = new AdminService();
	private MembersDao memDao = new MembersDao();
       
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ��û�ľ� : action=0000 : getParameter("key") : String
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);
		
		switch(action) {
			case "login":	// 1. �α��� ����
				login(request, response);
				break;
			case "logout":	// 2. �α׾ƿ� ����
				logout(request, response);
				break;
			case "joinSave":	// 3. ȸ������ ���� ���� ����
				joinSave(request, response);
				break;
			case "myInfo":		// 4. �� ���� ��ȸ ����
				myInfo(request, response);
				break;
			case "changePw":	// 5. ��й�ȣ ���� ����
				changePw(request, response);
				break;
			case "changeMyInfo":	// 6. �� ���� ���� ����
				changeMyInfo(request, response);
				break;
			case "deletePost":	// 12. �Խñ� ���� ����
				deletePost(request, response);
				break;
			case "deleteComment":	// 13. ��� ���� ����
				deleteComment(request, response);
				break;
			case "createBoard":	// 14. �Խ��� ���� ����
				createBoard(request, response);
				break;
			case "createPost":	// 15. �Խñ� ���� ����
				createPost(request, response);
				break;
			case "createComment":	// 16. ��� ���� ����
				createComment(request, response);
				break;
			case "searchBoard":
				searchBoard(request, response);
				break;
			case "adminMemberPage":
				adminMemberPage(request, response);
				break;
			case "adminBoardPage":
				adminBoardPage(request, response);
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
   	   				RequestDispatcher dispatcher = request.getRequestDispatcher("/memberPageAfterLogin.jsp");
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
   	
    /**
	 * 2. �α׾ƿ� ���� �޼ҵ�
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	 /**
	 * 3. ȸ������ ���� ���� ���� �޼ҵ�
	 */
	protected void joinSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 4. �� ���� ��ȸ ���� �޼ҵ�
	 */
	protected void myInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 5. ��й�ȣ ���� ���� �޼ҵ�
	 */
	protected void changePw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 6. �� ���� ���� ���� �޼ҵ�
	 */
	protected void changeMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
//	/**
//	 * 9. ȸ�� ��ü ���� ��ȸ ����
//	 */
//	protected void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(false);
//		
//		if (session != null && session.getAttribute("memberNo") != null) {
//			int memberNo = (int)session.getAttribute("memberNo");
//			
//			if (memberNo >= 1 && memberNo <= 1000) {
//				ArrayList<MembersInfo> list = memDao.selectList();
//				request.setAttribute("memInfolist", list);
//				request.getRequestDispatcher("/adminMember.jsp").forward(request, response);
//			} else {
//				request.setAttribute("Message", "�ش� ���񽺿� ���� ������ �ʿ��մϴ�.");
//				request.getRequestDispatcher("/error.jsp").forward(request, response);
//			}	
//		} else {
//			request.setAttribute("Message", "�α��� �� ����Ͻñ� �ٶ��ϴ�.");
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
//		}
//	}
	
	/**
	 * 10. �ű԰��� ȸ�� ��ȸ ����
	 */
	protected void newMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * 11. ȸ�� ���� ����
	 */
	protected void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 12. �Խñ� ���� ����
	 */
	protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 13. ��� ���� ����
	 */
	protected void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 14. �Խ��� ���� ����
	 */
	protected void createBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 15. �Խñ� ���� ����
	 */
	protected void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 16. ��� ���� ����
	 */
	protected void createComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private BoardSearchDao boardSearchDao = new BoardSearchDao();
	protected void searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## action : searchBoard()");
		// 2. ��û ������ ���� : join.jsp
		String searchTag = request.getParameter("searchTag");
		
		if (searchTag != null && searchTag.length() > 0) {
			ArrayList<NoticeBoards> boardList = boardSearchDao.searchBoardWithTag(searchTag);

			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/memberList.jsp").forward(request, response);
		}
		
	}

	protected void adminMemberPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void adminBoardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
