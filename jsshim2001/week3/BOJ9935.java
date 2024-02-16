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