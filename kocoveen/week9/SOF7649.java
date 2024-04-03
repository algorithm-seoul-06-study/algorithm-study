package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SOF7649 {
    static int n, m, mx; // mx: 최대 행복 지수
    static String S; // S: 가장 좋아하시는 여행 경로
    static List<Info>[] graph;
    static boolean[] vis; // 백트래킹 방문 체크

    // e: 여행 장소, r: 여행 루트
    static class Info {
        int e;
        String r;

        Info(int e, String r) {
            this.e = e;
            this.r = r;
        }
    }

    static int[][] dp; // LCS를 위한 dp 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        S = br.readLine();

        dp = new int[S.length() + 1][n];

        graph = new List[n];
        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            String r = st.nextToken();

            graph[u].add(new Info(v, r));
            graph[v].add(new Info(u, r));
        }

        vis[0] = true;
        backtracking(0, new StringBuilder());

        System.out.println(mx);
    }

    static void backtracking(int cur, StringBuilder B) {
        // 현재 장소에서 더 이상 갈 곳이 없다면
        if (isAllVisited(cur)) {
            return;
        }

        for (Info nxt : graph[cur]) {
            if (vis[nxt.e]) continue;

            vis[nxt.e] = true;

            StringBuilder tmp = B.append(nxt.r); // 여행 루트 추가

            mx = Math.max(mx, LCS(tmp.toString(), tmp.length())); // 여행 루트로 LCS 실행 후, 비교
            backtracking(nxt.e, tmp);

            B.deleteCharAt(B.length() - 1); // 최근에 간 여행 루트 삭제

            vis[nxt.e] = false;
        }
    }

    /**
     * 현재 cur 장소에서 갈 곳이 있는지 확인하는 메서드
     * @param cur : 현재 여행 장소
     * @return true : 갈 곳 존재, false: 갈 곳 부재
     */
    static boolean isAllVisited(int cur) {
        for (Info nxt : graph[cur]) {
            if (!vis[nxt.e]) return false;
        }
        return true;
    }

    /**
     * 가장 좋아하는 여행 루트와 현재 고른 여행 루트를 LCS하는 메서드
     * @param B : 여행 루트
     * @param j : B.length()
     * @return dp[A.length()][B.length()]
     */
    static int LCS(String B, int j) {
        for (int i = 1; i <= S.length(); i++) {
            if (S.charAt(i-1) == B.charAt(j-1)) dp[i][j] = dp[i - 1][j - 1] + 1;
            else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
        return dp[S.length()][B.length()];
    }
}
