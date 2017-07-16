package work.model.dto;

import java.io.Serializable;

public class Members implements Serializable {
	
	/** 회원 번호 */
	private int memberNo;
	/** 회원 이메일 */
	private String memberEmail;
	/** 회원 별명 */
	private String memberNickname;
	/** 회원 비밀번호 */
	private String memberPw;
	
	public Members() {}
	
	// 전체 조회 관련
	 public Members(int memberNo, String memberEmail, String memberNickname){
		 this.memberNo = memberNo;
		 this.memberEmail = memberEmail;
		 this.memberNickname = memberNickname;
	 }
	// 전체 생성자
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
