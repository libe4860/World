package com.webapp.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	/*
	 * 문자열 검색, 대체, 추출 ==> 정규표현식(Regular Expression)
	 * 
	 * . ==> new line 문자를 제외한 모든 문자
	 * ^ ==> Line Start 라인의 시작을 알림
	 * $ ==> Line End 라인의 끝을 알림
	 * * ==> 앞문자가 0개 이상 반복(무한대)
	 * + ==> 앞문자가 1개 이상 반복(무한대)
	 * [AYZ] ==> []안에 기재된 'A','Y','Z'만 오는 것만 허용
	 * //A부터Z까지 올 수 있는 문자 테스트, 한 문자씩만 허용가능/A-true, AB-false, B-true 
	 * {} ==> 앞문자가 n개 반복
	 * {n,} ==> 앞문자가 n개 이상 반복 
	 * {n,m} ==> 앞문자가 n <= 문자갯수 <= m
	 */
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//한 라인에 대해 표현식 적용 
//		String regex = "^.$"; //한 문자만 인식하는 테스트
//		String regex = "^A*$"; //A라는 문자가 0개 이상 반복되는 테스트/빈 공백상태enter-true, A-true, AAA-true, AB-false
//		String regex = "^A+$"; //A라는 문자가 1개 이상 반복되는 테스트/빈 공백상태enter-false, A-true, AAA-true, AB-false
//		String regex = "^[A-Z0-9]{3}$"; //A부터Z까지, 0부터 9까지의 숫자중 조합해서 3자리까지 입력가능함 
//		String regex = "^[A-Z0-9]{3,5}$"; //A부터Z까지, 0부터 9까지의 숫자중 조합해서 3자리에서 5자리까지 입력가능함
//		String regex = "^[가-힣]{3,5}$"; //한글로만 이루어져있고 3자리에서 5자리까지 입력가능함
		String regex = "[A-Za-z]{3}"; //한 라인에서 영문자가 3자리이상 영문자가 있으면 true
		while(true){
			String line = scan.nextLine();
			System.out.println("line = [" + line + "]");
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(line);
			System.out.println("match = " + m.find());
			//한 라인에 대한 매칭, .은 한 글자만 인식함 / matches : 시작과 끝을 인식함 
//			System.out.println("match = " + Pattern.matches(regex, line)); //boolean return
		}
	}

}
