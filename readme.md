## 📜규칙

|  | 설명 |
| --- | --- |
| 스터디 진행 시간 | (목) 18:30 ~ 19:30 |
| 플랫폼 | 백준 |

* 시간이나 플랫폼의 수정 및 건의 사항이 있으면, 협의 후 결정


### 참여방법

BOJ 그룹 (https://www.acmicpc.net/group/19832)

### 상세 설명 *(매우 중요)*

1. clone [본인이 풀 각 pc(SSAFY컴, 랩탑, 데스크탑 등)에서 각 처음 한번만]

2. 문제 풀 때(pr 생성할 때)마다 본인 branch 생성 후, 생성한 branch로 전환
<br><span style="color:red"><strong> ※ 주의 : 꼭 반드시 본인 branch를 생성해야 함!!!</strong></span>
<br><span style="color:red"><strong> ※ 주의 : 꼭 반드시 [PR 규칙](#pullRequest) 지켜야 함!!!</strong></span>
`git branch 브랜치명(본인 아이디로)`
`git switch 브랜치명`

3. 문제 푸셈

4. add, commit, push origin {본인이생성한브랜치명}
<br><span style="color:red"><strong> ※ 주의 : [commit 메시지 컨벤션](#commit) 지켜서 올리자!!!!</strong></span>
    1. 본인이 풀었다면,  github에 pull request 페이지에 들어가서 create new P.R.
    <br><span style="color:red"><strong> ※ 주의 : 스터디 전까지 절대 merge 하지말기!!!!</strong></span>
    2. 못 풀었다면 (merge 전이라면),
        1. 현재 pc(이하 A)인 경우, 4로 돌아감
        2. 다른 pc(이하 B)에서 하는 경우,
            1. A에서 하던 내용을 원격 레포에 push 후,
            2. B에서 로컬 레포로 쓸 폴더를 새로 만들고 1부터 진행
            3. 본인이 생성한 브랜치를 pull
            <br>`git pull origin {본인이생성한브랜치명}`
        3. 4.1로 진행

5. 스터디 할 때 모여서, 모든 멤버 확인 후, merge


<br>

### 진행방식

1. 매 주 월마다 문제가 출시되면, 다음 모임까지 풀어서 PR을 날림.

    -   <sub style="color: rgba(0, 0, 0, 0.8); margin-left: 20px;"> 풀지 못한 문제의 경우에도 시도한 코드를 PR 날림.</sub>


2. 모임 시간에 맞춰 함께 PR을 보며, 코드 리뷰 진행.
    -   <sub style="color: #808080; margin-left: 20px;"> 코드 리뷰의 원활한 진행을 위해 언어는 `JAVA`를 사용.</sub>

3. 코드 리뷰가 끝나면 코드를 수정하고, PR을 수정.
4. PR 수정이 끝나면, merge
    -   <sub style="color: #808080; margin-left: 20px;"> 동기화를 위해 git pull을 진행할 수 있음.</sub>

5. 남는 시간에는 질문 및 각자 본인 시간 활용

<br><br>

### 파일 생성 규칙

#### 📁 Repository 폴더 구조
```
{Github ID}/{주차}/{"Site약자 + 문제번호".java}
```

<br>

ex) <br>
`kocoveen/week1/boj1004.java` <br>
`kocoveen/week10/boj9876543210.java` <br>
<br>

### 커밋 규칙 <a id="commit"></a>

- Commit subject : [태그] 문제번호

| 태그 | 설명 |
| --- | --- |
| [solved] | 해결 |
| [fix] | 수정 |
| [docs] | 문서 |
| [chore] | 빌드, 설정 파일 |

ex)

1. Commit subject : `[solved] boj28354`
2. Commit subject : `[fix] boj28354` 

<br><br>

### PR 규칙 <a id="pullRequest"></a>

PR Title : 이름 / 주차 / 요일

ex)  `장웅기 / 1주차 / 목`

PR 코멘트 : 템플릿에 맞게 작성

---

### 건의사항

MM채널, 개인 MM을 이용한다.