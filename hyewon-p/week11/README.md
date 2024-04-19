# Week12

## BOJ16235 나무 재테크

### 🎈 해결방법 :
빡구현

### 💬 코멘트 :
(x,y)라고 써놨으면서 사실은 (y,x)인 게 뭐임 장난하나

### 📄 코드
```java
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ16235 {

    static class Tree implements Comparable<Tree>{
        int x, y, age;
        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }

    }
    static int[][] ground;
    static int[][] A;
    static PriorityQueue<Tree> trees;
    static Queue<Tree> deadTrees;
    static Queue<Tree> grownTrees;
    static int aliveTreeAmount;
    public static void main(String[] args) throws IOException {
        int N = readInt(),M = readInt(), K = readInt();
        ground = new int[N][N];
        A = new int[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                ground[i][j] = 5;
                A[i][j] = readInt();
            }
        }
        trees = new PriorityQueue<>();
        deadTrees = new ArrayDeque<>();
        grownTrees = new ArrayDeque<>();
        aliveTreeAmount = M;
        for (int m=0; m<M; m++){
            int y = readInt()-1;
            int x = readInt()-1;
            trees.add(new Tree(x, y, readInt()));
        }
        for (int k=0; k<K; k++){
            // 봄
            spring();
            // 살아있는 나무 없으면 그냥 탈출
            if (deadTrees.size()==aliveTreeAmount) {
                aliveTreeAmount=0;
                break;
            }
            // 여름
            summer();
            // 가을
            autumn();
            // 겨울
            winter();
        }
        System.out.println(aliveTreeAmount);

    }


    private static void spring(){
        Queue<Tree> tmp = new ArrayDeque<>();
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (ground[tree.y][tree.x] >= tree.age) {
                ground[tree.y][tree.x] -= tree.age;
                tree.age++;
                if(tree.age%5==0){
                    grownTrees.add(tree);
                }else{
                    tmp.add(tree);
                }
            } else {
                deadTrees.add(tree);
            }
        }
        trees.addAll(tmp);


    }

    private static void summer() {
        while(!deadTrees.isEmpty()){
            Tree tree =deadTrees.poll();
            ground[tree.y][tree.x]+=tree.age/2;
        }
    }

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1};
    private static void autumn() {
        while(!grownTrees.isEmpty()){
            Tree tree =grownTrees.poll();
            for (int i=0; i<8; i++){
                int newY = tree.y+dy[i], newX = tree.x+dx[i];
                if (check(newX, newY)){
                    trees.add(new Tree(newX, newY, 1));
                }
            }
            trees.add(tree);
        }
    }

    private static void winter(){
        for (int i=0; i<ground.length; i++){
            for (int j=0; j<ground.length; j++){
                ground[i][j] += A[i][j];
            }
        }
        aliveTreeAmount = trees.size();
    }

    // 맵 내에 있는지 검사
    static boolean check(int x, int y){
        if (x<0 || x>=ground.length || y<0 || y>=ground.length) return false;
        return true;
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

```

## BOJ1647 도시 분할 계획

### 🎈 해결방법 :
크루스칼...인듯?
1. 거리 기준 오름차순으로 연결 정렬
2. 앞에서부터 빼보면서 연결함
3. 다 연결됐으면 마지막으로 연결된 길 끊음

### 💬 코멘트 :
조회 안 하는 조건을 잘못 써서 헤매다

### 📄 코드
```java
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
        // 조상 노드 저장
        houses = new int[N+1];
        for (int i=1; i<=N; i++){
            houses[i] = i;
        }

        // 길 정렬할 큐
        PriorityQueue<Route> routes = new PriorityQueue<>();
        for (int m=0; m<M; m++){
            int A = readInt(), B = readInt(), C = readInt();
            routes.add(new Route(A, B, C));
        }
        int answer = 0;
        int lastRoute = 0;
        while (!routes.isEmpty()) {
            Route route = routes.poll();
            // 만약 조상이 같다면 넘어갈 것
            // 검사할 때 꼭 getRoot 함수 써줘야 확실함
            if (houses[getRoot(route.A)]==houses[getRoot(route.B)]) continue;
            // 조상 통일
            houses[getRoot(route.A)] = houses[route.B];
            answer+=route.len;
            // 마지막으로 연결한 길 저장
            lastRoute = route.len;
        }
        System.out.println(answer-lastRoute);
    }

    // 조상 찾기
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
```