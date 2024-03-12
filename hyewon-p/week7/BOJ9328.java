import java.util.*;
import java.io.*;

public class BOJ9328 {
	static char[][] maze;
	static int h, w;
	static HashMap<Character, Boolean> keys;
	static Queue<Coor> queue;
	static int docCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 변수 초기화
			queue = new LinkedList<>();
			docCount = 0;
			keys = new HashMap<>();

			// 인풋 받는 내용이 너무 길어서 분리
			getInput(br);

			// 방문했었는지 기록할 배열
			boolean[][] visited = new boolean[h][w];
			
			int answer = 0;
			// 움직이지 못하고 턴 넘기는 경우 카운트
			// 만약 큐를 다 돌았는데 모든 경우에 움직이지 못한다면, break
			int limit = 0;
			while (!queue.isEmpty()) {
				// 문서 다 찾았으면 탈출
				if (docCount == answer) break;
				// 다 돌았는데 한번도 움직이지 못함
				if (limit == queue.size() + 1) break;

				Coor curr = queue.poll();

				// 만약 현재 위치가 문이 아니고 방문했었다면 패스
				if (!checkIfDoor(curr.x, curr.y) && visited[curr.y][curr.x]) continue;

				// 방문 표시
				visited[curr.y][curr.x] = true;

				// 만약 현재 위치가 열쇠라면 hashmap에 기록
				if (checkIfKey(curr.x, curr.y)) {
					keys.put(maze[curr.y][curr.x], true);
				// 만약 현재 위치가 문이라면
				} else if (checkIfDoor(curr.x, curr.y)) {
					// 열쇠가 존재하는 문인가?
					if (checkIfValidDoor(curr.x, curr.y)) {
						// 열쇠가 존재는 하나 아직 얻지 못함
						if (!keys.get(Character.toLowerCase(maze[curr.y][curr.x]))) {
							// 미루자
							limit++;
							queue.add(curr);
							continue;
						}
					// 열쇠가 애초에 없으면 버림
					} else {
						continue;
					}
				// 현재 자리가 문서인 경우
				} else if (maze[curr.y][curr.x] == '$') {
					answer++;
				}

				// 움직였으면 초기화
				limit = 0;

				// 사방을 탐색
				for (int[] dir : new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }) {
					// 이동할 수 없거나, 방문한 적 있는 곳이라면 패스
					if (!checkIfMoveable(curr.x+dir[0], curr.y+dir[1]) || visited[curr.y + dir[1]][curr.x + dir[0]])
						continue;
					// 만약 문이고 열쇠가 없다면 방문 표시하고 패스
					if (checkIfDoor(curr.x + dir[0], curr.y + dir[1])
							&& !checkIfValidDoor(curr.x + dir[0], curr.y + dir[1])) {
						visited[curr.y + dir[1]][curr.x + dir[0]] = true;
						continue;
					}
					// 모든 조건을 통과했다면 다음 목적지 후보로 큐에 추가
					queue.add(new Coor(curr.x + dir[0], curr.y + dir[1]));
				}

			}
			System.out.println(answer);
		}

	}

	static boolean checkIfMoveable(int x, int y) {
		if (!checkIfInMaze(x, y)) return false;
		if (maze[y][x] == '*') return false;
		return true;
	}

	static boolean checkIfInMaze(int x, int y) {
		if (y < 0 || y >= h)
			return false;
		if (x < 0 || x >= w)
			return false;
		return true;
	}

	static boolean checkIfKey(int x, int y) {
		if (maze[y][x] >= 'a' && maze[y][x] <= 'z') {
			return true;
		}
		return false;
	}

	static boolean checkIfDoor(int x, int y) {
		if (maze[y][x] >= 'A' && maze[y][x] <= 'Z') {
			return true;
		}
		return false;

	}

	// 문의 소문자가 hashmap에 존재하는지 검사
	static boolean checkIfValidDoor(int x, int y) {
		if (keys.containsKey(Character.toLowerCase(maze[y][x]))) {
			return true;
		}
		return false;
	}

	static void getInput(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		maze = new char[h][w];

		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				if (line.charAt(x) == '$') {
					docCount++;
				}
				// hashmap에 맵에 존재하는 열쇠들을 모두 등록
				if (line.charAt(x) >= 'a' && line.charAt(x) <= 'z') {
					keys.put(line.charAt(x), false);
				}

				maze[y][x] = line.charAt(x);

				// 출발지 찾아서 큐에 등록
				if ((y == 0 || x == 0 || y == h - 1 || x == w - 1) && (line.charAt(x) != '*')) {
					queue.add(new Coor(x, y));
				}
			}
		}

		// 가지고 있는 열쇠라면 true로 기록
		for (char a : br.readLine().toCharArray()) {
			keys.put(a, true);
		}
	}
}

class Coor {
	int x;
	int y;

	Coor(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
