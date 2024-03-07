package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1790 {
	public static void main(String[] args) {
		long[] prefixSum = new long[11];
		long[] sum = new long[11];
		sum[10] = 1;

		for (int i = 1; i < 10; i++) {
			sum[i] = (long) ((long) 9 * Math.pow(10, i - 1));
			prefixSum[i] = sum[i] * i + prefixSum[i - 1];
		}
		prefixSum[10] = prefixSum[9] + 10;
//		System.out.println(Arrays.toString(sum));
//		System.out.println(Arrays.toString(prefixSum));

		Scanner sc = new Scanner(System.in);

		long n = sc.nextLong();
		long k = sc.nextLong();

		// n이 1500이면 1-9 / 10-99 / 100-999 / 1000-1500
		// n이 총 몇자리인지sum[1]*1 sum[2]*2 ,,, 몫이 0일 때는 수-(Math.pow(10,i-1)+1)*i
		// 몫이 0이 될 때 갯수는 그 수 - 해당 인덱스 10 pow한 거

		long nlen = 0;

		long n2 = n;
		for (int i = 1; i < 11; i++) {
			if (n2 / 10 != 0) {
				n2 /= 10;
				nlen += sum[i] * i;
			} else {
				nlen += ((n - Math.pow(10, i - 1)) + 1) * i;
				break;
			}
		}



		int idx = 0;

		if (k > nlen) {
			System.out.println("-1");
		} else {
			for (int i = 1; i < 11; i++) {
				if (k <= prefixSum[i] && k > prefixSum[i - 1]) {
					idx = i;
					break;
				}
			}


			// 몇자리 수에 범위에 걸쳐있는지가 idx-1
			
			// 몇자리 수에 걸쳐있는지 확인 후 그 prefixSum[idx] 부터 수를 세기 위해서 
			// idx부터 몇번째 수인지 센다
			//1.해당 idx에서 몇번째인지 kk에 저장
			long kk = k - prefixSum[idx - 1];
			//2.idx-1이 몇자리 수인지 나타내기 때문에 kk를 idx-1로 나누면 해당 해당 자리수의 몇번째 숫자인지 알 수 있다. 즉 그 숫자 마지막자리로 부터 나머지 만큼 이동 하면됨
			long kres = (long) ((kk / idx) + Math.pow(10, idx - 1));
			//3.나머지 저장
			long kmod = kk % idx;
			//4.나머지가 0이면 그냥 바로 제일 끝 수 출력
			int ans = 0;
//			System.out.println(kk);
//			System.out.println(idx);
//			System.out.println(kres);
//			System.out.println(kmod);
			if (kmod == 0) {
				
				ans = (int) (kres-1) % 10;
			} else {
				//5. 나머지가 0이 아니면 다음 숫자로 넘어가서 이동후  출력
				ans = (int) ((kres) / (Math.pow(10, idx- kmod))) % 10;

			}

			System.out.println(ans);

		}
	}
}
