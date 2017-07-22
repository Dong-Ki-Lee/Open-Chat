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

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BoardController() {
        super();
    }
	private NoticesDao noticesDao = new NoticesDao();

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch(action) {
		case "selectPost":
			selectPost(request,response);
			break;
		case "selectInternalPost":
			selectInternalPost(request, response);
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

	/** �Խñ� ��� ����  */
	protected void selectPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("memberNo") != null ) {
			int memberNo = (int) session.getAttribute("memberNo");
			
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.valueOf(boardNoString);
			System.out.println("boardNo : " + boardNo);
			ArrayList<Posts> posts = noticesDao.selectPosts(boardNo);
			if (posts != null)  {
				request.setAttribute("posts", posts);
				request.setAttribute("boardNo", boardNo);
				request.getRequestDispatcher("/postsView.jsp").forward(request, response);
			} else {
				//    			response.sendRedirect("result.jsp");
			}
		}
		else {
			request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/** �Խñ� ���� ���� */
	protected void selectInternalPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("memberNo") != null) {
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.parseInt(boardNoString);
			String postNoString = request.getParameter("postNo");
			int postNo = Integer.parseInt(postNoString);
			System.out.println("board : " + boardNoString + " postNo :" + postNoString);
			int memberNo = (int) session.getAttribute("memberNo");
			String memberNickname = noticesDao.selectNickname(memberNo);
			Posts posts = noticesDao.selectPosts(boardNo, postNo);
			ArrayList<Comments> comments = noticesDao.selectComments(posts.getBoardNo(), posts.getPostNo());
			
			if( posts != null && comments != null && memberNickname != null) {
				request.setAttribute("posts", posts);
				request.setAttribute("comments", comments);
				request.setAttribute("memberNickname", memberNickname);
				
				request.getRequestDispatcher("/postsInternalView.jsp").forward(request, response);
			}
			
		} else {
			request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/** �Խñ� ���� */
	protected void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/** �Խñ� ���� */
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
			request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/** �Խñ� ���� */
	protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("memberNo") != null) {
			int memberNo = (int)session.getAttribute("memberNo");
			String boardNoString = request.getParameter("boardNo");
			int boardNo = Integer.valueOf(boardNoString);
			String postNoString = request.getParameter("postNo");
			int postNo = Integer.valueOf(postNoString);

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
			request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/** ��� ���� */
	protected void createComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.setAttribute("message", "ȸ������ ���� �Դϴ�. �α��� �� ����Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
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
