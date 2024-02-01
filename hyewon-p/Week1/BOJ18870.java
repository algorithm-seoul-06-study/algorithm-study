import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Integer[] arr = new Integer[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> indexArr = new HashSet<>(Arrays.asList(arr));
        List<Integer> ia = new ArrayList<>(indexArr);
        Map<Integer, Integer> hash = new HashMap<>();
        ia.sort((a,b)->a-b);
        for (int i=0; i<ia.size();i++) {
            hash.put(ia.get(i), i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n;i++) {
            sb.append(hash.get(arr[i])+" ");
        }
        System.out.print(sb.toString());

    }
}