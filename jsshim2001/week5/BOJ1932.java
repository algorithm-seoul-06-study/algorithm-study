import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = (n * n + 1) / 2; 
        int[][] arr = new int[n][]; 
        
        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1]; // 삼각형 형태로 배열 만들기
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < arr[i].length; j++) { // 배열에 할당
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        
        for(int i=n-1;i>0;i--) { // 아래 배열부터 양쪽에 있는 값을 비교해서 큰 값을 상위 배열에 더하기
        	for(int j=0;j<arr[i].length-1;j++) {
        		arr[i-1][j]+=Math.max(arr[i][j],arr[i][j+1]);
        	}
        }
        
        System.out.println(arr[0][0]); // 맨 꼭대기가 가장 클 때
    }
}
