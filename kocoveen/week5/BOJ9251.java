package week5;

import java.util.Scanner;

import static java.lang.Math.*;

public class BOJ9251 {

    static char[] s1, s2;
    static Integer[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s1 = sc.next().toCharArray();
        s2 = sc.next().toCharArray();

        dp = new Integer[s1.length][s2.length];
        System.out.println(findMax(s1.length - 1, s2.length - 1));
    }


    // Recursive
    private static Integer findMax(int i1, int i2) {
        if (i1 < 0 || i2 < 0) return 0;
        if (dp[i1][i2] == null) {
            if (s1[i1] != s2[i2]) dp[i1][i2] = max(findMax(i1 - 1, i2), findMax(i1, i2 - 1));
            else dp[i1][i2] = findMax(i1 - 1, i2 - 1) + 1;
        }
        return dp[i1][i2];
    }
}
