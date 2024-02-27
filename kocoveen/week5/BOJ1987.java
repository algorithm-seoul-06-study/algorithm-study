package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1987 {

    static int R, C, mx;
    static char[][] map; // 표 모양 보드
    static int visitedBit; // A~Z 를 지났는지 체크하는 비트
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        R = Integer.parseInt(token[0]);
        C = Integer.parseInt(token[1]);

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0); // 0, 0 부터 시작
        System.out.println(mx);
    }

    private static void dfs(int r, int c) {
        int curVisitedBit = 1 << (map[r][c] - 'A');
        visitedBit |= curVisitedBit; // 현재 위치의 알파벳을 지났다고 체크
        mx = Math.max(mx, count(visitedBit)); // 현재 경로에서 지난 알파벳의 수와 비교
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || R <= nr || nc < 0 || C <= nc) continue;
            int nxtVisitedBit = 1 << (map[nr][nc] - 'A');
            if ((visitedBit & nxtVisitedBit) == nxtVisitedBit) continue; // 만약 지나갈 곳의 알파벳을 이미 지났다면 continue;
            visitedBit |= nxtVisitedBit; // 갈 곳의 알파벳 체크
            dfs(nr, nc); // 다음 스텝
            visitedBit &= (~curVisitedBit); // 현재 스텝이 끝났으면 방문 표시 해제
        }
    }

    // 지난 알파벳의 수를 계산
    private static int count(int visitedBit) {
        return Integer.bitCount(visitedBit);
    }
}
