package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {
	static int N;
	static int[] arr;
	static int[] results;
	static int[] counts;
	static int findCount;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[M*2];
		
		// 친구 관계를 1차원 배열 arr에 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i*2+j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 결과 저장 배열
		results = new int[N+1];
		for (int i = 1; i <= N; i++) {
			// count 배열
			counts = new int[N+1];
			// counts[i] 값 -1로 초기화 (0 안겹치게)
			counts[i] = -1; 
			// findCount 0으로 초기화
			findCount = 0;
			// queue 선언 후 i 넣기
			queue = new LinkedList<>();
			queue.offer(i);
			// 함수 실행
			findFriend(1);
			// results[i] 값 1로 초기화 (counts[i] == -1 이라서)
			results[i] = 1; 
			// results[i] 에 counts 값 다 더하기
			for (int j = 1; j < counts.length; j++) {
				results[i] += counts[j]; 
			}
		}
		
		// 결과의 최소값
		int resultMin = Integer.MAX_VALUE;
		// 결과가 최소값인 숫자
		int result = 0;
		for (int i = 1; i < results.length; i++) {
			if (resultMin > results[i]) {
				resultMin = results[i];
				result = i;
			}
		}
		System.out.println(result);
	}
	
	static void findFriend(int k) {
		// 큐 사이즈만큼 반복
		for (int size = queue.size(); size > 0; size--) {
			// 큐 빼기
			int n = queue.poll();
			for (int i = 0; i < arr.length; i++) {
				// arr[i]가 n일 때
				if (arr[i] == n) {
					// n이 짝수고 counts[arr[i+1]] == 0 (처음 만나는 숫자) 이면
					if (i % 2 == 0 && counts[arr[i+1]] == 0) {
						// 짝을 큐에 넣고
						queue.offer(arr[i+1]);
						// 카운트 배열에 k값 넣고
						counts[arr[i+1]] = k;
						// 찾은 인원 +1
						findCount++;
						// n이 홀수고 counts[arr[i-1]] == 0 (처음 만나는 숫자) 이면
					} else if (i % 2 == 1 && counts[arr[i-1]] == 0) {
						// 짝을 큐에 넣고
						queue.offer(arr[i-1]);
						// 카운트 배열에 k값 넣고
						counts[arr[i-1]] = k;
						// 찾은 인원 +1
						findCount++;
					}
				}
				// 찾은 인원이 자기 자신 제외한 N-1 일 경우 함수 종료
				if (findCount == N - 1) {
					return;
				}
			}
		}
		// k를 하나 늘리고 함수 반복
		findFriend(k+1);
	}
}
