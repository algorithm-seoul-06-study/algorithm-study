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