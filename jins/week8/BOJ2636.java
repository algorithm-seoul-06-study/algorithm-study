package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {

	static int[] dx = { 0, 1, 0, -1 }; // 상하좌우 이동을 위한 x 좌표 변화량
	static int[] dy = { 1, 0, -1, 0 }; // 상하좌우 이동을 위한 y 좌표 변화량
	
	static boolean[][] visited; // 방문 여부를 저장하는 배열
	static int[][] board; // 치즈 판의 상태를 저장하는 배열
	
	static int n, m, cheese; // 치즈 판의 크기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		cheese = 0;

		// 총 치즈의 갯수 세기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1)
					cheese++;
			}
		}

		int count = 0; // 이전 시간대의 치즈 개수
		int time = 0; // 치즈가 모두 녹는데 걸리는 시간

		// 치즈가 모두 녹을 때까지 반복
		while (cheese != 0) {
			
			// 현재 치즈 갯수
			count = cheese;
			
			// 녹는시간 추가
			time++;
			// 시간별 치즈판 초기화
			visited = new boolean[n][m];
			
			bfs();
			
		}

		System.out.println(time);
		System.out.println(count);
		
	}

	// BFS
	public static void bfs() {
		
		Queue<int[]> start = new LinkedList<>();
		
		// 출발 좌표
		start.offer(new int[] { 0, 0 });
		
		// 출발 지점 방문 표시
		visited[0][0] = true;

		while (!start.isEmpty()) {
			
			int[] now = start.poll();

			// 상하좌우 이동
			for (int i = 0; i < 4; i++) {
				
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				// 다음 위치가 범위 내에 있고, 아직 방문하지 않았다면
				if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
					
					visited[nx][ny] = true; // 방문 표시
					
					if (board[nx][ny] == 0) { // 치즈가 아닌 경우
						
						start.offer(new int[] { nx, ny }); // 큐에 추가하여 탐색 계속
						
					} else { // 치즈가 있는 경우
						
						// 치즈 개수 감소
						cheese--;
						
						// 치즈 제거
						board[nx][ny] = 0;
						
					}
					
				}
				
			}
			
		}
		
	}
}
