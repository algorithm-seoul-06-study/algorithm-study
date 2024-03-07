import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] letters = br.readLine().toCharArray();

		if (letters[0] == '0') {
			System.out.println(0);
			return;
		}
		
		long[] dp = new long[letters.length];
		for (int i = 0; i < dp.length; i++) {
			if (i == 0) {
				dp[i] = 1;
			}else if(i==1) {
				if(Integer.parseInt(letters[i - 1] + "" + letters[i]) <= 26) dp[i]++;
				if(letters[i]!='0') dp[i]++;
			}else {
				if (letters[i] == '0'
						&& (letters[i - 1] == '0' || Integer.parseInt(letters[i - 1] + "" + letters[i]) > 26)) {
					System.out.println(0);
					return;
				}

				if (letters[i] != '0') {
					dp[i] += dp[i - 1];
				}
				if (Integer.parseInt(letters[i - 1] + "" + letters[i]) <= 26 && letters[i - 1] != '0') {
					dp[i] += dp[i - 2];
				}
				
				dp[i] %= 1000000;
			}
		}

		System.out.println(dp[dp.length - 1] % 1000000);
	}
}