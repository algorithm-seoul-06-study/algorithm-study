# Week 2
## BOJ1018 체스판 다시 칠하기
### 🎈 해결방법 :
1. 첫번째 칸을 기준으로 모든 칸에 대해 적절한 값이 할당되어 있는지 검사
2. 색을 바꿔야 하는 칸이 32칸(절반)보다 많다면 64칸에서 뺀 값으로 대체

### 💬 코멘트 :
접근법을 생각해내는 것이 까다로웠던 것 같습니다.

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int minChange = 64;
		char[][] board = new char[n][m];
		for (int i = 0; i < n; i++) {
			String nextLine = bf.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = nextLine.charAt(j);
			}
		}
		for (int y = 0; y <= n - 8; y++) {
			for (int x = 0; x <= m - 8; x++) {
				char color = board[y][x];
				int change = 0;
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						change += (color == board[y + i][x + j] ? 0 : 1);
						color = (color == 'B' ? 'W' : 'B');
					}
					color = (color == 'B' ? 'W' : 'B');
				}
				if (change > 32) {
					change = 64 - change;
				}
				minChange = Math.min(minChange, change);

			}
		}
		System.out.println(minChange);

	}
}
```

## BOJ1107 리모컨
### 🎈 해결방법 :
1. 숫자 중 고장난 숫자가 없다면 100에서 이동하는 횟수와 숫자의 길이를 비교하여 출력
2. 1 증가한 값을 chanNum1, 1 감소한 값을 chanNum2에 저장하고 둘 중 고장난 숫자가 포함되어 있지 않은 숫자가 있는지 검사
3. 조건을 만족하는 숫자를 찾으면 break
    - 만약 둘 다 조건을 만족한다면 더 짧은 값을 선택
    - 만약 증감량이 100에서 이동하는 횟수보다 커진다면 즉시 break
4. 숫자의 길이+증감량 vs 100에서 이동하는 횟수 중 작은 값 출력

### 💬 코멘트 :
시간 초과를 해결하는 데에 있어서 어려움을 겪었습니다.
### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1107 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String chan = br.readLine().trim();
		int bannedNum = Integer.parseInt(br.readLine().trim());
		List<Integer> banned = new ArrayList<>();
		if (bannedNum > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < bannedNum; i++) {
				banned.add(Integer.parseInt(st.nextToken()));
			}
		}
		int answer = 0;
		int press = 0;
		for (int i = 0; i < chan.length(); i++) {
			if (!banned.contains(chan.charAt(i) - '0')) {
				press++;
			}
		}
		if (press == chan.length()) {
			System.out.println(Math.min(Math.abs(Integer.parseInt(chan) - 100), chan.length()));
		} else {
			int chanNum1 = Integer.parseInt(chan);
			int chanNum2 = Integer.parseInt(chan);
			boolean notFound = true;
			int min = Math.abs(Integer.parseInt(chan) - 100);
			while (notFound) {
				answer++;
				chanNum1 += 1;
				chanNum2 -= 1;
				if (chanNum2 < 0) {
					chanNum2 = 0;
				}

				for (int k = 0; k < (chanNum1 + "").length(); k++) {
					if (banned.contains((chanNum1 + "").charAt(k) - '0')) {
						break;
					}
					if (k == (chanNum1 + "").length() - 1) {
						min = Math.min((chanNum1 + "").length() + answer, min);
						notFound = false;
					}
				}
				for (int k = 0; k < (chanNum2 + "").length(); k++) {
					if (banned.contains((chanNum2 + "").charAt(k) - '0')) {
						break;
					}
					if (k == (chanNum2 + "").length() - 1) {
						min = Math.min((chanNum2 + "").length() + answer, min);
						notFound = false;
					}
				}
				if (answer > Math.abs(Integer.parseInt(chan) - 100)) {
					break;
				}

			}
			System.out.println(min);

		}

	}
}
```

## BOJ2156 포도주 시식
### 🎈 해결방법 :
1. 포도주 정보를 cups 리스트에 저장
2. 특정 index의 포도주를 마시지 않을 때, 최대한 많은 양의 포도주를 마시는 경우를 maxCups 리스트에 저장
    - 해당 index 이전 3개의 index에 대해 최대값을 검사
    - maxCups[i-3]+cups[i-2]+cups[i-1] / maxCups[i-2]+cups[i-1] / maxCups[i-1]
