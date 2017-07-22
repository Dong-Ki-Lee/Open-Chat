package work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
import work.model.service.MemberService;
import work.model.dto.Posts;
import work.model.dao.NoticesDao;

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminsv = new AdminService();
	private MemberService membersv = new MemberService();
	
	private MembersDao memberDao = new MembersDao();

	private BoardSearchDao boardSearchDao = new BoardSearchDao();

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch(action) {
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "joinSave":
			joinSave(request, response);
			break;
		case "home":
			home(request, response);
			break;
		case "myInfoPage":
			myInfoPage(request, response);
			break;
//		case "findMemberPw":
//			findMemberPw(request, response);
//			break;
		default:	
		}	
	}

	public MemberController() {
		super();
	}
	
	/** 홈으로 이동 */
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			
			if (memberNo >= 1 && memberNo <= 1000) {
				ArrayList<MembersInfo> list = adminsv.getMemberList();
				request.setAttribute("membersInfolist", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/adminMember.jsp");
				dispatcher.forward(request, response);
			} else {
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
		}
	}

	/** 로그인 (관리자, 회원 구분) */
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("Message", "로그인 정보를 입력하시기 바랍니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/** 로그아웃  */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if(session != null && session.getAttribute("memberNo") != null ) {
			session.removeAttribute("memberEmail");
			session.removeAttribute("memberNo");
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else {
			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	/** 회원가입 정보 저장 */
	protected void joinSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		String memberNickname = request.getParameter("memberNickname");
		String memberPw = request.getParameter("memberPw");
		Members dto = new Members(0000, memberEmail, memberNickname, memberPw);
		int rows = memberDao.insert(dto);
		if (rows == 1) {
			request.setAttribute("message", memberNickname + "님 회원가입이 완료되었습니다. 로그인 후 이용하시기 바랍니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			request.setAttribute("message", memberNickname + "님 회원가입이 정상적으로 이루어 지지 않았습니다. 다시 확인 하시기 바랍니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/** 마이페이지 (회원 전용 ) */
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
			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
//	protected void findMemberPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String[]> map = request.getParameterMap();
//		if (map != null) {
//			String memberEmail = map.get("memberEmail")[0];
//			String memberNickname = map.get("memberNickname")[0];
//			
//			if (memberEmail != null && memberNickname != null) {
//				String tmpPw = memberDao.selectMemberPw(memberEmail, memberNickname);
//				if (tmpPw != null) {
//					StringBuilder info = new StringBuilder();
//					info.append(memberNickname);
//					info.append("님의 임시발급암호는 ");
//					info.append(tmpPw);
//					info.append(" 입니다.");
//					info.append("로그인 후 암호를 변경하시기 바랍니다.");
//					request.setAttribute("message", info);
//					request.getRequestDispatcher("/login.jsp").forward(request, response);
//					return;
//				}
//			}
//		}		
//		request.setAttribute("message", "정보를 다시 확인하시기 바랍니다.");
//		request.getRequestDispatcher("/error.jsp").forward(request, response);
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}

}
