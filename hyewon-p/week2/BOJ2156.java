import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine().trim());
		List<Integer> cups = new ArrayList<>();
		// dp 저장
		// 해당 index의 포도주를 마시지 않았을 때의 최대값
		List<Integer> maxCups = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			cups.add(Integer.parseInt(br.readLine().trim()));
		}
		if (n < 3) {
			int total = 0;
			for (int i = 0; i < n; i++) {
				total += cups.get(i);
			}
			System.out.println(total);
		} else {
			maxCups.add(0);
			maxCups.add(cups.get(0));
			maxCups.add(cups.get(0) + cups.get(1));
			// 포도주 개수 +1의 값을 출력(마지막 index의 포도주를 마실 수도 있으므로...)
			for (int i = 3; i < n+1; i++) {
				int max = maxCups.get(i - 1);
				for (int j = 1; j < 4; j++) {
					int num =maxCups.get(i-j);
					for (int k=1; k<j;k++) {
						num += cups.get(i-j+k);
					}
					max = Math.max(max, num);
					
				}
				maxCups.add(max);
			}
			System.out.println(maxCups.get(n));
		}
	}
}
