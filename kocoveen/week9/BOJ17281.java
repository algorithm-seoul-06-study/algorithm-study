package week9;

public class BOJ17281 {

    static int n;
    static int[][] players; // players[i][j]: i+1 이닝에 j번 타자가 얻는 결과;
    // 0 -> 아웃, 1 -> 안타, 2 -> 2루타, 3 -> 3루타, 4 -> 홈런
    static int[] battingOrder = new int[9]; // 타순
    static boolean[] visited = new boolean[9]; // 타자 방문 체크
    static int mxRun; // 최대 점수

    public static void main(String[] args) throws Exception {
        n = read();
        players = new int[n][9];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 9; j++) {
                players[i][j] = read();
            }
        }

        visited[0] = true;
        backtracking(0); // zero index
        System.out.println(mxRun);
    }

    private static void backtracking(int depth) {
        if (depth == 9) {
            int run = getRunByCurrentBattingOrder(battingOrder);
            mxRun = Math.max(mxRun, run);
            return;
        }

        // 4번 타자일 때는 넘어감
        if (depth == 3) {
            dfs(depth + 1);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            battingOrder[depth] = i;
            dfs(depth + 1);
            visited[i] = false;
        }

    }

    /**
     * 현재 타순(battingOrder)으로 n이닝 까지의 점수를 구하는 메서드
     * @param battingOrder : 타순
     * @return run : 점수
     */
    static int getRunByCurrentBattingOrder(int[] battingOrder) {
        int run = 0; // 점수
        int out; // 아웃
        int idx = 0; // 게임 첫 타자

        for (int inning = 0; inning < n; inning++) {
            out = 0; // 아웃 초기화
            int base = 0; // 3루, 2루, 1루, 홈 상태 초기화

            // 3아웃이 되기 전까지
            while (out < 3) {
                int batting = players[inning][battingOrder[idx]];
                if (batting == 0) {
                    out++;
                } else {
                    base = (base + 1) << batting; // 루타만큼 비트 이동
                    run += Integer.bitCount(base >> 4); // 뒤에서 4번째 비트보다 큰 비트들의 1을 세어, 점수에 합
                    base %= (1 << 4); // 뒤에서 4번째 비트보다 왼쪽의 비트(수가 큰 비트)는 전부 0으로 바꿈
                }
                // 다음 타자로
                idx = (idx + 1) % 9;
            }
        }
        return run;
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}