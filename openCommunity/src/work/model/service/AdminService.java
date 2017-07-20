package work.model.service;

import java.util.ArrayList;

import work.model.dao.AdminDao;
import work.model.dao.MembersDao;
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
	
	/** 등록 회원수 조회 메서드 */
	public int getCount() {
		return 0;
	}
	
	/**
	 * 회원 탈퇴  
	 * @param memberId
	 * @return 탈퇴적용 행수 반환 
	 */
	public int deleteMember(String memberId) {
		return 0;
	}
	
	/**
	 * 전체 회원 조회 
	 * @return 회원 객체 배열 
	 */
	public ArrayList<MembersInfo> getMemberList() {
		return memDao.selectList();
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
	
	
	
	
	/**
	 * 로그인
	 */
	public int selectOneLogin(String memberEmail, String memberPw) {
		if (memDao.loginCheck(memberEmail, memberPw) != 0) {
			return memDao.loginCheck(memberEmail, memberPw);
		}
		return 0;
	}
	
//	
//case "boardList":	// 7. 게시판 전체 정보 조회 서비스
//	boardList(request, response);
//	break;
//case "disPostList":	// 8. 신고 게시글 정보 조회 서비스
//	disPostList(request, response);
//	break;
//case "memberList":	// 9. 모든 회원 조회 서비스
//	memberList(request, response);
//	break;
//case "newMemberList":	// 10. 신규가입 회원 조회 서비스
//	newMemberList(request, response);
//	break;
//case "deleteMember":	// 11. 회원 삭제 서비스
//	deleteMember(request, response);
//	break;
	
}









