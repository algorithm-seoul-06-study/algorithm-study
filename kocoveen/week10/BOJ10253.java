package week10;

public class BOJ10253 {
    public static void main(String[] args) throws Exception {

        int t = read();
        while (t-- > 0) {
            int num = read();   // num: 분자
            int denom = read(); // denom: 분모

            // 분자(index 0)와 분모(index 1)를 배열로 표현
            int[] origin = {num, denom};

            // 단위 분수가 아니면, 계속 실행
            while (!isUnitFraction(origin)) {
                int[] unitFraction = getNearUnitFraction(origin); // 현재 분수에서 작지만 가장 큰 단위 분수를 찾음
                origin = diffFraction(origin, unitFraction); // 현재 분수를 찾은 단위 분수를 뺌
            }
            System.out.println(origin[1]);
        }
    }

    /**
     * 현재 분수에서 작지만 가장 큰 단위 분수를 찾는 메서드
     * @param origin : 현재 분수
     * @return : 현재 분수에서 작지만 가장 큰 단위 분수
     */
    private static int[] getNearUnitFraction(int[] origin) {
        int num = origin[0];
        int denom = origin[1];
        while (denom % num != 0) denom++;
        return new int[]{1, denom / num};
    }

    /**
     * 현재 분수를 찾은 단위 분수를 빼는 메서드
     * @param origin : 현재 분수
     * @param unitFraction : 현재 분수보다 작지만 가장 큰 단위 분수
     * @return : origin - unitFraction;
     */
    private static int[] diffFraction(int[] origin, int[] unitFraction) {
        int num = origin[0] * unitFraction[1] - origin[1];
        int denom = origin[1] * unitFraction[1];   // 통분
        int gcd = gcd(num, denom); // 최대공약수
        return new int[]{num / gcd, denom / gcd};  // 약분 후 저장
    }

    // 분수가 단위 분수인지 확인하는 메서드
    private static boolean isUnitFraction(int[] fraction) {
        return fraction[0] == 1;
    }

    private static int gcd(int A, int B) {
        if (B == 0) return A;
        return gcd(B, A % B);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
