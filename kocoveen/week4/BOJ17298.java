package week4;

import java.util.Stack;

public class BOJ17298 {

    static int n, idx;
    static int[] arr, nge;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();


        n = read();
        arr = new int[n];
        nge = new int[n];
        for (int i = 0; i < n; i++) arr[i] = read();

        Stack<Integer> idxStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!idxStack.isEmpty() && arr[idxStack.peek()] < arr[i]) {
                idx = idxStack.pop();
                nge[idx] = arr[i];
            }
            idxStack.push(i);
        }

        while (!idxStack.isEmpty()) nge[idxStack.pop()] = -1;

        for (int i = 0; i < n; i++) sb.append(nge[i]).append(" ");
        System.out.println(sb);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}