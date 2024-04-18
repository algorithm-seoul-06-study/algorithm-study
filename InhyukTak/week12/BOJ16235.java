import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16235 {
	static int N, M, K; // N : 땅 크기, M : 처음 나무 개수, K : 몇 년 후
	static int[][] foods; // S2D2가 뿌리는 양분의 양
	static int[][] land; // 땅에 있는 양분의 양
	static List<Integer>[][] trees; // 각 지역에 있는 나무들의 나이
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}; // 8방탐색
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1}; // 8방탐색
	static int treeCnt; // 현재 나무 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		foods = new int[N+1][N+1];
		land = new int[N+1][N+1];
		
		for (int r = 1; r < N + 1; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < N + 1; c++) {
				foods[r][c] = Integer.parseInt(st.nextToken());
				land[r][c] = 5;
			}
		}
		
		trees = new ArrayList[N+1][N+1];
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				trees[r][c] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			trees[x][y].add(z); // 해당 땅에 나무 나이 저장
		}
		treeCnt = M; // 현재 나무 개수 M으로 설정
		
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if (trees[r][c].size() > 1) {
					Collections.sort(trees[r][c]); // 나이 오름차순으로 정렬
				}
			}
		}
		
		// K년만큼 봄여름가을겨울 반복
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(treeCnt);
	}

	private static void spring() {
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if (trees[r][c].size() > 0) {
					for (int i = 0; i < trees[r][c].size(); i++) {
						int tree = trees[r][c].get(i);
						if (land[r][c] >= tree) { // 나이만큼 양분 먹고 나이 +1
							land[r][c] -= tree; // 땅의 양분 업데이트
							trees[r][c].set(i, tree + 1); // 나무 나이 +1
						} else {
							trees[r][c].set(i, -tree); // 양분 못먹고 죽은 나무의 나이를 음수로 변경
						}
				}
				}
			}
		}
	}

	private static void summer() {
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if (trees[r][c].size() > 0) {
					for (int i = 0; i < trees[r][c].size(); i++) {
						int tree = trees[r][c].get(i);
						if (tree < 0) { // 나이가 음수인 나무는 죽을 나무
							land[r][c] -= tree / 2; // 양분이 돼라
							trees[r][c].remove(i); // 리스트에서 제거
							treeCnt--; // 나무 개수 -1
							i--; // trees[r][c].size()가 1만큼 줄었으므로 i-- 적용
						}
					}
				}
			}
		}
	}

	private static void fall() {
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if (trees[r][c].size() > 0) {
					for (int i = 0; i < trees[r][c].size(); i++) {
						int tree = trees[r][c].get(i);
						if (tree % 5 == 0) { // 나무의 나이가 5의 배수이면
							for (int di = 0; di < dc.length; di++) { // 8방탐색
								int nr = r + dr[di];
								int nc = c + dc[di];
								if (nr >= 1 && nr < N + 1 && nc >= 1 && nc < N + 1) {
									trees[nr][nc].add(0, 1); // 맨 앞에 나이 1짜리 나무 추가
									treeCnt++; // 나무 개수 +1
								}
							}
						}
					}
				}
			}
		}
	}

	private static void winter() {
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				land[r][c] += foods[r][c]; // S2D2로 땅에 양분 뿌리기
			}
		}
	}
}
