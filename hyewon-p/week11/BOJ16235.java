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
            spring();
            if (deadTrees.size()==aliveTreeAmount) {
                aliveTreeAmount=0;
                break;
            }
            summer();
            autumn();
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