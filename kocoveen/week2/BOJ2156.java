package week2;

public class BOJ2156 {
    static int[] arr; // 포도주가 있는 위치
    static Integer[] dp;

    public static void main(String[] args) throws Exception {
        int n = read();
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = read();
        }

        dp = new Integer[n + 1];
        System.out.println(func(n));
    }

    // dp[i]를 구하는 메서드 (3연속 먹지 못함)
    public static int func(int i) {
        if (i <= 0) return 0; // 경계조건 1
        if (i == 1) return arr[1]; // 경계조건 2

        if (dp[i] == null) {
            dp[i] = max(
                    func(i - 2) + arr[i], // 포도주[i]를 먹을 수 있고, 한 칸을 건너 뛴 경우
                    func(i - 3) + arr[i - 1] + arr[i], // 포도주[i]를 먹고, 인접한 포도주[i-1]를 먹어 포도주[i-3]을 먹는 경우
                    func(i - 1) // 포도주[i]를 못 먹는 경우
            );
        }
        return dp[i];
    }

    // 최대를 구하는 메서드
    private static int max(int... nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        return max;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
