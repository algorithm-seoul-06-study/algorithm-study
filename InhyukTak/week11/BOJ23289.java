import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ23289 {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int R, C, K, W;
	static int[][][] map;
	static List<Node> heaters;
	static List<Node> checkPoints;
	static int[][] walls;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로 길이
		C = Integer.parseInt(st.nextToken()); // 가로 길이
		K = Integer.parseInt(st.nextToken()); // 검사 온도
		
		map = new int[3][R][C]; // [0] : 입력값, [1] : 온풍기, [2] : 온도 조절 및 테두리 온도 감소
		heaters = new ArrayList<>(); // 히터 위치 저장 배열
		checkPoints = new ArrayList<>(); // 검사 위치 저장 배열
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[0][r][c] = Integer.parseInt(st.nextToken());
				
				if (map[0][r][c] >= 1 && map[0][r][c] <= 4) {
					heaters.add(new Node(r, c)); // 히터 위치 저장
				}
				
				if (map[0][r][c] == 5) {
					checkPoints.add(new Node(r, c)); // 검사 위치 저장
				}
			}
		}
		
		W = Integer.parseInt(br.readLine()); // 벽 개수
		walls = new int[W][3]; // 벽 정보 배열
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			walls[i][0] = Integer.parseInt(st.nextToken()) - 1; // x
			walls[i][1] = Integer.parseInt(st.nextToken()) - 1; // y
			walls[i][2] = Integer.parseInt(st.nextToken()); // t
		}
		
		int choco = 0;
		
		while (!checkK()) {
			// 초콜릿 100개 넘기면 나오기
			if (choco > 100) {
				break;
			}
			
			// 1. 온풍기 on // fin
			map[1] = new int[R][C];
			heaterOn();
			
			// 2. 온도 조절
			map[2] = new int[R][C];
			control();
			
			// 3. 바깥쪽 감소
			edge();
			
			// 4. 초콜릿 먹기
			choco++;
		}
		
		System.out.println(choco);
		
	}
	
	// 검사 온도 통과 못한 지역 있으면 false / 다 통과하면 true
	public static boolean checkK() {
		for (Node node : checkPoints) {
			int r = node.r;
			int c = node.c;
			if (map[2][r][c] < K) {
				return false;
			}
		}
		return true;
	}
	
	// 테두리 온도 1씩 낮추기
	public static void edge() {
		for (int r = 0; r < R; r++) {
			if (map[2][r][0] > 0) {
				map[2][r][0]--;
			}
			if (map[2][r][C-1] > 0) {
				map[2][r][C-1]--;
			}
		}
		for (int c = 1; c < C - 1; c++) {
			if (map[2][0][c] > 0) {
				map[2][0][c]--;
			}
			if (map[2][R-1][c] > 0) {
				map[2][R-1][c]--;
			}
		}
	}
	
	// 온도 조절 메서드
	public static void control() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[2][r][c] += map[1][r][c];
				outc:
				for (int di = 0; di < dc.length; di++) {
					int nr = r + dr[di];
					int nc = c + dc[di];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[1][r][c] - map[1][nr][nc] >= 4) {
						int tmp = (map[1][r][c] - map[1][nr][nc]) / 4;
						// 방향마다 벽 확인
						switch (di) {
						case 0:
							for (int i = 0; i < walls.length; i++) {
								if (walls[i][0] == r && walls[i][1] == c && walls[i][2] == 0) {
									continue outc;
								}
							}
							break;
						case 1:
							for (int i = 0; i < walls.length; i++) {
								if (walls[i][0] == r + 1 && walls[i][1] == c && walls[i][2] == 0) {
									continue outc;
								}
							}
							break;
						case 2:
							for (int i = 0; i < walls.length; i++) {
								if (walls[i][0] == r && walls[i][1] == c - 1 && walls[i][2] == 1) {
									continue outc;
								}
							}
							break;
						case 3:
							for (int i = 0; i < walls.length; i++) {
								if (walls[i][0] == r && walls[i][1] == c && walls[i][2] == 1) {
									continue outc;
								}
							}
							break;

						default:
							break;
						}
						// 벽 없으면 온도 조절 진행
						map[2][nr][nc] += tmp;
						map[2][r][c] -= tmp;
					}
				}
			}
		}
	}
	
	// 온풍기 작동 메서드
	public static void heaterOn() {
		for (int i = 0; i < heaters.size(); i++) {
			Node node = heaters.get(i);
			int r = node.r;
			int c = node.c;
			visited = new boolean[R][C];
			doHeat(r, c, 5, map[0][r][c]);
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[1][r][c] += map[2][r][c]; // 지난 상태 반영
			}
		}
	}
	
	public static void doHeat(int r, int c, int power, int part) {
		// 온풍기 작동 완료
		if (power == 0) {
			return;
		}
		switch (part) {
		case 1: // 오른쪽 방향 작동
			if (wallCheck(r, c, 1, part) && c + 1 < C && !visited[r][c+1]) { // 오른
				visited[r][c+1] = true;
				map[1][r][c+1] += power;
				doHeat(r, c + 1, power - 1, part);
			}
			if (power != 5 && c + 1 < C) {
				if (wallCheck(r, c, 5, part) && r - 1 >= 0 && !visited[r-1][c+1]) { // 오른위
					visited[r-1][c+1] = true;
					map[1][r-1][c+1] += power;
					doHeat(r - 1, c + 1, power - 1, part);
				}
				if (wallCheck(r, c, 6, part) && r + 1 < R && !visited[r+1][c+1]) { // 오른아래
					visited[r+1][c+1] = true;
					map[1][r+1][c+1] += power;
					doHeat(r + 1, c + 1, power - 1, part);					
				}
			}
			break;
		case 2: // 왼쪽 방향 작동
			if (wallCheck(r, c, 2, part) && c - 1 >= 0 && !visited[r][c-1]) { // 왼쪽
				visited[r][c-1] = true;
				map[1][r][c-1] += power;
				doHeat(r, c - 1, power - 1, part);
			}
			if (power != 5 && c - 1 >= 0) {
				if (wallCheck(r, c, 7, part) && r - 1 >= 0 && !visited[r-1][c-1]) { // 왼쪽위
					visited[r-1][c-1] = true;
					map[1][r-1][c-1] += power;
					doHeat(r - 1, c - 1, power - 1, part);
				}
				if (wallCheck(r, c, 8, part) && r + 1 < R && !visited[r+1][c-1]) { // 왼쪽아래
					visited[r+1][c-1] = true;
					map[1][r+1][c-1] += power;
					doHeat(r + 1, c - 1, power - 1, part);					
				}
			}
			break;
		case 3: // 위쪽 방향 작동
			if (wallCheck(r, c, 3, part) && r - 1 >= 0 && !visited[r-1][c]) { // 위쪽
				visited[r-1][c] = true;
				map[1][r-1][c] += power;
				doHeat(r - 1, c, power - 1, part);
			}
			if (power != 5 && r - 1 >= 0) {
				if (wallCheck(r, c, 5, part) && c + 1 < C && !visited[r-1][c+1]) { // 오른위
					visited[r-1][c+1] = true;
					map[1][r-1][c+1] += power;
					doHeat(r - 1, c + 1, power - 1, part);					
				}
				if (wallCheck(r, c, 7, part) && c - 1 >= 0 && !visited[r-1][c-1]) { // 왼쪽위
					visited[r-1][c-1] = true;
					map[1][r-1][c-1] += power;
					doHeat(r - 1, c - 1, power - 1, part);
				}
			}
			break;
		case 4: // 아래쪽 방향 작동
			if (wallCheck(r, c, 4, part) && r + 1 < R && !visited[r+1][c]) { // 아래
				visited[r+1][c] = true;
				map[1][r+1][c] += power;
				doHeat(r + 1, c, power - 1, part);
			}
			if (power != 5 && r + 1 < R) {
				if (wallCheck(r, c, 6, part) && c + 1 < C && !visited[r+1][c+1]) { // 오른아래
					visited[r+1][c+1] = true;
					map[1][r+1][c+1] += power;
					doHeat(r + 1, c + 1, power - 1, part);					
				}
				if (wallCheck(r, c, 8, part) && c - 1 >= 0 && !visited[r+1][c-1]) { // 왼쪽아래
					visited[r+1][c-1] = true;
					map[1][r+1][c-1] += power;
					doHeat(r + 1, c - 1, power - 1, part);
				}
			}
			break;

		default:
			break;
		}
	}
	
	// 진행 방향에 벽 있는지 확인하는 메서드 -> 벽 있으면 false / 없으면 true
	public static boolean wallCheck(int r, int c, int dir, int part) {
		switch (dir) {
		case 1: // 오른
			for (int i = 0; i < walls.length; i++) {
				if (walls[i][0] == r && walls[i][1] == c && walls[i][2] == 1) {
					return false;
				}
			}
			break;
		case 2: // 왼쪽
			for (int i = 0; i < walls.length; i++) {
				if (walls[i][0] == r && walls[i][1] == c - 1 && walls[i][2] == 1) {
					return false;
				}
			}
			break;
		case 3: // 위쪽
			for (int i = 0; i < walls.length; i++) {
				if (walls[i][0] == r && walls[i][1] == c && walls[i][2] == 0) {
					return false;
				}
			}
			break;
		case 4: // 아래
			for (int i = 0; i < walls.length; i++) {
				if (walls[i][0] == r + 1 && walls[i][1] == c && walls[i][2] == 0) {
					return false;
				}
			}
			break;
		case 5: // 오른위
			for (int i = 0; i < walls.length; i++) {
				if (part == 1 && ((walls[i][0] == r && walls[i][1] == c && walls[i][2] == 0) || (walls[i][0] == r - 1 && walls[i][1] == c && walls[i][2] == 1))) {
					return false;
				}
				if (part == 3 && ((walls[i][0] == r && walls[i][1] == c + 1 && walls[i][2] == 0) || (walls[i][0] == r && walls[i][1] == c && walls[i][2] == 1))) {
					return false;
				}
			}
			break;
		case 6: // 오른아래
			for (int i = 0; i < walls.length; i++) {
				if (part == 1 && ((walls[i][0] == r + 1 && walls[i][1] == c && walls[i][2] == 0) || (walls[i][0] == r + 1 && walls[i][1] == c && walls[i][2] == 1))) {
					return false;
				}
				if (part == 4 && ((walls[i][0] == r + 1 && walls[i][1] == c + 1 && walls[i][2] == 0) || (walls[i][0] == r && walls[i][1] == c && walls[i][2] == 1))) {
					return false;
				}
			}
			break;
		case 7: // 왼쪽위
			for (int i = 0; i < walls.length; i++) {
				if (part == 2 && ((walls[i][0] == r && walls[i][1] == c && walls[i][2] == 0) || (walls[i][0] == r - 1 && walls[i][1] == c - 1 && walls[i][2] == 1))) {
					return false;
				}
				if (part == 3 && ((walls[i][0] == r && walls[i][1] == c - 1 && walls[i][2] == 0) || (walls[i][0] == r && walls[i][1] == c - 1 && walls[i][2] == 1))) {
					return false;
				}
			}
			break;
		case 8: // 왼쪽아래
			for (int i = 0; i < walls.length; i++) {
				if (part == 2 && ((walls[i][0] == r + 1 && walls[i][1] == c && walls[i][2] == 0) || (walls[i][0] == r + 1 && walls[i][1] == c - 1 && walls[i][2] == 1))) {
					return false;
				}
				if (part == 4 && ((walls[i][0] == r + 1 && walls[i][1] == c - 1 && walls[i][2] == 0) || (walls[i][0] == r && walls[i][1] == c - 1 && walls[i][2] == 1))) {
					return false;
				}
			}
			break;

		default:
			break;
		}
		return true;
	}
}
