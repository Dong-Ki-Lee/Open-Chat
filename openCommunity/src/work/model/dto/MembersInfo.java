/**
 * 
 */
package work.model.dto;

import java.io.Serializable;
import work.model.dto.Members;

/**
 * @author limjinha
 *
 */
public class MembersInfo extends Members implements Serializable {
	
	/** ȸ�� ������ ���� */
	private String joinDate;
	
	/** ȸ�� ������ �α��� ���� */
	private String lastLoginDate;
	
	/** ȸ�� ���ϸ��� ���� */
	private int mileage;

	
	/**
	 * ȸ�� ���� �⺻ ������
	 */
	public MembersInfo() {
		super();
	}

	/**
	 * @param joinDate
	 * @param lastLoginDate
	 * @param mileage
	 */
	public MembersInfo(int memberNo, String memberEmail, String memberNickname, String memberPw, String joinDate, String lastLoginDate, int mileage) {
		super(memberNo, memberEmail, memberNickname, memberPw);
		this.joinDate = joinDate;
		this.lastLoginDate = lastLoginDate;
		this.mileage = mileage;
	}

	/**
	 * @return the joinDate
	 */
	public String getJoinDate() {
		return joinDate;
	}


	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	/**
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}


	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}


	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

}
