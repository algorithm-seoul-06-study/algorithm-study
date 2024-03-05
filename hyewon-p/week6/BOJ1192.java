import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ1992 {
	static String[] nums;
	static Deque<char[]> zipped;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		zipped = new ArrayDeque<>();
		// 입력받은 숫자들 저장
		nums = new String[N];
		for (int n = 0; n < N; n++) {
			nums[n] = br.readLine();
		}

		zip(0, 0, N);
		StringBuilder sb = new StringBuilder();
		while (!zipped.isEmpty()) {
			sb.append(zipped.poll());
		}

		System.out.println(sb);
	}

	// 재귀를 통해 네 구역을 검사하고, 압축이 가능하다면 압축해서 zipped에 저장
	// 검사는 zipped에서 4개의 배열을 빼서 -> 배열의 크기가 1인가? -> 내용물이 다 같은가? 검사
	// 압축 안되면 괄호를 추가해서 저장
	static void zip(int x, int y, int size) {
		// 크기 1이면 그냥 추가 후 리턴
		if (size == 1) {
			zipped.add(new char[] { nums[y].charAt(x) });
			return;
		}

		// 네 구역 검사
		zip(x, y, size / 2);
		zip(x + size / 2, y, size / 2);
		zip(x, y + size / 2, size / 2);
		zip(x + size / 2, y + size / 2, size / 2);

		// zipped의 뒤 4개의 배열은 위 검사의 결과물이므로...
		Deque<char[]> tmpLi = new ArrayDeque<>();
		int[] count = new int[2];
		// 하나씩 빼서 검사
		for (int i = 0; i < 4; i++) {
			char[] tmp = zipped.pollLast();
			// 압축이 다 돼서 길이가 1이라면
			if (tmp.length == 1) {
				// 0/1 카운트
				count[tmp[0] - '0']++;
			}
			tmpLi.addLast(tmp);
		}

		// 만약 1이 4개거나 0이 4개면 괄호 없이 하나만 zipped에 추가
		if (count[0] == 4 || count[1] == 4) {
			zipped.addLast(tmpLi.pollLast());
		} else {
			// 아니라면 괄호 추가하고 1차원으로 펴서 zipped에 추가
			StringBuilder tt = new StringBuilder();
			tt.append('(');
			while (!tmpLi.isEmpty()) {
				for (char tm : tmpLi.pollLast()) {
					tt.append(tm);
				}
			}
			tt.append(')');
			char[] a = new char[tt.length()];
			tt.getChars(0, tt.length(), a, 0);
			zipped.add(a);
		}

	}

}
