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
