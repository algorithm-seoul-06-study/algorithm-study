package week6;

public class BOJ1790 {
    public static void main(String[] args) throws Exception {
        long n = read(); long k = read();

        long len = 1, exp = 1, tmp;
        while (k > (tmp = 9L * exp * len)) {
            k -= tmp;
            exp *= 10;
            len++;
        }

        k--;
        long target = exp + k / len;

        if (target > n) System.out.println(-1);
        else System.out.println(String.valueOf(target).charAt((int) (k % len)));
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
