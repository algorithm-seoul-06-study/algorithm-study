# Week 4
## BOJ2263 트리의 순회
### 🎈 해결방법 :
1. 순회의 특징
   1. 후위 순회에서 그 트리의 최고 조상은 특정 범위 트리의 맨 마지막이라는 것
      1. 후위 순회 모양 -> \[{왼쪽자식트리} {오른쪽자식트리} {노드}]
   2. 중위 순회에서 최고 조상의 양 옆으로 자식 트리가 있다는 것을 이용
      1. 중위 순회 모양 -> \[{왼쪽자식트리} {노드} {오른쪽자식트리}]
2. 전위 순회는 루트 노드부터 탐색하므로,
   1. 후위 순회를 이용해 노드를 출력
   2. 현재 탐색 노드의 중위 순회에서의 인덱스를 구함
   3. 인덱스로 중위 순회 양 옆의 자식 트리의 크기를 구함
   4. 구한 트리 크기로 자식 크기만큼 후위 순회에서 자식 트리의 부모를 구함
   5. i부터 다시 시작, 단말 노드가 될 때까지 반복

### 💬 코멘트 :
순회의 특징을 알아차린다면, 아주 쉬운 문제가 될 것 같은 문제이다.
처음 저 특징을 봤을 때 신기해서 지금도 잊혀지지 않는다.

### 📄 코드
```java
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
     * @param in_st : inTree 첫 인덱스 (inclusive)
     * @param in_en : inTree 끝 인덱스 (exclusive)
     * @param post_st : inTree 첫 인덱스 (inclusive)
     * @param post_en : inTree 끝 인덱스 (exclusive)
     */
    private static void dfs(int in_st, int in_en, int post_st, int post_en) {

        // 만약에 첫 인덱스가 끝 인덱스보다 크거나 같아지면, 순회 종료 
        if (in_st >= in_en || post_st >= post_en) return;

        int root = postTree[post_en - 1]; // 현재 트리 상의 최상위 노드

        preTree[idx++] = root; // 현재 트리 상의 최상위 노드 저장

        int in_root_index = indexes[root]; // 부모노드의 inTree 인덱스 반환

        int l_size = in_root_index - in_st; // 좌측 자식 트리 size
        int r_size = in_en - in_root_index - 1; // 우측 자식 트리 size

        int l_in_st = in_st; // inTree 좌측 자식 트리 첫 인덱스
        int l_in_en = l_in_st + l_size; // inTree 좌측 자식 트리 끝 인덱스
        int l_post_st = post_st; // postTree 좌측 자식 트리 첫 인덱스
        int l_post_en = post_st + l_size; // postTree 좌측 자식 트리 끝 인덱스

        int r_in_st = in_root_index + 1; // inTree 우측 자식 트리 첫 인덱스
        int r_in_en = r_in_st + r_size; // inTree 우측 자식 트리 끝 인덱스
        int r_post_st = post_st + l_size; // postTree 우측 자식 트리 첫 인덱스
        int r_post_en = r_post_st + r_size; // postTree 우측 자식 트리 끝 인덱스

        dfs(l_in_st, l_in_en, l_post_st, l_post_en); //좌측 자식 트리 탐색
        dfs(r_in_st, r_in_en, r_post_st, r_post_en); //우측 자식 트리 탐색
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

## BOJ11866 요세푸스 문제 0
### 🎈 해결방법 :
1. 1 ~ N 까지 숫자를 큐에 담는다.
2. 순서를 하나씩 올리면서 순서가 k의 배수일 때, 큐에서 숫자를 꺼낸다.
3. 만약 순서가 k의 배수가 아니라면, 큐 맨 앞에 값을 맨 뒤로 보낸다.
4. 꺼낸 값을 차례로 담으면 요세푸스 순열이 된다.

### 💬 코멘트 :
원형 큐 문제인 것 같다. 자바엔 원형 큐가 없어서 인덱스를 조정해주는 게 귀찮았다.

### 📄 코드
```java
package week4;

import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ11866 {
    public static void main(String[] args) throws Exception {
        int n = read(), k = read();

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) q.add(i);

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cnt = 0; // 카운트
        
        // 큐가 빌 때까지
        while (!q.isEmpty()) {
            cnt++; // 카운트 증가
            // 카운트가 k의 배수가 아니면 큐 dequeue 한걸 enqueue 함
            if (cnt % k != 0) q.add(q.remove());
            else if (q.size() > 1) sb.append(q.remove()).append(", ");
            else sb.append(q.remove());
        }
        sb.append(">");
        System.out.print(sb);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

## BOJ17298 오큰수
### 🎈 해결방법 :
1. n까지 스택에 숫자를 저장한 배열의 인덱스를 차례대로 담는다.
2. 담기 전에, 본인과 담긴 top을 비교한다.
3. 담으려는 숫자가 top보다 크다면, 스택에서 꺼내고 꺼낸 값의 NGE는 현재 담으려는 숫자이다.
4. 만약 스택에 여전히 남아있는 숫자는 nge() = -1
5. NGE(꺼낸 값) <- 담으려는 숫자 (대입)

### 💬 코멘트 :
스택을 주로 다루는 문제는 전부 이런 형태의 문제여서 익숙했다.

### 📄 코드
```java
package week4;

import java.util.Stack;

public class BOJ17298 {

    static int n, idx;
    static int[] arr, nge; // arr : 입력 받은 숫자를 저장하는 배열, nge : NGE 값을 저장하는 배열

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        n = read();
        arr = new int[n];
        nge = new int[n];
        for (int i = 0; i < n; i++) arr[i] = read();

        Stack<Integer> idxStack = new Stack<>();
        // 입력 받은 값을 순회
        for (int i = 0; i < n; i++) {
            // 스택에 top이 arr[i]보다 작다면, top pop
            while (!idxStack.isEmpty() && arr[idxStack.peek()] < arr[i]) {
                idx = idxStack.pop();
                nge[idx] = arr[i];
            }
            // 스택에 넣어줌
            idxStack.push(i);
        }

        // 아직 스택에 남아있는 값의 오큰수는 -1;
        while (!idxStack.isEmpty()) nge[idxStack.pop()] = -1;

        for (int i = 0; i < n; i++) sb.append(nge[i]).append(" ");
        System.out.println(sb);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```