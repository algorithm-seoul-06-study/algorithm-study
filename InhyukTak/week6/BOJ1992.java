package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992_쿼드트리_2 {
	static String[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new String[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().split("");
		}
		
		quadTree(N);
		
		// 최종 결과는 arr[0][0]에 저장됨
		System.out.println(arr[0][0]);
	}
	
	static void quadTree(int N) {
		// 한 변의 길이가 1이면 종료
		if (N == 1)
			return;
		
		// (N/2) * (N/2) 부분에 다시 채우기
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				// 좌상, 우상, 좌하, 우하 다 같으면 하나로 압축
				if ((arr[2*i][2*j].equals("0") || arr[2*i][2*j].equals("1")) && arr[2*i][2*j].equals(arr[2*i][2*j+1]) && arr[2*i][2*j].equals(arr[2*i+1][2*j]) && arr[2*i][2*j].equals(arr[2*i+1][2*j+1]))
					arr[i][j] = arr[2*i][2*j];
				// 아니면 괄호 붙여서 문자열로 표현
				else
					arr[i][j] = "(" + arr[2*i][2*j] + arr[2*i][2*j+1] + arr[2*i+1][2*j] + arr[2*i+1][2*j+1] + ")"; 
				
			}
		}
		// N -> N/2 로 재귀 구현
		quadTree(N/2);
	}
}
