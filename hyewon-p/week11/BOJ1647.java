import java.io.IOException;
import java.util.*;

public class BOJ1647 {
    static class Route implements Comparable<Route>{
        int A, B, len;

        public Route(int a, int b, int len) {
            A = a;
            B = b;
            this.len = len;
        }

        @Override
        public int compareTo(Route o) {
            return this.len-o.len;
        }

    }

    static int[] houses;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        houses = new int[N+1];
        for (int i=1; i<=N; i++){
            houses[i] = i;
        }
        PriorityQueue<Route> routes = new PriorityQueue<>();
        for (int m=0; m<M; m++){
            int A = readInt(), B = readInt(), C = readInt();
            routes.add(new Route(A, B, C));
        }
        int answer = 0;
        int lastRoute = 0;
        while (!routes.isEmpty()) {
            Route route = routes.poll();
            if (houses[getRoot(route.A)]==houses[getRoot(route.B)]) continue;
            houses[getRoot(route.A)] = houses[route.B];
            answer+=route.len;
            lastRoute = route.len;
        }
        System.out.println(answer-lastRoute);
    }

    public static int getRoot(int a){
        if (houses[a] == a){
            return a;
        }
        return houses[a] = getRoot(houses[a]);
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
