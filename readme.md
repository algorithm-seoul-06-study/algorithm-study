# 📜규칙

|  | 설명 |
| --- | --- |
| 스터디 진행 시간 | (목) 18:30 ~ 19:30 |
| 플랫폼 | 백준 |

* 시간이나 플랫폼의 수정 및 건의 사항이 있으면, 협의 후 결정


## 참여방법

BOJ 그룹 (https://www.acmicpc.net/group/19832)

<br>

## 상세 설명
### 최초 설정
1. 원격 레포지토리 클론
   ```
   git clone {url}
   ```
2. 로컬 개인 branch 생성 후 전환
   
   :exclamation:  **반드시 본인 branch를 사용해야 함**  :exclamation:
    ```
    git branch {브랜치명(본인 아이디로)}
    git switch {브랜치명}
    ```

### 최초 설정 이후
1. 메인 브랜치에 변경사항이 있을 수 있으므로 작업 전에 pull 하기
   ```
   git pull origin main
   ```
   만약 abort 된다면 아래를 시도<br>
   ```
   git pull --rebase origin main
   ```
   또는<br>
   ```
   git fetch --prune origin 
   git reset --hard origin/main
   ```
  
2. 문제 풀기
3. 로컬 개인 브랜치에서 작업 후, add & commit

   :exclamation:  **[commit 메시지 컨벤션](#commit) 지켜서 올리자**  :exclamation:
   ```
   git add .
   git commit -m "{커밋 메세지}"
   ```
   또는<br>
   ```
   git add {파일명}
   git commit -m "{커밋 메세지}"
   ```
   
   
5. 원격 저장소의 개인 branch로 push  
    ```
    git push origin {생성한 브랜치명}
    ```
6. 모든 문제에 대해 커밋을 완료했다면, github에 pull request 페이지에 들어가서 create new P.R.
    
    - :exclamation: **스터디 전까지 절대 merge 하지말기** :exclamation:

    - 남은 문제가 있다면,
        1. 계속해서 같은 PC에서 작업하는 경우 2번으로 돌아가 계속 진행 

        2. 다른 PC (이하 B)에서 작업을 진행하는 경우,
            1. A에서 진행하던 내용을 원격 레포에 push 후,
            2. B에서 로컬 레포로 쓸 브랜치를 생성한 뒤 1부터 진행
            3. 본인이 생성한 브랜치를 pull
            ```
            git pull origin {본인이생성한브랜치명}
            ```


5. 스터디 할 때 모여서, 모든 멤버 확인 후, merge

<br>

## 진행방식

1. 매 주 월마다 문제가 출시되면, 다음 모임까지 풀어서 PR을 날림.
    - 풀지 못한 문제의 경우에도 시도한 코드를 PR 날림.

2. 모임 시간에 맞춰 함께 PR을 보며, 코드 리뷰 진행.
    - 코드 리뷰의 원활한 진행을 위해 언어는 `JAVA`를 사용.

3. 코드 리뷰가 끝나면 코드를 수정.
4. PR 수정이 끝나면, merge
5. 남는 시간에는 질문 및 각자 본인 시간 활용
<br><br>

## 파일 생성 규칙

### 📁 Repository 폴더 구조
```
{Github ID}/{주차}/{"Site약자 + 문제번호".java}
```

ex) <br>
`kocoveen/week1/BOJ1004.java` <br>
`kocoveen/week10/BOJ9876543210.java` <br>
<br>

## 커밋 규칙 <a id="commit"></a>

### 문제 관련
**[태그] 문제번호**

| 태그 | 설명 |
| --- | --- |
| [solved] | 해결 |
| [unsolved] | 미해결 |
| [fix] | 수정 |

ex)<br>
`[solved] BOJ28354`<br>
`[fix] BOJ28354` 
<br>

### 기타
**[태그] 관련 정보** 
| 태그 | 설명 |
| --- | --- |
| [docs] | 문서 |
| [chore] | 빌드, 설정 파일 |

<br>

## PR 규칙 <a id="pullrequest"></a>

PR Title : 이름 / 주차 / 요일

ex)  `장웅기 / 1주차 / 목`

PR 코멘트 : 템플릿에 맞게 작성

---

## 건의사항

MM채널, 개인 MM을 이용한다.
