import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] strarr = new String[n][n]; // n*n 2차원 배열
        for (int i = 0; i < n; i++) {
            strarr[i] = br.readLine().split("");
        }

        while (n > 1) { // n이 1일 때까지 압축
            String[][] strarr2 = new String[n / 2][n / 2]; // 딱 반만큼의 배열 만들기
            for (int i = 0; i < n; i += 2) { // 가로세로 2*2 조사
                for (int j = 0; j < n; j += 2) { 
                    if (strarr[i][j].equals(strarr[i][j + 1]) &&
                            strarr[i][j + 1].equals(strarr[i + 1][j]) &&
                            strarr[i + 1][j].equals(strarr[i + 1][j + 1]) && (strarr[i][j].equals("0")||strarr[i][j].equals("1"))) { // 모두 같고 0 또는 1일 경우 0,1저장
                        strarr2[i / 2][j / 2] = strarr[i][j];
                    } else { // 그렇지않으면 (0011) 이런 식으로 저장
                        strarr2[i / 2][j / 2] = "(" + strarr[i][j] + strarr[i][j + 1] +
                                strarr[i + 1][j] + strarr[i + 1][j + 1] + ")";
                    }
                }
            }
            strarr = strarr2; // 배열 덧입히기
            n /= 2; // 크기 줄이기
        }
        System.out.println(strarr[0][0]); // 결국 1*1인 배열만 남음

    }
}