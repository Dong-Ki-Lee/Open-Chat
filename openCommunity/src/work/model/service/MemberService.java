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
public class MemberService {
	private MembersDao memDao = new MembersDao();
	private AdminDao admDao = new AdminDao();
	private NoticesDao ntcDao = new NoticesDao();
	
	/** ȸ�� �г��� ��ȸ */
	public String getMemberNickname(int memberNo) {
		return memDao.selectMyNickname(memberNo);
	}
	
	/** ���ϸ��� ��ȸ */
	public int getMileage(int memberNo) {
		return memDao.selectMileage(memberNo);
	}
	
	/** ���� �� �Խñ� �� */
	public int getMyPostCnt(int memberNo) {
		return memDao.selectPostCnt(memberNo);
	}
	
	/** ���� �� ��� ��  */
	public int getMyCommentCnt(int memberNo) {
		return memDao.selectCommentCnt(memberNo);
	}
	
	/**
	 * ȸ�� Ż��  
	 * @param memberId
	 * @return Ż������ ��� ��ȯ 
	 */
	public int deleteMember(int memberNo) {
		return memDao.delete(memberNo);
	}
	
	/**
	 * �Խ��� ��ü ��ȸ
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

}









