import java.util.Scanner;

public class BOJ10253 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		for(int t=0; t<tc; t++) {
			double a=sc.nextDouble();
			double b=sc.nextDouble();
			
			//제일큰 분수가 1/2라서 2부터 시작
			double mod =2;
			
			//분자가 1이 될 때 까지 반복
			while(a!=1) {
				double ab = a / b;
				//1/2부터 시작해서 뺐을 때 0보다 크면 빼주고 분자 분모 재 설정
				if(ab-(1/mod)>0) {
					a=a*mod - b;
					b=b*mod;
				}
				// 기약분수 만드는 곳
				for(int i =(int)a; i>1; i--) {
					if(a%i==0&&b%i==0) {
						a/=i;
						b/=i;
					}
				}					
				mod+=1;
			}
			
			System.out.println((int)b);
		}
	}
}
