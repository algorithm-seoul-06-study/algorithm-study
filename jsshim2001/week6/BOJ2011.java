import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ2011 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("");
		int[] arr = new int[str.length];
		for (int i = 0; i < str.length; i++) { // 입력받기 - 배열화
			arr[i] = Integer.parseInt(str[i]);
		}
		int[] arr1 = new int[5000];
		int[] arr2 = new int[5000];
		arr1[0] = 1;
		arr2[0] = 0;
		for (int i = 1; i < 5000; i++) { // 규칙에 따라 배열 만들기
			arr1[i] = (arr1[i - 1] + arr2[i - 1]) % 1000000;
			arr2[i] = arr1[i - 1];
		}
		List<Integer> list = new ArrayList<>();

		boolean cond = true; // 해석할 수 있는지 없는지 추가
		int cnt = 0;
		list.add(0);
		for (int i = 0; i < str.length; i++) { 
			if (arr[i] == 0) { // 배열 특정수가 0일때
				if (i == 0) { // 배열 맨앞에 나오면 해석 불가
					cond = false;
				} else if (arr[i - 1] == 1 || arr[i - 1] == 2) { // 1 또는 2가 나오면
					list.add(i-1);
					list.add(i);
					list.add(i + 1);
				} else if (arr[i - 1] != 1 || arr[i - 1] != 2) { // 그 외에는 해석 불가
					cond = false;
				} else {
					list.add(i + 1); 
				}
			} else if (3 <= arr[i] && arr[i] <= 6) { // 3~6일 때는 앞에 1,2 확인할 필요 없
				list.add(i + 1);
			} else if (7 <= arr[i] && arr[i] <= 9) { // 7~9 일때는 1인지 2인지 확인 필
				if(i==0) {
					list.add(i+1);
				} else if(arr[i-1]==1) {
					list.add(i+1);
				} else {
					list.add(i);
					list.add(i + 1);
				}
			}
		}
		list.add(str.length);
		
		List<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < list.size() - 1; i++) { // 개수씩 슬라이싱
			list2.add(list.get(i + 1) - list.get(i));
		}
		int sum = 1;
		for (int k = 0; k < list2.size(); k++) { // 결과값 곱해서 구하기
			sum *= arr1[list2.get(k)];
			sum %= 1000000;
		}
		if (cond) { // 조건 거르기
			System.out.println(sum);
		} else {
			System.out.println(0);
		}

	}
}