package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {
	static char[][] arr;
	static int cntMax;
	static char[] target;
	static int targetSize;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		// 이미 밟아서 더이상 갈 수 없는 알파벳 저장하는 배열 target
		target = new char[26];
		// target 배열의 크기와 index를 조절하기 위한 변수 targetSize
		targetSize = 0;
		for (int r = 0; r < arr.length; r++) {
			String str = br.readLine();
			for (int c = 0; c < arr[0].length; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		
		check(0, 0, 1);
		System.out.println(cntMax);
		
	}
	
	static void move(int r, int c, int cnt) {
		// 4방탐색
		for (int i = 0; i < dc.length; i++) {
			// 배열의 경계 조건
			if (r + dr[i] >= 0 && r + dr[i] < arr.length && c + dc[i] >= 0 && c + dc[i] < arr[0].length) { 
				// 갈 수 있는 곳인지 확인하는 boolean 변수
				flag = true;
				// 이동하려는 곳이 target에 있는지 확인
				for (int j = 0; j < targetSize; j++) {
					// 있으면 flag를 false로 바꿔서 이동하지 않음
					if (arr[r+dr[i]][c+dc[i]] == target[j]) {
						flag = false;
						break;
					}
				}
			// target에 하나도 걸리지 않았다면 그 곳으로 이동 후 check() 함수 실행
			if (flag) 		
				check(r + dr[i], c + dc[i], cnt + 1);
			
			}
		}
		// cntMax < cnt 이면 대입
		if (cntMax < cnt) 
			cntMax = cnt;
		
	}
	
	// 밟은 칸을 target에 저장하는 check() 함수
	// r, c : 현재 위치
	// cnt : 칸 수
	static void check(int r, int c, int cnt) {
		// 밟은 칸 target에 저장하고 targetSize++
		target[targetSize++] = arr[r][c];
		// 이동하는 함수
		move(r, c, cnt);
		// 되돌리기
		targetSize--;
	}
}
