package aStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		
		List<Integer> list = new LinkedList<>();
		
		for(int i=1; i<=n; i++) {
			list.add(i);
		}
		int j =-1;
		System.out.print("<");
		
		for(int i=0; i<n; i++) {
			j+=k;
			if(j>=list.size()) {
				j%=list.size();
			} 
			System.out.print(list.remove(j));
			j-=1;
			if (i!=n-1) {
				System.out.print(", ");
			}
		}
		System.out.print(">");
		
	}
}
