# Week4
## BOJ17298 오큰수
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 우선 받은 배열의 뒤부터 탐색
2. 결과 값을 result stack으로 받음
3. 스택에 값이 존재하는 조건으로 반복하여 stack.peek값이 현재 배열보다 클 때까지 반복하며, 그 때를 만족하면 result stack에 push
4. stack이 비어있을 때 -1을 result stack에 push
5. result stack의 가장 나중에 쌓인 것부터 stringbuilder를 통해 출력

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 시간 초과를 고려해야해서 조금 까다롭지만 스택만 알고 있어서 금방 해결할 수 있었다
2. stringbuilder를 적극적으로 사용해야겠다. 하지만 45같은 정수를 stringbuilder를 통해 넣고 reverse하면 54가 나오기 때문에 result stack을 따로 만들었다, 이 부분이 조금 그랬음

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int[] arr = new int[T];
        // input을 배열로 받기
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> result = new Stack<>();
        
        // stack을 받은 배열의 끝부분 부터 확인하여 stack의 끝이 현재 부분보다 크다면 result stack에 더함
        for (int i = T - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() > arr[i]) {
                    result.add(stack.peek());
                    break;
                } else {
                    stack.pop();
                }
            } // stack이 비었으면 -1을 result stack에 넣음
            if (stack.isEmpty()) {
                result.add(-1);
            }
            stack.push(arr[i]);
        }
        
        // StringBuilder 이용하여 result의 끝부터 뽑아 더하기
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
```

## BOJ11866 요세푸스 문제 0
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. cnt를 넣어 cnt가 두번 째로 받은 수의 약수일 경우 queue값을 뽑아낸다
2. 약수가 아니면 다시 queue에 넣음

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 누가봐도 원형큐를 사용하는 문제, 스택 쓰라는줄 알고 당황
2. EZ

### 📄 코드
```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ11866 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		// 1부터 N까지 queue에 넣기
		for(int i=1;i<=N;i++) {
			queue.add(i);
		}
		System.out.print('<');
		// cnt를 1로 초기화
		int cnt=1;
		while(!queue.isEmpty()) {
			// cnt가 K의 배수라면 queue를 뽑아내고 print
			if(cnt%K==0 && queue.size()!=1) {
				System.out.print(queue.poll()+", ");
			} else if(cnt%K==0 && queue.size()==1){
				// queue size 가 1일 때는 마지막일 때 이므로 '>' print
				System.out.print(queue.poll()+">");

			}
			
			// 그렇지 않으면 queue에서 뽑아내서 제일 끝에 add
			else {
				queue.add(queue.poll());
			}
			// cnt를 계속 더함
			cnt++;
		}
		
		
	}
}
```

... 문제 수 만큼 작성 ...