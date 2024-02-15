# Week 3
## BOJ9095 1, 2, 3 더하기
### 🎈 해결방법 :
1. 1, 2, 3을 각각 1, 2, 3의 합으로 나타내는 방법을 구함
2. n (단, n > 3)을 1처럼 진행하면,
   1. n - 1을 1, 2, 3의 합으로 나타내는 방법에 +1씩
   2. n - 2을 1, 2, 3의 합으로 나타내는 방법에 +2씩
   3. n - 3을 1, 2, 3의 합으로 나타내는 방법에 +3씩
3. 2의 (i, ii, iii)의 방법의 수를 전부 합하면, n을 1, 2, 3의 합으로 나타내는 방법의 수가 됨
4. 점화식
```math
f(n) = \left\{
\begin{matrix}
1 & \textrm{if $n = 1$} \\
2 & \textrm{if $n = 2$} \\
4 & \textrm{if $n = 3$} \\
f(n - 1) + f(n - 2) + f(n - 3) & \textrm{if $n > 3$}
\end{matrix}
\right.
```

### 💬 코멘트 :
한 6부터 눈치를 금방 챌 수 있었다.
DP는 점화식을 세우는 것이 중요한데, 점화식을 만들기 좋은 문제같다.

### 📄 코드
```java
public class BOJ9095 {
   public static void main(String[] args) throws Exception {
      int t = read();
      int[] dp = new int[12];
      dp[1] = 1;
      dp[2] = 2;
      dp[3] = 4;
      for (int i = 4; i <= 11; i++) {
         dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
      }

      StringBuilder sb = new StringBuilder();
      while (t-- > 0) {
         sb.append(dp[read()]).append('\n');
      }
      System.out.print(sb);
   }

   private static int read() throws Exception {
      int c, n = System.in.read() & 15;
      while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
      return n;
   }
}
```

## BOJ9935 문자열 폭발
### 🎈 해결방법 :
1. 문자열의 문자를 하나씩 스택에 담는다.
2. (스택의 사이즈가 폭발 문자열보다 클 때만) 스택에 하나씩 담을 때마다 바깥 부분부터 폭발 문자열이 있는지 확인한다.
   1. 폭발 문자열이 있으면, 스택에서 폭발 문자열 삭제
   2. 폭발 문자열이 없으면, 1번으로 넘어감

### 💬 코멘트 :
java Stack은 가장 스택의 안쪽 부분(0)부터 바깥 부분(size() - 1)까지 인덱스로 조회할 수 있다.
이것만 알면 간단하게 풀 수 있는 문제였다.

### 📄 코드
```java
import java.io.*;
import java.util.*;

public class BOJ9935 {
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String text = br.readLine(); // 문자열
      String pattern = br.readLine(); // 폭발 문자열

      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < text.length(); i++) {
         stack.push(text.charAt(i)); // 문자열의 문자를 순차적으로 push

         // 부분 문자열의 크기가 폭발 문자열의 크기보다 클 때
         // &&
         // 부분 문자열의 바깥 문자열이 패턴과 일치할 때
         if (stack.size() >= pattern.length() && isCorrect(stack, pattern)) {

            // 폭발 문자열을 스택에서 pop
            for (int j = 0; j < pattern.length(); j++) {
               stack.pop();
            }
         }
      }

      StringBuilder sb = new StringBuilder();

      if (stack.isEmpty()) {
         sb.append("FRULA");
      } else {
         for (char c : stack) {
            sb.append(c);
         }
      }
      System.out.println(sb);
   }

   /**
    * 부분 문자열(stack)의 바깥 부분 문자열과 폭발 문자열(pattern)이 같은지를 확인
    * @param stack 부분 문자열
    * @param pattern 폭발 문자열
    * @return 부분 문자열의 바깥 부분 문자열이 폭발 문자열과 같을 때 true, 그렇지 않으면 false
    */
   private static boolean isCorrect(Stack<Character> stack, String pattern) {
      int si = stack.size() - pattern.length();
      for (int pi = 0; pi < pattern.length(); pi++) {
         if (stack.get(si + pi) != pattern.charAt(pi)) {
            return false;
         }
      }
      return true;
   }
}
```
