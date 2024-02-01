import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ18870 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		// 정렬 후 찾는 값의 바로 전 범위에서 중복 없이 카운트
		int[] newarr = arr.clone();
		Arrays.sort(newarr);
		
		StringBuilder sb = new StringBuilder();
		
		// 정렬된 배열 순회
		for(int i=0; i<N; i++) {
			// 입력 순서대로 탐색하여 해당 인덱스 저장
			int idx = Arrays.binarySearch(newarr, arr[i]);
			// 인덱스 앞까지의 중복 확인
			int du = 0;
			for(int j=0; j<idx; j++) {
				if(newarr[j]==newarr[j+1]) du++;
			}
			sb.append(idx-du).append(" ");
		}
		
		System.out.println(sb);
		
		br.close();
	}
}