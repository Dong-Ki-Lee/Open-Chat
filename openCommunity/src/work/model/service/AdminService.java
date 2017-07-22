package work.model.service;

import java.util.ArrayList;

import work.model.dao.AdminDao;
import work.model.dao.MembersDao;
import work.model.dao.NoticesDao;
import work.model.dao.MembersDao;
import work.model.dto.BoardsInfo;
import work.model.dto.Members;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;
import work.model.dto.PostInfo;
import work.model.dto.Posts;

/**
 * @author limjinha
 *
 */
public class AdminService {
	private MembersDao memDao = new MembersDao();
	private AdminDao admDao = new AdminDao();
	private NoticesDao ntcDao = new NoticesDao();
	
	/**
	 * 전체 회원 조회 
	 * @return 회원 객체 배열 
	 */
	public ArrayList<MembersInfo> getMemberList() {
		return admDao.selectList();
	}
	
	/**
	 * 게시판 전체 조회
	 * @return
	 */
	public ArrayList<BoardsInfo> getBoardList() {
		ArrayList<BoardsInfo> list = new ArrayList<BoardsInfo>();
		ArrayList<NoticeBoards> boardList = admDao.selectBoardList();
		ArrayList<Integer> postsCntList = admDao.selectPostsCnt();
		ArrayList<Integer> subCntList = admDao.selectSubscribeCnt();
		BoardsInfo dto = null;
		
		for (int i = 0; i < boardList.size(); i++) {
			int boardNo = boardList.get(i).getBoardNo();
			String boardTitle = boardList.get(i).getBoardTitle();
			int postsCnt = postsCntList.get(i);
			int subCnt = subCntList.get(i);
			
			dto = new BoardsInfo(boardNo, boardTitle, postsCnt, subCnt);
			list.add(dto);
		}
		return list;
	}
	
	/**
	 *  신고글 조회
	 * @return
	 */
	public ArrayList<PostInfo> getDisPostsList() {
		ArrayList<PostInfo> list = new ArrayList<PostInfo>();
		ArrayList<PostInfo> postsList = admDao.selectPostsList();
		ArrayList<Integer> disPostsCntList = admDao.selectDisPostsCnt();
		PostInfo dto = null;
		
		for (int i = 0; i < disPostsCntList.size(); i++) {
			int boardNo = postsList.get(i).getBoardNo();
			String boardTitle = postsList.get(i).getBoardTitle();
			String postTitle = postsList.get(i).getPostTitle();
			String postContent = postsList.get(i).getPostContent();
			String createTime = postsList.get(i).getCreateTime();
			int disPostCnt = disPostsCntList.get(i);
			
			dto = new PostInfo(boardNo, boardTitle, postTitle, postContent, createTime, disPostCnt);
			list.add(dto);
		}
		return list;
	}
	
	/** 신고글 삭제 */
	public int deleteDisPost(int postNo, int boardNo) {
		return ntcDao.deletePost(postNo, boardNo);
	}
	
	/** 로그인 */
	public int selectOneLogin(String memberEmail, String memberPw) {
		if (memDao.loginCheck(memberEmail, memberPw) != 0) {
			return memDao.loginCheck(memberEmail, memberPw);
		}
		return 0;
	}
	
}









