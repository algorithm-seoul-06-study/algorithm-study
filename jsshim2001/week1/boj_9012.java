import java.util.Scanner;

public class boj_9012 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i =0; i<T;i++) {
			int l=0;
			int r=0;
			int flag=1;
			String str = sc.next();
			char[] chararr=str.toCharArray();
			if(chararr[0]=='('&&chararr[chararr.length-1]==')') {
				for(int j=0;j<chararr.length;j++) {
					if(chararr[j]=='(') {
						l++;
					} else {
						r++;
					}
				}
				if(l==r) {
					for(int j=0;j<chararr.length;j++) {
						int l2=0;
						int r2=0;
						if(chararr[j]==')') {
							for(int k=0;k<=j;k++) {
								if(chararr[k]=='(') {
									l2++;
								} else {
									r2++;
								}
							} 
							if(l2<r2) {
								System.out.println("NO");
								flag=0;
								break;
							}
						}
					}
					
					if(flag==1) {
						System.out.println("YES");
					}
				} else {
					System.out.println("NO");
				}
			}else {
				System.out.println("NO");
			}
			
		}
	}
}