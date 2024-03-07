package week6;

import java.util.Scanner;

public class BOJ1790 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long K = sc.nextInt();

		long target = 0;
		long length = 1;
		long count = 9;

		while (K > count * length) {
			
			K -= (length * count);
			target += count;

			length++;
			count *= 10;
			
		}

		target = (target + 1) + (K - 1) / length;

		if (target > N) {
			System.out.println(-1);
		} else {
			int idx = (int) ((K - 1) % length);
			System.out.println(String.valueOf(target).charAt(idx));
		}
	}
}
