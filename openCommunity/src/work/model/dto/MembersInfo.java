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
	
	/** 회원 가입일 정보 */
	private String joinDate;
	
	/** 회원 마지막 로그인 정보 */
	private String lastLoginDate;
	
	/** 회원 마일리지 정보 */
	private int mileage;

	
	/**
	 * 회원 정보 기본 생성자
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
