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

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminsv = new AdminService();
	private MemberService membersv = new MemberService();

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch(action) {
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
		default:	
		}	
	}

	public AdminController() {
		super();
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
				membersv.deleteMember(deleteMemberNo);
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
				request.getRequestDispatcher("/adminBoard.jsp").forward(request, response);
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}

}
