package week4;

import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ11866 {
    public static void main(String[] args) throws Exception {
        int n = read(), k = read();

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) q.add(i);

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cnt = 0;
        while (!q.isEmpty()) {
            cnt++;
            if (cnt % k != 0) q.add(q.remove());
            else if (q.size() > 1) sb.append(q.remove()).append(", ");
            else sb.append(q.remove());
        }
        sb.append(">");
        System.out.print(sb);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
