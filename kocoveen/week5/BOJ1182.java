package week5;

public class BOJ1182 {

    static int n, s, cnt; // n: 정수 개수, s: 찾을 수, cnt: s 갯수
    static int[] arr; // 수열이 담긴 배열

    public static void main(String[] args) throws Exception {
        n = read(); s = read();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = read();

        // arr[0]부터 더할 부분 수열의 합을 저장하는 변수 sum 설정
        subsequenceSum(0, 0);
        if (s == 0) cnt--; // 공집합의 경우가 항상 포함되기 때문에, s가 0이라면 카운트 1개를 뺴줌
        System.out.println(cnt);
    }


    // depth: 수열의 위치를 나타내는 변수, sum: 현재 depth까지 더한 부분수열의 합
    private static void subsequenceSum(int depth, int sum) {
        if (depth == n) {
            if (sum == s) cnt++;
            return;
        }

        // 현재 arr[depth]을 더하거나
        subsequenceSum(depth + 1, arr[depth] + sum);
        // 현재 arr[depth]을 더하지 않거나
        subsequenceSum(depth + 1, sum);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }
}