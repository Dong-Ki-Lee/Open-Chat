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
	
	/** ��� ȸ���� ��ȸ �޼��� */
	public int getCount() {
		return 0;
	}
	
	/**
	 * ȸ�� Ż��  
	 * @param memberId
	 * @return Ż������ ��� ��ȯ 
	 */
	public int deleteMember(String memberId) {
		return 0;
	}
	
	/**
	 * ��ü ȸ�� ��ȸ 
	 * @return ȸ�� ��ü �迭 
	 */
	public ArrayList<MembersInfo> getMember() {
		return null;
	}
	
	/**
	 * �α���
	 */
	public int selectOneLogin(String memberEmail, String memberPw) {
		if (memDao.loginCheck(memberEmail, memberPw) != 0) {
			return memDao.loginCheck(memberEmail, memberPw);
		}
		return 0;
	}
	
//	
//case "boardList":	// 7. �Խ��� ��ü ���� ��ȸ ����
//	boardList(request, response);
//	break;
//case "disPostList":	// 8. �Ű� �Խñ� ���� ��ȸ ����
//	disPostList(request, response);
//	break;
//case "memberList":	// 9. ��� ȸ�� ��ȸ ����
//	memberList(request, response);
//	break;
//case "newMemberList":	// 10. �ű԰��� ȸ�� ��ȸ ����
//	newMemberList(request, response);
//	break;
//case "deleteMember":	// 11. ȸ�� ���� ����
//	deleteMember(request, response);
//	break;
	
}









