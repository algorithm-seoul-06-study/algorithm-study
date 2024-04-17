package week12;

import java.util.PriorityQueue;

public class BOJ1647 {

    static int n, m, mx, ans;

    static class Info {
        int a, b, c;
        Info(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int[] p;
    static PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.c - i2.c);

    public static void main(String[] args) throws Exception {
        n = read(); m = read();
        while (m-- > 0) {
            int a = read();
            int b = read();
            int c = read();
            pq.add(new Info(a, b, c));
        }

        make_set();

        while (!pq.isEmpty()) {
            Info info = pq.remove();

            int ap = find(info.a);
            int bp = find(info.b);

            if (ap != bp) {
                union(ap, bp);
                ans += info.c;
                mx = Math.max(mx, info.c);
            }
        }
        System.out.println(ans - mx);
    }


    private static void make_set() {
        p = new int[n + 1];
        for (int i = 1; i <= n; i++) p[i] = i;
    }

    private static void union(int up, int vp) {
        p[up] = vp;
    }

    private static int find(int u) {
        if (p[u] == u) return u;
        return p[u] = find(p[u]);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
