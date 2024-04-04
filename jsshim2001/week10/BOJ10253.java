import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ10253 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            long child = Long.parseLong(str[0]);
            long mother = Long.parseLong(str[1]);
            long newmother = 0;
            while (true) {
                if (mother % child == 0) {
                    break;
                }
                if (child == 0) {
                    break;
                }
                newmother = mother / child + 1;
                child = child * newmother - mother;
                mother = mother * newmother;
            }
            sb.append(mother / child + "\n");
        }
        System.out.println(sb);
    }
}
