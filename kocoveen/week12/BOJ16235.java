package week12;

import java.util.PriorityQueue;

public class BOJ16235 {

    static int n, m, k;
    static int[][] food, A;

    static PriorityQueue<Integer>[][] tree, dead;

    static int[] dr = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        n = read(); m = read(); k = read();

        food = new int[n][n];
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                food[i][j] = 5;
                A[i][j] = read();
            }
        }

        tree = new PriorityQueue[n][n];
        dead = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tree[i][j] = new PriorityQueue<>();
                dead[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < m; i++) {
            int r = read() - 1;
            int c = read() - 1;
            int a = read();
            tree[r][c].add(a);
        }

        while (k-- > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    PriorityQueue<Integer> tmp = new PriorityQueue<>();
                    while (!tree[i][j].isEmpty()) {
                        int cur = tree[i][j].remove();
                        if (food[i][j] >= cur) {
                            food[i][j] -= cur;
                            tmp.add(cur + 1);
                        } else {
                            tree[i][j].add(cur);
                            break;
                        }
                    }
                    dead[i][j].addAll(tree[i][j]);
                    tree[i][j].clear();
                    tree[i][j].addAll(tmp);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    while (!dead[i][j].isEmpty()) {
                        int cur = dead[i][j].remove();
                        food[i][j] += (cur / 2);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int t : tree[i][j]) {
                        if (t % 5 == 0) {
                            for (int k = 0; k < 8; k++) {
                                int nr = dr[k] + i;
                                int nc = dc[k] + j;
                                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                                tree[nr][nc].add(1);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    food[i][j] += A[i][j];
                }
            }
        }

        int size = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                size += tree[i][j].size();
            }
        }
        System.out.println(size);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
