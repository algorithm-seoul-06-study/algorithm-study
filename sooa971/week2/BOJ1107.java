package aStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ1107 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int channel = sc.nextInt();
		List<String> list = new ArrayList<>();

		int n = sc.nextInt();

		//n이 10이면 100에서 부터 출발하는 거 밖에 없음
		if (n == 10) {
			System.out.println(Math.abs(100 - channel));
		} else {
			//n이 10이 아니면 숫자버튼 하나라도 누를 수 있음.
			for (int i = 0; i < n; i++) {
				list.add(Integer.toString(sc.nextInt()));
			}
			System.out.println(list);
			//포함되지 않는 숫자 빼고 
			int[] arr = new int[1000001];
			out: for (int i = 0; i < 1000001; i++) {
				String s = Integer.toString(i);
				for (int j = 0; j < n; j++) {
					if (s.contains(list.get(j))) {
						continue out;
					}
				}
				arr[i]=1;
			}
			
		
			
			//channel과 제일 가깝고 누른 버튼 수가 작은 걸 찾는다.
			//인덱스 저장
			int min =0;
			//비교값 저장
			int min_comp=1000001;
			for (int i=0; i<1000001; i++) {
				if (arr[i]==1) {
					if(Math.abs(channel -i)<min_comp) {
						min = i;
						min_comp=Math.abs(channel -i);
					}
				}
			}
			

//			System.out.println(min);
			int ans = Integer.toString(min).length() + (Math.abs(min - channel));
			// 100에서 up다운 하는 경우
			int ud100 = Math.abs(channel - 100);
			ans = ud100 >= ans ? ans : ud100;

			System.out.println(ans);

		}
	}


}
