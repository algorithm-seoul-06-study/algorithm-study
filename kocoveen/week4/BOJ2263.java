package week4;

public class BOJ2263 {

    static int n, idx = 1; // idx : preTree의 인덱스
    static int[] preTree; // preorder 순회를 저장
    static int[] postTree; // postorder 순회를 저장
    static int[] inTree; // inorder 순회를 저장
    static int[] indexes; // inTree 값의 인덱스 저장

    public static void main(String[] args) throws Exception {
        n = read();
        preTree = new int[n + 1];
        inTree = new int[n + 1];
        postTree = new int[n + 1];

        indexes = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            inTree[i] = read();
            indexes[inTree[i]] = i;
        }

        for (int i = 1; i <= n; i++) postTree[i] = read();

        /* ================================ */

        // 순회 시작
        dfs(1, n + 1, 1, n + 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(preTree[i]).append(" ");
        }
        System.out.println(sb);
    }

    /**
     * 주어진 inorder와 postorder를 이용해 preorder를 구하는 메서드
     * @param in_st : inTree 시작점 (inclusive)
     * @param in_en : inTree 끝점 (exclusive)
     * @param post_st : inTree 시작점 (inclusive)
     * @param post_en : inTree 끝점 (exclusive)
     */
    private static void dfs(int in_st, int in_en, int post_st, int post_en) {

        if (in_st >= in_en || post_st >= post_en) return;

        int root = postTree[post_en - 1]; // 현재 트리의 부모 노드

        preTree[idx++] = root; // 부모노드를 preorder로

        int in_root_index = indexes[root]; // 부모노드의 inTree 인덱스 반환

        int l_size = in_root_index - in_st; // 좌측 자식 트리 size
        int r_size = in_en - in_root_index - 1; // 우측 자식 트리 size

        int l_in_st = in_st; // inTree 좌측 자식 트리 시작점
        int l_in_en = l_in_st + l_size; // inTree 좌측 자식 트리 끝점
        int l_post_st = post_st; // postTree 좌측 자식 트리 시작점
        int l_post_en = post_st + l_size; // postTree 좌측 자식 트리 끝점

        int r_in_st = in_root_index + 1; // inTree 우측 자식 트리 시작점
        int r_in_en = r_in_st + r_size; // inTree 우측 자식 트리 끝점
        int r_post_st = post_st + l_size; // postTree 우측 자식 트리 시작점
        int r_post_en = r_post_st + r_size; // postTree 우측 자식 트리 끝점

        dfs(l_in_st, l_in_en, l_post_st, l_post_en);
        dfs(r_in_st, r_in_en, r_post_st, r_post_en);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}