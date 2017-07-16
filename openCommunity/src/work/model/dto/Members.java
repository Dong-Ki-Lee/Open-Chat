package work.model.dto;

import java.io.Serializable;

public class Members implements Serializable {
	
	/** ȸ�� ��ȣ */
	private int memberNo;
	/** ȸ�� �̸��� */
	private String memberEmail;
	/** ȸ�� ���� */
	private String memberNickname;
	/** ȸ�� ��й�ȣ */
	private String memberPw;
	
	public Members() {}
	
	// ��ü ��ȸ ����
	 public Members(int memberNo, String memberEmail, String memberNickname){
		 this.memberNo = memberNo;
		 this.memberEmail = memberEmail;
		 this.memberNickname = memberNickname;
	 }
	// ��ü ������
	public Members(int memberNo, String memberEmail, String memberNickname, String memberPw) {
		super();
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberNickname = memberNickname;
		this.memberPw = memberPw;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
}
