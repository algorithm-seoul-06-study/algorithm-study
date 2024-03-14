import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ1389 {
	
	static int n,m;
	static int[][] kb;
	static boolean[][] know;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		
		kb = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				kb[i][j]=100;
				if(i==j) {
					kb[i][j]=0;
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			String[] line2 = br.readLine().split(" ");
			kb[Integer.parseInt(line2[0])][Integer.parseInt(line2[1])]=1;
			kb[Integer.parseInt(line2[1])][Integer.parseInt(line2[0])]=1;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
			}
		}	
	}
	
	public static void bfs(int r, int c) {
		
	}
}
