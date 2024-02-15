# Week + 주차
## BOJ9095 1, 2, 3 더하기
### 🎈 해결방법 :
1. Memoization 활용
2. 1~3까지 초기값 정하기
3. 이후 재귀함수를 이용하여 n-3부터 n-1까지 더한 값을 return

### 💬 코멘트 :
약간 이지 하기는 했지만 규칙찾는것에 조금 머리씀

### 📄 코드
```java
package algorithm;

import java.util.Scanner;

public class BOJ9095 {
    static int[] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        memo = new int[11]; // memoization 이용, 초기값 설정 1,2,3일 때
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            System.out.println(result(n));
        }

    }

    static int result(int n) { // 재귀함수로 앞에 3개 더한 값과 같음
        if (memo[n] != 0)
            return memo[n];
        memo[n] = result(n - 1) + result(n - 2) + result(n - 3);
        return memo[n];

    }
}

```

## BOJ9935 문자열 폭발
### 🎈 해결방법 :
1. stack에다가 문자 하나씩 저장
2. target문자열의 가장 마지막에 있는 문자를 stack에다가 넣을시, stack에서 get method를 이용하여 확인
3. target 문자열과 같을시 모두 pop해버린다
4. 결과 stack에 아무것도 없으면 FRULA를 출력

### 💬 코멘트 :
1. 누나들 동생 먼저 풀었습니다 ㅅㄱ
2. stack 연습하기에 좋은 문제였다고 생각이 드는 바입니다
3. FRULA

### 📄 코드
```java
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String target = br.readLine();
        // string으로 받기
        String result = removePattern(str, target);
        System.out.println(result);
    }

    public static String removePattern(String str, String target) {
        Stack<Character> stack = new Stack<>();
        // 문자열 내 char stack내로 push하기
        for (char c : str.toCharArray()) {
            stack.push(c);

            // 스택에 쌓인 문자열의 길이가 패턴의 길이보다 크거나 같을 때만 비교
            if (stack.size() >= target.length()) {
                boolean isPattern = true; 
                for (int i = 0; i < target.length(); i++) { // target 문자열과 같은지 비교
                    if (stack.get(stack.size() - target.length() + i) != target.charAt(i)) {
                        isPattern = false;
                        break;
                    }
                }

                // 패턴을 발견한 경우 스택에서 패턴 길이만큼 문자를 제거
                if (isPattern) {
                    for (int i = 0; i < target.length(); i++) {
                        stack.pop();
                    }
                }
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) { // 결과 뽑기
            result.append(stack.pop());
        }
        // 결과의 길이가 0이면 FRULA뽑기
        return result.length() == 0 ? "FRULA" : result.reverse().toString();
    }
}
```

... 문제 수 만큼 작성 ...