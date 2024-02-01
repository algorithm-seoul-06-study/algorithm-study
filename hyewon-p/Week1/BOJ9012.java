import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n;i++) {
            String p = br.readLine();
            String answer = "NO";
            int pair = 0;
            for (int j=0; j<p.length(); j++) {
                if(p.charAt(j)=='(') {
                    pair++;
                }else {
                    pair--;
                }
                if (pair<0) {
                    break;
                }
            }
            if (pair==0) {
                answer = "YES";
            }
            System.out.println(answer);
        }
    }
}