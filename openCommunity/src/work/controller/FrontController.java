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
		// 1. 요청파악 : action=0000 : getParameter("key") : String
		String action = request.getParameter("action");
		System.out.println("\n## action : " + action);
		
		switch(action) {
			case "login":	// 1. 로그인 서비스
				login(request, response);
				break;
			case "logout":	// 2. 로그아웃 서비스
				logout(request, response);
				break;
			case "joinSave":	// 3. 회원가입 정보 저장 서비스
				joinSave(request, response);
				break;
			case "myInfo":		// 4. 내 정보 조회 서비스
				myInfo(request, response);
				break;
			case "changePw":	// 5. 비밀번호 변경 서비스
				changePw(request, response);
				break;
			case "changeMyInfo":	// 6. 내 정보 변경 서비스
				changeMyInfo(request, response);
				break;
			case "deletePost":	// 12. 게시글 삭제 서비스
				deletePost(request, response);
				break;
			case "deleteComment":	// 13. 댓글 삭제 서비스
				deleteComment(request, response);
				break;
			case "createBoard":	// 14. 게시판 생성 서비스
				createBoard(request, response);
				break;
			case "createPost":	// 15. 게시글 생성 서비스
				createPost(request, response);
				break;
			case "createComment":	// 16. 댓글 생성 서비스
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
   	   				ArrayList<MembersInfo> list = memDao.selectList();
   	   				request.setAttribute("memInfolist", list);
   	   				RequestDispatcher dispatcher = request.getRequestDispatcher("/adminMember.jsp");
   	   				dispatcher.forward(request, response);
   	   			} else {
   	   				RequestDispatcher dispatcher = request.getRequestDispatcher("/memberPageAfterLogin.jsp");
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
   	
    /**
	 * 2. 로그아웃 서비스 메소드
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	 /**
	 * 3. 회원가입 정보 저장 서비스 메소드
	 */
	protected void joinSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 4. 내 정보 조회 서비스 메소드
	 */
	protected void myInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 5. 비밀번호 변경 서비스 메소드
	 */
	protected void changePw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 6. 내 정보 변경 서비스 메소드
	 */
	protected void changeMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
//	/**
//	 * 9. 회원 전체 정보 조회 서비스
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
//				request.setAttribute("Message", "해당 서비스에 대한 권한이 필요합니다.");
//				request.getRequestDispatcher("/error.jsp").forward(request, response);
//			}	
//		} else {
//			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
//		}
//	}
	
	/**
	 * 10. 신규가입 회원 조회 서비스
	 */
	protected void newMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * 11. 회원 삭제 서비스
	 */
	protected void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 12. 게시글 삭제 서비스
	 */
	protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 13. 댓글 삭제 서비스
	 */
	protected void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 14. 게시판 생성 서비스
	 */
	protected void createBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 15. 게시글 생성 서비스
	 */
	protected void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 16. 댓글 생성 서비스
	 */
	protected void createComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private BoardSearchDao boardSearchDao = new BoardSearchDao();
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
