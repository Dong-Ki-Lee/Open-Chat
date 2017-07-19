package work.model.service;

import java.util.ArrayList;

import work.model.dao.MembersDao;
import work.model.dao.MembersDao;
import work.model.dto.Members;
import work.model.dto.MembersInfo;

/**
 * @author limjinha
 *
 */
public class AdminService {
	private MembersDao memDao = new MembersDao();
	
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
	public ArrayList<MembersInfo> getMember() {
		return null;
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









