import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904 {

    static int N;
    static int k;
    static int length;
    static int beforeLength;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine()); 
        k = -1;
        length = 0;
        
        while (length < N) {
            
            k++;
            length = (2 * length) + (k + 3);
        }
        
        beforeLength = (length - (k + 3)) / 2;
                
        while (true) {
            if (N > beforeLength && N <= length - beforeLength) {
                
                if (N == beforeLength + 1) System.out.println("m");
                else System.out.println("o");
                break;
                
            } else if (N <= beforeLength){
                
                k--;
                length = beforeLength;
                System.out.println("1.before : " + beforeLength);
                beforeLength = (beforeLength - (k + 3)) / 2;
                System.out.println("2.before : " + beforeLength);
                        
            } else {
                
                N -= (beforeLength + (k + 3));
                k--;
                length = beforeLength;
                System.out.println("1.before : " + beforeLength);
                beforeLength = (beforeLength - (k + 3)) / 2;
                System.out.println("2.before : " + beforeLength);
            }
        }
    }
}