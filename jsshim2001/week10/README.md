# Week10
## BOJ10253 헨리
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 분모를 이용한 통분으로 계산하였습니다.

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 간단했음, 귀찮은건 자료형 정도?

### 📄 코드
```java

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
            
            // 분자: child
            long child = Long.parseLong(str[0]);
            
            // 분모: mother
            long mother = Long.parseLong(str[1]);
            
            // 통문 위한 새엄마
            long newmother = 0;
            while (true) {
            	// 통분했는데 분모에서 분자로 나눴을 때 나머지가 0이면 끝냄 (약분 가능함을 의미)
                if (mother % child == 0) {
                    break;
                }
                
                // 새엄마는 분모/분자의 몫+1부터 재탐사
                newmother = mother / child + 1;
                
                // 통분해서 계산했을 때 분자
                child = child * newmother - mother;
                // 통분해서 계산했을 때 분모
                mother = mother * newmother;
            }
            // break되면 분모/분자 -> 1/(어떤 수) 형태로 나와야함
            sb.append(mother / child + "\n");
        }
        System.out.println(sb);
    }
}

```