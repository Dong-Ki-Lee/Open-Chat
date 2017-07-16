/**
 * 
 */
package work.model.dto;

import java.io.Serializable;

/**
 * @author limjinha
 *
 */
public class MembersInfo implements Serializable {
	
	/** ȸ�� ��ȣ */
	private int memberNo;
	
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
	 * @return the memberNo
	 */
	public int getMemberNo() {
		return memberNo;
	}


	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MembersInfo [memberNo=" + memberNo + ", joinDate=" + joinDate + ", lastLoginDate=" + lastLoginDate
				+ ", mileage=" + mileage + "]";
	}

}
