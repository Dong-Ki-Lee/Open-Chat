package work.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	/** ���� ���� ���� ��� �޼��� - String Ŭ���� �̿�*/
	public String convertSecureString(String memberPw) {
		String subFirstPw = memberPw.substring(0, 2);
		String subRemainPw = memberPw.substring(2);
		for (int index = 0; index < subRemainPw.length(); index++) {
			subFirstPw += "*";
		}
		return subFirstPw;
	}
	
	/** ������ ���� ���ڿ� ��ȯ �޼��� : �⺻���� 4 */
	public String getSecureString() {
		String password = "";
		for (int index = 0; index < 4; index++) {
			password += String.valueOf((int)(Math.random() * 10));
		}
		return password;
	}
	
	/** ������ ���� ���ڿ� ��ȯ �޼��� : length */
	public String getSecureString(int length) {
		String password = "";
		for (int index = 0; index < length; index++) {
			password += String.valueOf((int)(Math.random() * 10));
		}
		return password;
	}
	
	/** ���糯¥�� �⺻������ ���ڿ��� ��ȯ�ϴ� �޼��� : �ƱԸ�Ʈ String Ÿ��*/
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
	
	/** ���糯¥�� �⺻������ ���ڿ��� ��ȯ�ϴ� �޼��� : �ƱԸ�Ʈ Date Ÿ��*/
	public String getCurrentDay(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
		return simple.format(date);
	}
}
