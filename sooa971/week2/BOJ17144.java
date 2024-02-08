package aStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17144 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int r = Integer.parseInt(str[0]);
		int c = Integer.parseInt(str[1]);
		int t = Integer.parseInt(str[2]);
		int[][] map = new int[r][c];
		int[][] map2 = new int[r][c];
		int[] air_r= new int[2];
		int[] air_c= new int[2];
		
		
		int[] dr= {0,-1,0,1};
		int[] dc= {1,0,-1,0};


		int idx=0;
		for(int i =0; i<r; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<c; j++) {
				map[i][j]=Integer.parseInt(line[j]);
				if(map[i][j]==-1) {
					air_r[idx]=i;
					air_c[idx]=j;
					idx++;
				}
			}
		}
		
	
		
		for(int i=0; i<t; i++) {
			//인접확산
			for(int j=0; j<r; j++) { //r
				for(int q=0; q<c; q++) { //c
					
					if((j==air_r[0]&&q==air_c[0])||(j==air_r[1]&&q==air_c[1])) {
						continue;
					}
					int cnt=0;
					//방향별
					for(int d =0; d<4; d++) {
						if(j+dr[d]<r && j+dr[d]>=0 && q+dc[d]>=0 && q+dc[d]<c) {
							if(map[j+dr[d]][q+dc[d]]==-1) {
								continue;
							}else {
							map2[j+dr[d]][q+dc[d]]+=map[j][q]/5;
							cnt++;
							}
						}
					}
					map2[j][q]-=(map[j][q]/5)*cnt;
				}
			}
			
			for(int j=0; j<r; j++) { //r
				for(int q=0; q<c; q++) { //c
					map[j][q]+=map2[j][q];
					
				}
			}
			
			//바람따라 돌기
			//윗칸
			for(int j=air_r[0]-1; j>=1;j--) {
				map[j][0]=map[j-1][0];
			}
			for (int j=0; j<r-1; j++)
			{
				map[0][j]=map[0][j+1];
			}
			for(int j=0; j<air_r[0]; j++) {
				map[j][c-1]=map[j+1][c-1];
			}
			for(int j=c-1; j>=2; j--) {
				map[air_r[0]][j]=map[air_r[0]][j-1];
			}
			
			//아랫칸
			for(int j=air_r[1]+1; j<r-1;j++) {
				map[j][0]=map[j+1][0];
			}
			for (int j=0; j<c-1; j++)
			{
				map[r-1][j]=map[r-1][j+1];
			}
			for(int j=c-1; j>=air_r[1]+1; j--) {
				map[r-1][j]=map[r-1][j-1];
			}
			for(int j=c-1; j>=2; j--) {
				map[air_r[1]][j]=map[air_r[1]][j-1];
			}
			
		}
		
		
		
		
		
		for(int i =0; i<r; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	
}
