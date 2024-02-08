package aStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b포도주 {
	public static void main(String[] args) throws IOException {
		//마실 수 있는 경우의 수
		//2020/ 2010/1010,1020
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] vine = new int[n];
		for(int i =0; i<n; i++) {
			vine[i]=Integer.parseInt(br.readLine());
		}
		
		int[] vine0 =new int[n];
		vine0[0] = 0;
		int[] vine1 = new int[n];
		vine1[0] = vine[0];
		int[] vine2 = new int[n];
		vine2[0]=vine[0];
		
		for (int i=1; i<n; i++) {
			vine0[i]=Math.max(Math.max(vine1[i-1],vine2[i-1]),vine0[i-1]);
			vine1[i]=vine0[i-1]+vine[i];
			vine2[i]=vine1[i-1]+vine[i];
		}
		
		int max = Math.max(vine2[n-1], Math.max(vine0[n-1], vine1[n-1]));
		System.out.println(max);
	}
}