3. maxCups[n] 값 출력
    - maxCups[0]은 언제나 0이다.


### 💬 코멘트 :
dp로 풀어야한다는 것은 일찍 파악했는데도 규칙을 찾는 것이 어려웠습니다. 새로운 시각으로 문제에 접근하는 기회가 되어서 좋았습니다.

### 📄 코드
```java
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
```

## BOJ17144 미세먼지 안녕!
### 🎈 해결방법 :
1. 미세먼지 정보를 받아 house 배열에 저장
2. 모든 좌표를 순회하며 만약 미세먼지가 존재한다면 spreadDust 메서드를 실행
3. 모두 돌았다면 실행 결과를 저장한 배열을 house에 덮어씌움
4. cleanTop, cleanBottom 메서드를 실행하여 공기청정기 작동
5. T만큼 반복 후 미세먼지 합계 출력

### 💬 코멘트 :
Python으로 풀고 Java로 한번 더 풀었는데, 처음에는 어렵게 느껴졌지만 두번째 푸니까 할만하다는 생각이 들었습니다. 공기청정기 돌릴 때 세로로 미세먼지를 옮기는 부분에서 제일 애먹었던 것 같습니다. 공기청정기 정보를 배열+리스트로 저장했는데 코드를 작성하다보니 헷갈려서 클래스로 분리했다면 더 좋았을 것 같다고 생각했습니다.

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17144 {
	static int[][] house;
	static List<int[]> cleaner;
	static int R;
	static int C;
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		house = new int[R][C];
		cleaner = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int dust = Integer.parseInt(st.nextToken());
				if (dust == -1) {
					cleaner.add(new int[] { c, r });
				}
				house[r][c] = dust;
			}
		}

		for (int t = 0; t < T; t++) {
			int[][] tmpHouse = new int[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					tmpHouse[r][c] += house[r][c];
					if (house[r][c] > 0) {
						spreadDust(c, r, tmpHouse);
					}
				}
			}
			house = tmpHouse;
			cleanTop(cleaner.get(0)[0], cleaner.get(0)[1]);
			cleanBottom(cleaner.get(1)[0], cleaner.get(1)[1]);
		}
		
		int total = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (house[r][c] > 0) {
					total += house[r][c];
				}
			}
		}
		System.out.println(total);
	}

	static void spreadDust(int x, int y, int[][] tmpHouse) {
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int dustAmount = house[y][x];
		int spreaded = 0;
		for (int[] d : dir) {
			if ((x + d[0] >= 0 && x + d[0] < C) && (y + d[1] >= 0 && y + d[1] < R)) {
				if (house[y + d[1]][x + d[0]] != -1) {
					tmpHouse[y + d[1]][x + d[0]] += dustAmount / 5;
					spreaded++;
				}
			}
		}
		tmpHouse[y][x] -= dustAmount / 5 * spreaded;
	}

	static void cleanTop(int x, int y) {
		int tmp = house[y][C - 1];
		System.arraycopy(house[y], 1, house[y], 2, C - 2);
		house[y][1] = 0;
		int tmp3 = house[0][0];
		System.arraycopy(house[0], 1, house[0], 0, C - 1);
		int tmp2 = 0;
		for (int i = y - 1; i >= 0; i--) {
			tmp2 = house[i][C - 1];
			house[i][C - 1] = tmp;
			tmp = tmp2;
		}
		for (int i = 1; i <= y - 1; i++) {
			tmp2 = house[i][0];
			house[i][0] = tmp3;
			tmp3 = tmp2;
		}
	}

	static void cleanBottom(int x, int y) {
		int tmp = house[y][C - 1];
		System.arraycopy(house[y], 1, house[y], 2, C - 2);
		house[y][1] = 0;
		int tmp3 = house[R - 1][0];
		System.arraycopy(house[R - 1], 1, house[R - 1], 0, C - 1);
		int tmp2 = 0;
		for (int i = y + 1; i <= R - 1; i++) {
			tmp2 = house[i][C - 1];
			house[i][C - 1] = tmp;
			tmp = tmp2;
		}
		for (int i = R - 2; i > y; i--) {
			tmp2 = house[i][0];
			house[i][0] = tmp3;
			tmp3 = tmp2;
		}

	}
}

```