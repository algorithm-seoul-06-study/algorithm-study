package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split("");
		int len = line.length;

		int[] cho1 = new int[len];
		int[] cho2 = new int[len];

		int[] arr = new int[len];

		boolean flag = true;
		//암호가 없을 때 에러
		if (len==0) {
			flag=false;
		}
		for (int i = 0; i < len; i++) {
			arr[i] = Integer.parseInt(line[i]);
			//첫 단어부터 0이면 에러
			if(i==0&&arr[i]==0) {
				flag = false;
			}
			//그 이후 앞 단어가 1또는2가 아닌데 0일 경우 에러 
			else if (i>0&&!(arr[i-1]==1 || arr[i-1]==2)&&arr[i] == 0) {
				flag = false;
			}

		}

		// 1개 선택 : 앞 인덱스 두개 더하기
		// 2개 선택 : 앞 앞 인덱스 두개 더하기
		if (flag) {
			if (len == 1) {
				System.out.println(1);
			} else if (len == 2) {
				System.out.println(possible1(arr[1]) + possible2(arr[0], arr[1]));
			} else {
				cho1[0] = 1;
				cho1[1] = possible1(arr[1]);
				cho2[0] = 0;
				cho2[1] = possible2(arr[0], arr[1]);

				for (int i = 2; i < len; i++) {
					cho1[i] = ((cho1[i - 1] + cho2[i - 1]))*possible1(arr[i])% 1000000;
					cho2[i] = ((cho1[i - 2] + cho2[i - 2])*possible2(arr[i - 1], arr[i]) )% 1000000;
				}
				
				System.out.println((cho1[len - 1] + cho2[len - 1])% 1000000);
			}

			

		} else {
			System.out.println(0);
		}
	}

	
	// 1개 선택 가능한지 확인하는 메서드
	static int possible1(int n) {
		if (n!=0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// 2개 선택 가능한지 확인하는 메서드
	static int possible2(int l, int r) {
		
		if (l!=0 && l * 10 + r <= 26) {
			return 1;
		} else {
			return 0;
		}
	}
}
