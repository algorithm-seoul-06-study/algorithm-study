import java.util.Arrays;
import java.util.Scanner;

public class BOJ1790 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] arr = new int[9];
		
		// 배열 내에 개수 미리 정하기, 한 자릿수: 1*9. 두 자릿수: 2*90, 세 자릿수:3*900 ...
		for (int i = 1; i < 9; i++) {
			arr[i] = (int) (i * 9 * Math.pow(10, i - 1));
		}
		
		int cnt = 0;
		// k가 클 때까지 계속 빼기: k가 몇 자릿수인지 구하기
		for (int i = 0; i < 9; i++) {
			if(k>arr[i]) {
				k-=arr[i];
				cnt=i+1;
			}
		}
		
		// ex) 23이면 cnt=2 (두 자릿수), num = 그때 해당하는 수 (16)
		int num = (int) (Math.pow(10, cnt-1)+(k-1)/cnt);
		
		
		if(n<num) { // 그 수가 n보다 작으면 -1출력
			System.out.println(-1);
		} else { // 크면 원하는 자릿수 출력
			String str = Integer.toString(num);
			System.out.println(str.charAt((k-1)%cnt));
		}
	}
}