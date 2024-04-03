# Week 10
## BOJ10253 헨리
### 🎈 해결방법 :
- **구현**
1. 현재 분수가 단위 분수가 아니라면,
   1. 현재 분수보다 작지만 가장 큰 단위 분수를 찾음
   2. 현재 분수에서 1-1에서 찾은 단위 분수를 뺌.
   3. 뺀 분수가 단위 분수가 될 때까지 1 반복
2. 현재 분수가 단위 분수면, 분모 출력

### 💬 코멘트 :
말은 쉽지만 구현이 어려웠다.

### 📄 코드
```java
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
            int[] unitFraction = getNearUnitFraction(origin); // 현재 분수에서 작거나 같지만 가장 큰 단위 분수를 찾음
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
```

## BOJ12865 평범한 배낭
### 🎈 해결방법 :
- **DP**
1. $
   f(i, j) =
   \begin{cases}
   max( f(i-1, j), f(i-1, j-W(i)) + V(i) ) & \text{if } j \geq W(i) \\
   f(i-1, j) & \text{if } j \lt 0
   \end{cases}
   $
   - 단, f(i, j)는 i번째 까지의 물품을 넣고, 무게 j까지 담을 수 있을 때에 가장 큰 가치
   - W(i)는 i번째 물품의 무게, V(i)는 i번째 물품의 가치

### 💬 코멘트 :
어렵다 외우자

### 📄 코드
```java
package week10;

public class BOJ12865 {

   static int n, k;              // n: 물품 수, k: 버티는 무게
   static int[] weights, values; // weights[i]: i번째 물품의 무게, values[i]: i번째 물품의 가치
   static int[][] dp;            // dp 배열

   public static void main(String[] args) throws Exception {
      n = read(); k = read();

      weights = new int[n + 1];
      values = new int[n + 1];

      for (int i = 1; i <= n; i++) {
         int w = read();
         int v = read();
         weights[i] = w;
         values[i] = v;
      }

      dp = new int[n + 1][k + 1];

      // i번째 물품과 무게 j에서 가장 많은 가치는 f(i, j) = max( f(i-1, j), f(i-1, j-W(i)) + V(i));
      for (int i = 1; i <= n; i++) {
         for (int j = 1; j <= k; j++) {
            if (j >= weights[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
            else dp[i][j] = dp[i - 1][j];
         }
      }

      System.out.println(dp[n][k]);
   }

   static int read() throws Exception {
      int c, n = System.in.read() & 15;
      while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
      return n;
   }
}
```