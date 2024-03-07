package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[][] input = new int[N][N];

        for (int i = 0; i < N; i++) {
        	
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j <= i; j++) {
            	
            	input[i][j] = Integer.parseInt(st.nextToken());
            	
            }
            
        }

        for (int i = N - 2; i >= 0; i--) {
        	
            for (int j = 0; j <= i; j++) {
            	
            	input[i][j] += Math.max(input[i + 1][j], input[i + 1][j + 1]);
            	
            }
            
        }

        System.out.println(input[0][0]);
        
    }
}
