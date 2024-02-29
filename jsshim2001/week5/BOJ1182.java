import java.util.Arrays;
import java.util.Scanner;

public class BOJ1182 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}

		int cnt =0;
		
		for (int i = 0; i < (1 << n); i++) { // 비트마스킹으로 부분수열 구하기
			int sum=0;
			for (int j = 0; j < n; j++) { 
				if ((i & (1 << j)) > 0) {
					sum+=arr[j];
				}
			}
			if(sum==s) {
				cnt++;
			}
		}
		if(s==0) { // 시작 값이 0이라 처리 한 번 해줌
			System.out.println(cnt-1);
		} else {
			System.out.println(cnt);
		}

	}
}
