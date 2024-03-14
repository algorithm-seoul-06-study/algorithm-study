import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ9328 {
	// 변수 선언
    static boolean[][] visit;
    static int[] key = new int[26];
    static List<Integer[]> lockedcoordinate;
    static char[][] map;
    static int h;
    static int w;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int t = 0; t < n; t++) {
        	// ArrayList
            lockedcoordinate = new ArrayList<>();
            String[] str = br.readLine().split(" ");
            h = Integer.parseInt(str[0]);
            w = Integer.parseInt(str[1]);
            result = 0;
            map = new char[h][w];
            visit = new boolean[h][w];
            key = new int[26]; // 알파벳 26개
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
            }
            String keys = br.readLine();
            // 초기 키 값이 0인 경우, 0이 아닌 경우로 나눠서 key값 받기
            if (!keys.equals("0")) {
                for (int i = 0; i < keys.length(); i++) {
                    key[keys.charAt(i) - 'a']++;
                }
            }
            
            // 벽(*)이 아니면 모두 true로 만들기
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] != '*') {
                        visit[i][j] = true;
                    }
                }
            }
            
            // 시작 좌표의 ArrayList
            List<Integer[]> list = new ArrayList<>();
            
            // 가장자리 탐색 후 시작 좌표 더하기 <Horizontal>
            for (int i = 0; i < w; i++) {
                if (map[0][i] != '*') {
                    list.add(new Integer[] { 0, i });
                }
                if (map[h - 1][i] != '*') {
                    list.add(new Integer[] { h - 1, i });
                }
            }
            // 마찬가지로 시작 좌표 구하기 <Vertical>
            for (int i = 1; i < h - 1; i++) {
                if (map[i][0] != '*') {
                    list.add(new Integer[] { i, 0 });
                }
                if (map[i][w - 1] != '*') {
                    list.add(new Integer[] { i, w - 1 });
                }
            }
            
            // 모든 시작 부분에 대해 move method 적용
            for (Integer[] position : list) {
                move(position[0], position[1]);
            }
            
            // key값과 잠긴 좌표들을 비교하여 일치할 때, 탐색 진행
            // 상응하는 key가 없을 경우 found가 false로 되어 loop가 종료됨
            boolean found = true;
            while (found) {
                found = false;
                for (int i = 0; i < lockedcoordinate.size(); i++) {
                    if (key[lockedcoordinate.get(i)[0]] != 0) {
                    	// 이미 lockedcoordinate으로 갔으므로 move내에서 visit으로 false가 나올 수 있어, true로 바꿔줌
                        visit[lockedcoordinate.get(i)[1]][lockedcoordinate.get(i)[2]] = true;
                        move(lockedcoordinate.get(i)[1], lockedcoordinate.get(i)[2]);
                        found = true;
                        lockedcoordinate.remove(i); // 잠긴 문 좌표 제거
                        break;
                    }
                }
            }
            // $의 개수 출력
            System.out.println(result);
        }
    }

    static void move(int startR, int startC) {
        Queue<Integer[]> queue = new LinkedList<>();
        // 시작 
        queue.add(new Integer[] {startR, startC});
        
        // queue가 비울 때까지 돌아감
        while (!queue.isEmpty()) {
        	// 현재 값을 뽑아 r, c에 저장
            Integer[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            
            // map 외부로 벗어나거나, 이미 찾았던 곳이면 이동 끝남
            if (!(0 <= r && r < h && 0 <= c && c < w) || !visit[r][c]) {
                continue;
            }
            // 현 좌표 false로 바꿈
            visit[r][c] = false;
            
            // 현 좌표 값이 A부터 Z사이면 잠긴 문의 좌표를 char값과 같이 저장
            if (0 <= map[r][c] - 'A' && map[r][c] - 'A' <= 25) {
                lockedcoordinate.add(new Integer[] { map[r][c] - 'A', r, c });
            }
            
            // key를 찾으면 key값에 더함
            if (0 <= map[r][c] - 'a' && map[r][c] - 'a' <= 25) {
                key[map[r][c] - 'a']++;
            }
            
            // 목표 찾으면 ++
            if (map[r][c] == '$') {
                result++;
            }
            
            // 키 없으면 넘어감
            if (0 <= map[r][c] - 'A' && map[r][c] - 'A' <= 25 && key[map[r][c] - 'A'] == 0) {
                continue;
            }
            
            // 상화좌우 탐색
            queue.add(new Integer[]{r + 1, c});
            queue.add(new Integer[]{r - 1, c});
            queue.add(new Integer[]{r, c + 1});
            queue.add(new Integer[]{r, c - 1});
            
            // 중복값 제거
            for (int i = lockedcoordinate.size() - 1; i >= 0; i--) {
                if (lockedcoordinate.get(i)[1] == r && lockedcoordinate.get(i)[2] == c) {
                    lockedcoordinate.remove(i);
                    break;
                }
            }
        }
    }
}
