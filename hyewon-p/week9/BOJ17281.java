import java.io.IOException;

public class BOJ17281 {
	static int[] lineup = new int[9];;
	static int maxScore = 0;
	static boolean[] fixed = new boolean[9];
	static int[][] hits;

	public static void main(String[] args) throws IOException {
		int N = readInt();
		hits = new int[N][9];
		// 선수1 4번에 고정
		lineup[3] = 0;
		fixed[0] = true;
		// 입력 받기
		for (int n = 0; n < N; n++) {
			for (int i = 0; i < 9; i++) {
				hits[n][i] = readInt();
			}
		}
		getComb(0);
		System.out.println(maxScore);
	}

	static void getComb(int num) {
		// 9번까지 라인업 완성했으면 스코어 구해보기
		if (num == 9) {
			int score = play();
			maxScore = Math.max(score, maxScore);
			return;
		}
		// 4번은 고정이니까 넘어가셈
		if (num == 3) {
			getComb(num + 1);
			return;
		}
		// 선수 2부터 9까지 모든 타순에 넣어보면서 조합 찾기
		for (int i = 1; i < 9; i++) {
			if (!fixed[i]) {
				lineup[num] = i;
				fixed[i] = true;
				getComb(num + 1);
				lineup[num] = 0;
				fixed[i] = false;
			}

		}

	}

	static int play() {
		int score = 0;
		int currHitter = 0;
		boolean[] bases;

		for (int inning = 0; inning < hits.length; inning++) {
			bases = new boolean[3];
			int outCount = 0;
			while (outCount < 3) {
				int currPlayer = lineup[currHitter];
				currHitter = (currHitter + 1) % 9;
				int hit = hits[inning][currPlayer];
				switch (hit) {
				case 0:
					outCount++;
					break;
				case 4:
					score+=homerun(bases);
					break;
				default:
					// 안타인 경우 주자 옮기기
					for (int i = 2; i >= 0; i--) {
						if (bases[i]) {
							// 홈에 들어오면 점수+
							if (i + hit >= 3) {
								score++;
							// 아니면 베이스 이동
							} else
								bases[i + hit] = true;
							// 원래 있던 베이스 지우기
							bases[i] = false;
						}
					}
					// 타자 주자 기록
					bases[hit - 1] = true;
				}
				
			}
		}
		return score;
	}

	static int homerun(boolean[] bases) {
		int score = 0;
		// 베이스 검사 -> 주자 불러들임
		for (int i = 0; i < 3; i++) {
			if (bases[i]) {
				score++;
				bases[i] = false;
			}
		}
		// 본인까지 홈
		return ++score;
	}

	public static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}
}
