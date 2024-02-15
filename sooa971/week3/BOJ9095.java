package aStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9095 {
	public static void main(String[] args) throws IOException {
		int[] arr = new int[31];
		for (int i =0; i<=11; i++) {
			for(int j =0; j<=5; j++) {
				for (int q=0; q<=3; q++) {
					if (i==0 && j==0 && q==0) {
						continue;
					}
					arr[i*1+j*2+q*3]+=multiple(i+j+q)/(multiple(i)*multiple(j)*multiple(q));
				}
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			System.out.println(arr[Integer.parseInt(br.readLine())]);
		}
		
	}
	
	static int multiple(int a) {
		if(a==0) {
			return 1;
		}
		int ans = 1;
		for (int i=1; i<=a; i++) {
			ans*=i;
		}
		return ans;
	}
}
