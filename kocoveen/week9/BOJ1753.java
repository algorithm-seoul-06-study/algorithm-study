package week9;

import java.io.*;
import java.util.*;

public class BOJ1753 {
    static int V, E, start; // V: 정점 수, E: 간선 수, start: 시작 정점
    static ArrayList<ArrayList<Node>> graph; // 그래프

    // idx: 다음 노드 인덱스, cost: 다음 노드로 가는데 필요한 비용
    static class Node {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        int[] dist = dijkstra();

        // 결과 출력
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    private static int[] dijkstra() {
        int[] dist = new int[V + 1]; // 최소 비용을 저장할 배열
        for (int i = 1; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        // 최소 비용 추출 큐
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        // 시작 정점 삽입
        q.offer(new Node(start, 0));

        // 처음 노드는 0의 거리
        dist[start] = 0;

        while (!q.isEmpty()) {
            Node curNode = q.remove();

            // 목표 정점 도착시 바로 탈출하는 코드, but 여기는 전체 정점의 최소 거리를 구함
//			if (curNode.idx == end) {
//				System.out.println(dist[curNode.idx]);
//				return;
//			}

            // 현재 선택된 정점까지의 거리가 이미 적혀진 정점까지의 거리보다 크면, 선택된 거리는 버림
            if (dist[curNode.idx] < curNode.cost) continue;

            // 선택된 노드의 모든 주변 노드를 고려
            for (Node nxtNode : graph.get(curNode.idx)) {
                // 적혀진 선택된 정점에서 다음에 갈 정점까지의 거리 > 현재 선택된 정점까지의 거리 + 선택된 정점에서 다음에 갈 정점까지의 거리
                if (dist[nxtNode.idx] > curNode.cost + nxtNode.cost) {
                    // 갱신
                    dist[nxtNode.idx] = curNode.cost + nxtNode.cost;
                    // 갱신된 경우에만 큐에 삽입
                    q.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                }
            }
        }

        return dist;
    }
}