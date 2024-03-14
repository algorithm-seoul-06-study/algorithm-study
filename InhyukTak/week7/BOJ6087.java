package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6087 {
	static char[][] map;
	// C의 위치를 저장할 배열
	static int[][] placeC;
	// 상 좌 하 우
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		placeC = new int[2][2];
		for (int i = 0, idx = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < map[0].length; j++) {
				// C 찾으면 placeC 배열에 저장
				if (map[i][j] == 'C') {
					placeC[idx][0] = i;
					placeC[idx++][1] = j;
				}
			}
		}
		
		// 도착 지점의 C를 최대값으로 설정
		map[placeC[1][0]][placeC[1][1]] = Character.MAX_VALUE;
		// 출발 시 각 방향에 대해서 반복
		for (int i = 0; i < dr.length; i++) {
			// 출발 지점에서 해당 방향으로 함수 실행
			fill(placeC[0][0], placeC[0][1], i, 0);
		}
		// 도착 지점 C 위치의 값을 char -> int 해서 출력
		System.out.println(map[placeC[1][0]][placeC[1][1]] - 48);
		
	}
	
	static void fill(int r, int c, int di, int mirror) {
		// di 방향으로 한 칸 이동
		r += dr[di];
		c += dc[di];
		// 이동할 곳이 경계 조건을 만족하면서 벽이 아니면 그 방향으로 계속 반복
		while(r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c] != '*') {
			// 이동할 곳이 . 이거나 mirror보다 작은 수면
			if (map[r][c] == '.' || map[r][c] > mirror + 48) {
				// 도착 지점의 C에 도달했는지
				if (r == placeC[1][0] && c == placeC[1][1]) {
					// 도달했으면 mirror가 더 작으면 mirror 대입
					if (map[placeC[1][0]][placeC[1][1]] >= mirror + 48) {
						map[placeC[1][0]][placeC[1][1]] = (char) (mirror + 48);
					}
					// 함수 종료
					return;
				}
				// 대입
				map[r][c] = (char) (mirror + 48);
				// 방향 꺾어서 mirror+1 해서 재귀
				fill(r, c, (di+1)%4, mirror+1);
				fill(r, c, (di+3)%4, mirror+1);
			}
			// di 방향으로 한 칸 이동
			r += dr[di];
			c += dc[di];
		}
	}
}
