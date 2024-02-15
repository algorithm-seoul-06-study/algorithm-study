package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자열
		String str = br.readLine();
		// 폭발물
		String bomb = br.readLine();

		// 폭발물 제거 후의 문자열을 저장할 배열
		char[] result = new char[str.length()];

		// result 배열 인덱스
		int index = 0;

		for (int i = 0; i < str.length(); i++) {
			// 현재 문자를 result 배열에 저장
			result[index] = str.charAt(i);
			// 폭발물 체크, 폭발물이 범위만큼 문자 및 인덱스 감소
			if (isBomb(result, index, bomb))
				index -= bomb.length();
			// result 배열의 인덱스를 증가하여 다음 문자를 저장할 위치로 이동
			index++;
		}

		StringBuilder answer = new StringBuilder("");

		for (int i = 0; i < index; i++) {
			answer.append(result[i]);
		}

		System.out.println((answer.length() == 0) ? "FRULA" : answer);
	}

	private static boolean isBomb(char[] result, int index, String bomb) {
		
		// 문자열이 폭발물 길이보다 짧으면 X
		if (index < bomb.length() - 1)
			return false;

		// 폭발물 체크
		for (int i = 0; i < bomb.length(); i++) {
			if (bomb.charAt(i) != result[index - bomb.length() + 1 + i])
				return false;
		}

		return true;

	}

}
