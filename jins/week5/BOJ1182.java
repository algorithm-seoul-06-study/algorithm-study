package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
	
	static int N,S;
	static int[] arr;
	static int count = 0; 

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		countArr(0, 0);
		if(S == 0) {
			count -= 1;
		}
		System.out.println(count);

	}

	public static void countArr(int index, int sum) {

		if(index == N) {
			if(sum ==  S) {
				count++;
			}
			return;
		}
		
		
		countArr(index + 1, sum + arr[index]);
		countArr(index + 1, sum);
			
	}

}
