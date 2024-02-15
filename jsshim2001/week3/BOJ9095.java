package algorithm;

import java.util.Scanner;

public class BOJ9095 {
    static int[] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        memo = new int[11]; // memoization 이용, 초기값 설정 1,2,3일 때
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            System.out.println(result(n));
        }

    }

    static int result(int n) { // 재귀함수로 앞에 3개 더한 값과 같음
        if (memo[n] != 0)
            return memo[n];
        memo[n] = result(n - 1) + result(n - 2) + result(n - 3);
        return memo[n];

    }
}
