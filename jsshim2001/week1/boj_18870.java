import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class boj_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        Set<Integer> set = new HashSet<>();

        String[] input = br.readLine().split(" "); // Split the input string into an array of strings

        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(input[i]);
            indexMap.putIfAbsent(arr[i], 0); // Avoid overwriting existing indices
        }

        // Sort the unique elements
        int[] intArray = indexMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(intArray);

        for (int i = 0; i < T; i++) {
            System.out.printf("%d ", binarySearch(intArray, arr[i], 0, intArray.length));
        }

        br.close();
    }

    static int binarySearch(int arr[], int key, int low, int high) {
        int mid;

        if (low <= high) {
            mid = (low + high) / 2;

            if (key == arr[mid]) {
                return mid;
            } else if (key < arr[mid]) {
                return binarySearch(arr, key, low, mid - 1);
            } else {
                return binarySearch(arr, key, mid + 1, high);
            }
        }

        return -1;
    }
}