package week8;

import java.util.Scanner;

public class BOJ5904 {

    static int k;     // 입력값
    static int[] arr; // Moo 수열의 길이를 저장할 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();

        arr = new int[30];
        arr[0] = 0;
        arr[1] = 3;

        for (int i = 1; i < arr.length - 1; i++) {
            arr[i + 1] = arr[i] * 2 + i + 3;
        }

        int index = 30;
        int prevLen = arr[index - 1];
        System.out.println(func(k, index, 1, 1 + prevLen, 1 + prevLen + index + 2));

    }

    // Moo 수열의 k 위치를 구할 메서드
    // k: 입력 값, n: Moo 수열의 항, l_st: 왼쪽 부분의 첫 위치, m_st: 중간 부분의 첫 위치, l_st: 오른쪽 부분의 첫 위치
    static String func(int k, int n, int l_st, int m_st, int r_st) {

        // 항이 0 이면, 알파벳을 구함
        if (n == 0) {
            return getMoo(k, m_st);
        }

        String result;
        int prevLen = arr[n - 1]; // 직전 항의 길이를 구함

        
        if (k < m_st) {         // 만약 입력 값이 m_st 보다 작으면 -> 왼쪽 부분에 대해 func
            result = func(k, n - 1, l_st, l_st + prevLen, l_st + prevLen + n + 2);
        } else if (k < r_st) {  // 만약 입력 값이 r_st 보다 작으면 -> 중간 부분에서 알파벳을 구함
            return getMoo(k, m_st);
        } else {                // 그렇지 않으면 -> 오른쪽 부분에 대해 func
            result = func(k, n - 1, r_st, r_st + prevLen, r_st + prevLen + n + 2);
        }
        
        // 반환 결과를 리턴
        return result;


    }

    // 알파벳을 구하는 메서드
    static String getMoo(int n, int m_st) {
        if (n == m_st) return "m";
        return "o";
    }
}
