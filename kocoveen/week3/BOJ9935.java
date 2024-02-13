package week3;

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
