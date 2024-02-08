package week2;

import java.io.*;

public class BOJ1107 {

    static int n, m; // n = 가야할 채널, m = 고장난 버튼 수
    static int broken; // 고장난 버튼 번호를 bit로 저장

    public static void main(String[] args) throws IOException {
        n = read();
        m = read();
        for (int i = 0; i < m; i++) {
            broken |= 1 << read();
        }

        int min = Math.abs(n - 100); // 버튼을 눌러야 할 최소 수를 가장 최대로 눌러야 할 횟수로 잡음
        // 0 ~ 999999 의 채널에서 거쳐갈 채널(이하 i)을 찾음
        // 최대 999999로 잡은 이유는 숫자 버튼은 9까지고, 눌러야 할 최대 횟수는 6번이 최대이기 때문
        for (int i = 0; i <= 999999; i++) {
            String channel = Integer.toString(i);

            // i를 거쳐갈 수 있는지 체크
            boolean isBlocked = false;
            for (int j = 0; j < channel.length(); j++) {
                int button = channel.charAt(j) - '0';
                if ((broken & (1 << button)) == (1 << button)) {
                    isBlocked = true;
                    break;
                }
            }

            // 만약 버튼이 고장나 그 채널을 거치치 못하면,
            //      다음으로 넘어감
            if (isBlocked) continue;

            // 고장이 나지 않아 채널을 거쳐갈 수 있다면,
            //      min(채널번호의 길이 + |거쳐갈 채널 - 최종 채널|)
            int cnt = channel.length() + Math.abs(n - i);
            min = Math.min(min, cnt);
        }
        System.out.println(min);
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}