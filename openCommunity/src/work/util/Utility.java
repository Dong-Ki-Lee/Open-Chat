package work.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	/** 보안 문자 정보 출력 메서드 - String 클래스 이용*/
	public String convertSecureString(String memberPw) {
		String subFirstPw = memberPw.substring(0, 2);
		String subRemainPw = memberPw.substring(2);
		for (int index = 0; index < subRemainPw.length(); index++) {
			subFirstPw += "*";
		}
		return subFirstPw;
	}
	
	/** 임의의 보안 문자열 반환 메서드 : 기본길이 4 */
	public String getSecureString() {
		String password = "";
		for (int index = 0; index < 4; index++) {
			password += String.valueOf((int)(Math.random() * 10));
		}
		return password;
	}
	
	/** 임의의 보안 문자열 반환 메서드 : length */
	public String getSecureString(int length) {
		String password = "";
		for (int index = 0; index < length; index++) {
			password += String.valueOf((int)(Math.random() * 10));
		}
		return password;
	}
	
	/** 현재날짜를 기본형식의 문자열로 반환하는 메서드 : 아규먼트 String 타입*/
	public String getCurrentDay(String date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
		Date day = new Date();
		try {
			day = simple.parse(date);
		} catch (ParseException e) {
//			System.out.println("Error");
		}
		return simple.format(day);
		
	}
	
	/** 현재날짜를 기본형식의 문자열로 반환하는 메서드 : 아규먼트 Date 타입*/
	public String getCurrentDay(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
		return simple.format(date);
	}
}
