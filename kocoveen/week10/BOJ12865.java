package week10;

public class BOJ12865 {

    static int n, k;              // n: 물품 수, k: 버티는 무게
    static int[] weights, values; // weights[i]: i번째 물품의 무게, values[i]: i번째 물품의 가치
    static int[][] dp;            // dp 배열

    public static void main(String[] args) throws Exception {
        n = read(); k = read();

        weights = new int[n + 1];
        values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int w = read();
            int v = read();
            weights[i] = w;
            values[i] = v;
        }

        dp = new int[n + 1][k + 1];

        // i번째 물품과 무게 j에서 가장 많은 가치는 f(i, j) = max( f(i-1, j), f(i-1, j-W(i)) + V(i));
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= weights[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                else dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[n][k]);
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
