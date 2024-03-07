package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {

    static int n, C = 1000000;
    static int[] pw;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        n = s.length();
        pw = new int[n + 1];
        dp = new int[n + 1];
        for (int i = 1; i <= n; i++) pw[i] = s.charAt(i - 1) - '0';

        dp[0] = 1;

        for (int i = 1; i <= n; i++){
            if (pw[i] > 0) dp[i] = dp[i - 1] % C;
            int x = pw[i - 1] * 10 + pw[i];
            if (x >= 10 && x <= 26) dp[i] = (dp[i] + dp[i - 2]) % C;
        }

        System.out.println(dp[n]);
    }
}
