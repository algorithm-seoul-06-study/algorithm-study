import java.util.Arrays;
import java.util.Scanner;

public class BOJ5904 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[29];
		arr[0] = 3;
		int[] p = new int[29]; // 이 뒤는 10^9이 넘어감
		for (int i = 0; i < 28; i++) { // 수열의 크기를 할당
			arr[i + 1] = 2 * arr[i] + (i + 4);
			p[i]=i+4; // p는 가운데 MOOOOO의 길이
		}
		
		out: while(true) {
			for (int i = 1; i < 28; i++) {
				if (n < arr[i]) { // 그 수열보다 큰 순간이 나온다면 확인
					if(n>arr[i-1]) { // 크면 빼줌
						n-=arr[i-1];
					}
					if(n<=p[i-1]) { // 그 값이 가운데 MOOO의 길이보다 작으면 out:break
						if(n==1) {
							System.out.println("m");
						} 
						else {
							System.out.println("o");
						}
						break out;
					} else { // 그게 아니라면 빼줌
						n-=p[i-1];
						
						break;
					}
				}
			}
		}
		

	}

}