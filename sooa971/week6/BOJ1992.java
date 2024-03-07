package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
	static int[][] video;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		video = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				video[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		quad(0, n, 0, n);

	}
	
	static boolean check(int rStart, int rEnd, int cStart, int cEnd) {
		
		
		int n = video[rStart][cStart];
		
		for (int i = rStart; i < rEnd; i++) {
			for (int j = cStart; j < cEnd; j++) {
				
				int k = video[i][j];
				if (n != k) {
					
					return false;
						
					}

				}
			}
		
		return true;
		}
		
	
	
	static void quad(int rStart, int rEnd, int cStart, int cEnd) {
		
		int rMid = (rStart + rEnd) / 2;
		int cMid = (cStart + cEnd) / 2;
		
		if(check(rStart, rEnd, cStart, cEnd)) {
			System.out.print(video[rStart][cStart]);
			
		}
		
		else {
			System.out.print("(");
			quad(rStart, rMid, cStart, cMid);
			quad(rStart, rMid, cMid, cEnd);
			quad(rMid, rEnd, cStart, cMid);
			quad(rMid, rEnd, cMid, cEnd);
			System.out.print(")");
		}
		

	}
}
