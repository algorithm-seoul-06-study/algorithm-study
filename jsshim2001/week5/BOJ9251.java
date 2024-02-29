import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		boolean[][] table = new boolean[str1.length()][str2.length()];

		for (int i = 0; i < str1.length(); i++) { // 같은거 다른거 true/false table 만들기
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					table[i][j] = true;
				} else {
					table[i][j] = false;
				}
			}
		}

		int[][] resulttable = new int[str1.length()][str2.length()]; // table과 같은 크기의 resulttable
		int cnt = 0;
		for (int i = 0; i < str2.length(); i++) { // 첫 번째 row 채우기
			if (table[0][i]) {
				cnt = 1;
			}
			resulttable[0][i] = cnt;
		}

		cnt = 0;
		for (int i = 0; i < str1.length(); i++) { // 첫 번째 column 채우기
			if (table[i][0]) {
				cnt = 1;
			}
			resulttable[i][0] = cnt;
		}
		
		for (int i = 1; i < str1.length(); i++) { // table 위치에서 true일 경우 대각선 값+1, 그렇지 않으면 위나 옆에 값 중 큰 값을 가져옴
			for (int j = 1; j < str2.length(); j++) {
				if (table[i][j]) {
					resulttable[i][j] = resulttable[i - 1][j - 1]+1;
				} else {
					resulttable[i][j] = Math.max(Math.max(resulttable[i][j - 1], resulttable[i - 1][j]),
							resulttable[i - 1][j - 1]);
				}
			}
		}

		System.out.println(resulttable[str1.length() - 1][str2.length() - 1]); // table 가장 우하단의 값이 가장 큼
	}
}
