import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strin = br.readLine();
        int n = Integer.parseInt(strin.split(" ")[0]);
        int m = Integer.parseInt(strin.split(" ")[1]); 

        int[][] arr = new int[n][n]; 
        
        // 같으면 0, 아니면 m 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = (i == j) ? 0 : m; 
            }
        }
        
        // 바로 옆 친구면 1 저장
        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]) - 1; 
            int b = Integer.parseInt(str[1]) - 1; 
            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        // 전체 탐색 하여 작은 걸로 없데이트
        // 이유: 1에서 3으로 갈 때 (1에서 2)+(2에서 3)으로 갈 때를 의미, 그 때 최소 값 업데이트
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        // 합계의 최소 구하기, 그 때의 Index 저장
        int min = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
            }
            if (min > sum) {
                min = sum;
                minIdx = i;
            }
        }
        
        // Index로 받았으므로 minIdx+1을 해줘야 함
        System.out.println(minIdx + 1); 
    }
}