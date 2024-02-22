package week4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ11866 {

	static Queue<Integer> 사람;
	static ArrayList<Integer> result;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		Queue<Integer> 사람 = new LinkedList<>();
		List<Integer> result = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			사람.add(i);
		}

		while (!사람.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				사람.add(사람.poll());
			}

			result.add(사람.poll());
		}

		System.out.print("<");
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
			if (i < result.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print(">");

		sc.close();
	}
}
