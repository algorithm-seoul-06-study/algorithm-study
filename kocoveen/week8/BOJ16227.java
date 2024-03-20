package week8;

import java.util.*;

public class BOJ16227 {

    static final int INF = 987654321;
    static int n, k, u, v, t, size;
    static List<Info>[] graph;

    static class Info {
        int idx, cost, mileage;
        public Info(int idx, int cost, int mileage) {
            this.idx = idx;
            this.cost = cost;
            this.mileage = mileage;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        size = n + 2;
        k = sc.nextInt();

        graph = new List[size];
        for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
        }

        while (k-- > 0) {
            u = sc.nextInt();
            v = sc.nextInt();
            t = sc.nextInt();
            if (t > 100) continue;
            graph[u].add(new Info(v, t, 0));
            graph[v].add(new Info(u, t, 0));
        }

        int enCosts = dijkstra(0, n + 1, 100);
        System.out.println(enCosts);
    }

    private static int dijkstra(int st, int en, int maxTime) {
        int[][] costs = new int[size][maxTime + 1];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= maxTime; j++) {
                costs[i][j] = INF;
            }
        }
        costs[st][0] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.cost - i2.cost);
        pq.add(new Info(0, 0, 0));

        while (!pq.isEmpty()) {
            Info cur = pq.remove();

            int curIdx = cur.idx;
            int curMileage = cur.mileage;

            if (costs[curIdx][curMileage] < cur.cost) continue;

            for (Info nxt : graph[curIdx]) {
                int nxtIdx = nxt.idx;
                int nxtCost = nxt.cost;
                int nxtMileage = curMileage + nxtCost;

                if (maxTime >= nxtMileage) {
                    if (costs[nxtIdx][nxtMileage] > costs[curIdx][curMileage] + nxtCost) {
                        costs[nxtIdx][nxtMileage] = costs[curIdx][curMileage] + nxtCost;
                        pq.add(new Info(nxtIdx, costs[nxtIdx][nxtMileage], nxtMileage));
                    }
                } else {
                    nxtMileage = nxtCost;
                    if (costs[nxtIdx][nxtMileage] > costs[curIdx][curMileage] + nxtCost + 5) {
                        costs[nxtIdx][nxtMileage] = costs[curIdx][curMileage] + nxtCost + 5;
                        pq.add(new Info(nxtIdx, costs[nxtIdx][nxtMileage], nxtMileage));
                    }
                }

            }
        }

        int min = INF;
        for (int i = 0; i <= maxTime; i++) {
            min = Math.min(min, costs[en][i]);
        }
        return min;
    }
}