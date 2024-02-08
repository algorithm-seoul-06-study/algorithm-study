import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2156 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
		int[] exc = new int[n];
		if (n == 1) {
			System.out.println(arr[0]);
		} else if (n == 2) {
			System.out.println(arr[0] + arr[1]);
		} else if (n == 3) {
			System.out.println(arr[0] + arr[1] + arr[2] - Math.min(Math.min(arr[0], arr[1]), arr[2]));
		} else if (n >= 4) {
			exc[0] = 0;
			exc[1] = arr[0];
			exc[2] = arr[0] + arr[1];			
			for (int i = 3; i < exc.length; i++) {
				exc[i] = Math.max(Math.max(exc[i-1], arr[i-1] + exc[i-2]), arr[i-1] + arr[i-2] + exc[i-3]);
			}			
			System.out.println(Math.max(Math.max(exc[n-1], arr[n-1] + exc[n-2]), arr[n-1] + arr[n-2] + exc[n-3]));
		}
		
	}
}