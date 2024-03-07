# Week4
## BOJ1790 수 이어 쓰기 2
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 자릿수마다 개수 미리 배열화
2. 반복문을 통해 타겟 숫자가 몇자릿수인지 측정
3. 그 값을 통해 무슨 숫자가 오는지 출력

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 할만했습니다
2. 조금만 생각하면 금방 풀리는 문제

### 📄 코드
```java
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1790 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] arr = new int[9];
		
		// 배열 내에 개수 미리 정하기, 한 자릿수: 1*9. 두 자릿수: 2*90, 세 자릿수:3*900 ...
		for (int i = 1; i < 9; i++) {
			arr[i] = (int) (i * 9 * Math.pow(10, i - 1));
		}
		
		int cnt = 0;
		// k가 클 때까지 계속 빼기: k가 몇 자릿수인지 구하기
		for (int i = 0; i < 9; i++) {
			if(k>arr[i]) {
				k-=arr[i];
				cnt=i+1;
			}
		}
		
		// ex) 23이면 cnt=2 (두 자릿수), num = 그때 해당하는 수 (16)
		int num = (int) (Math.pow(10, cnt-1)+(k-1)/cnt);
		
		
		if(n<num) { // 그 수가 n보다 작으면 -1출력
			System.out.println(-1);
		} else { // 크면 원하는 자릿수 출력
			String str = Integer.toString(num);
			System.out.println(str.charAt((k-1)%cnt));
		}
	}
}

```

## BOJ1992 쿼드트리
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 전체 배열받아서 2*2 로 확인하기, 같으면 0 or 1로 바꾸기
2. 배열 업데이트해주기
3. 배열 가로 세로가 1이 될 때까지

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. EZ

### 📄 코드
```java
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

```

## BOJ2011 암호코드
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 규칙 찾기 -> 미리 배열 만듦
2. 규칙에 맞게 슬라이싱하기
3. 암호해석 불가 boolean 넣어서 판별

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 다른 방식으로 어케 풀지
2. 중간에 코드가 좀 복잡해진듯

### 📄 코드
```java
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


```
... 문제 수 만큼 작성 ...