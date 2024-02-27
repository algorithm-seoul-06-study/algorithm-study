package week5;

public class BOJ1932 {

    static int n;
    static int[][] tri = new int[505][505]; // tri: 정수 삼각형 저장 배열
    static Integer[][] dp = new Integer[505][505]; // dp: dp[i][j] = dp[i][j] 까지 더해서 내려온 경로 중 가장 큰 값

    public static void main(String[] args) throws Exception {
        n = read();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                tri[i][j] = read();
            }
        }
        System.out.println(findMax(1, 1));
    }

    // findMax(int i, int j) : dp[i][j] 까지 더해서 내려온 경로 중 가장 큰 값
    public static int findMax(int i, int j) {
        if (i == n + 1) return 0; // 만약 i가 끝 행을 넘어갔다면, 0을 반환(멈추는 위치에 영향이 없는 값이아야 하기 떄문)
        if (dp[i][j] == null) dp[i][j] = Math.max(findMax(i + 1, j), findMax(i + 1, j + 1)) + tri[i][j];
        // f(i, j) = max( f(i + 1, j), f(i + 1, j + 1) ) + a_ij;
        return dp[i][j];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
