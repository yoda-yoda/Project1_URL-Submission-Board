# 1차 프로젝트 - **URL 입력 게시판 프로젝트 (url-submission-board project)**

정해진 URL 입력에 따라 CRUD (생성, 조회, 수정, 삭제) 동작을 하며 회원에 따라 기능이 제한되는 게시판을 구현한다.

**(최종 완성 파일 => src/main/java 폴더의 FinalBoard.java)**

<br><br>

## 게시판 사용법

- `관리자` (관리자 등급) 계정은 미리 생성해놓았습니다. 계정명은 `관리자` 이고, 비밀번호도 `관리자` 입니다. 회원 번호는 `1`번 입니다.
- 게시판 추가는 `/boards/add`
- 게시판 수정은 `/boards/edit?boardId=1` 과 같은 형식입니다. `=` 뒤는 게시판 번호가 오고 숫자 1부터 입력가능합니다.
- 게시판 확인(view)은 `/boards/view?boardName=자유게시판` 과 같은 형식이고 `=` 뒤에는 게시판의 이름을 입력가능합니다.
- 게시글 추가는 `/posts/add?boardId=1` 과 같습니다.
- 게시글 수정은 `/posts/edit?postId=1&boardId=1` 과 같은 형식입니다.
- 게시글 확인(view)은 `/posts/view?postId=1&boardId=1` 과 같은 형식입니다.
- 계정 수정은 `/accounts/edit?accountId=1` 과 같습니다. `=` 뒤는 회원 번호를 입력합니다.

나머지 입력 형태는 위 사항과 같습니다.

<br><br>

## 요구사항과 해결방법

### 1-(1)

- 요구사항:

명령어를 입력받아서 출력할 수 있는 형태의 프로그램을 작성하세요.

명령어는 `명령어 >` 라는 문자 뒤에 공백 하나로서 받는 형태입니다.

```java
명령어 > 안녕하세요!
안녕하세요!
명령어 >

```

- 해결:

여기서 명령어를 입력받는다는것은 프로그램이 키보드의 입력을 감지해야한다는 것이다.

그리고 출력하려면 그 입력 값이 저장되어있어야 할것이다.

출력이라는건 어떤 데이터를 가져와서 내보내는 것이기 때문이다. 입력하는 데이터는 입력 버퍼라는 곳에 저장된다.

자바의 [System.in](http://system.in/) 이라는 변수가 그 역할을 한다.

그리고 입력 버퍼에 저장된 데이터를 꺼내서 사용 가능하게 하는 역할을 Scanner 라는 객체가 한다.

그리고 Scanner의 nextLine 메소드를 이용하면 입력 값을 문자열로 반환해준다.

그래서 그것들을 이용했다.

```java
Scanner sc = new Scanner(System.in);
String userInput = sc.nextLine();

```

`userInput` 변수에 입력값을 담았다.

### 1-(2) a

- 요구사항:`종료` 혹은 `exit` 등 특정 명령어를 입력하였을 때에만 프로그램이 종료되도록 구성해 주세요.

```
명령어 > 안녕하세요!
안녕하세요!
명령어 > 종료
프로그램이 종료됩니다.

```

- 해결:

문자열의 메모리 주소를 비교하지않고 문자열의 생김새 자체가 같은지 비교하는 방법이 있었다. String 클래스의 `equals` 메서드를 이용했다.

### 1-(2) b

- 요구사항:
종료되는 이외의 명령어를 입력하였을 때외에는 `존재하지 않는 명령어` 임을 사용자에게 알려주세요.

출력 예시)

```
명령어 > 안녕하세요!
존재하지 않는 명령어 입니다.
명령어 > 종료
프로그램이 종료됩니다.

```

- 해결:

```
else {
    System.out.println("존재하지 않는 명령어 입니다.");
}

```

if문을 이용했고, 원하는 입력값이 아닌 경우 전부 else로 나가게 만들었다.

### 1-(3) a

- 요구사항:
    
    게시글을 저장할 수 있는 객체를 정의해주세요.
    
- 해결:

```
 Map<String, String> boardStorage = new HashMap<>();
 LinkedList<String> keyIndexStorage = new LinkedList<>();

```

LinkgedList로 만든 이유 =>
원래는 Deque 로 만들려고 했는데, Deque는 인덱스번호만으로 값을가져오는 메서드가 없고 key값을 직접 입력해야했기 때문이다.

### 1-(3) b

- 요구사항:
다음은 작동하는 명령어와 그 동작에 대한 명세입니다. 정의한 객체를 아래의 명세에 맞추어 동작할 수 있도록 프로그램을 구성하세요.

| **명령어** | **동작** |
| --- | --- |
| 작성 | 글의 제목과 내용을 입력받아 게시글을 작성할 수 있습니다. |
| 조회 | 마지막으로 작성한 게시글을 확인할 수 있습니다.  조회한 게시물은 다음과 같이 출력됩니다.  :제목; 작성시 입력한 제목. 내용; 작성시 입력한 내용 |
| 삭제 | 마지막으로 작성한 게시글을 삭제합니다. |
| 수정 | 마지막으로 작성한 게시글을 수정합니다. 수정시, 제목과 내용이 모두 수정됩니다. |
- 해결:

```
if (userInput.equals("작성")) {

  System.out.print("제목을 작성해주세요 :");
  String key = sc.nextLine();
  System.out.print("내용을 작성해주세요 :");
  String value = sc.nextLine();
  keyIndexStorage.add(key);          //key와 index를 저장하기위한 영구저장 역할.
  boardStorage.put(key, value);      //Map에 제목(키), 내용(밸류) 로 저장.
  System.out.println();
  System.out.println("게시물이 저장되었습니다.");

```

이런식으로 if문에서 원하는 입력이 true이면,
저장 객체에 저장하도록 하였다.

조회, 삭제, 수정의 경우 저장소의 인덱스를 이용하여 몇번째 자료인지 확인할 수 있도록했다.

수정은 기존 인덱스 자료를 삭제한뒤에 그 자리에 추가하는 형태로 하였다. Map의 경우는 인덱스가 없어서, Map의 key(제목)와 링크드리스트의 값을 일치시켰고 링크드의 인덱스와 연결하여 순서를 알수있도록 활용하였다.

### 1-(4) a

- 요구사항:
    
    각각의 게시글은 번호를 갖습니다. 번호는 작성할 때 마다 하나씩 증가하도록 구성하세요.
    
- 해결:

```
 Map<String, String> boardStorage = new HashMap<>();
 LinkedList<String> keyIndexStorage = new LinkedList<>();

```

자동으로 인덱스가 하나씩 증가되는 링크드리스트를 처음 저장소를 만들때 활용했다.

### 1-(4) b

- 요구사항:
    
    게시글이 삭제되면 삭제된 자리는 다음 게시글이 위치합니다. 1번 2번 3번 게시글이 있을 때, 2번 글을 삭제하면 1번글 다음은 3번글이 되는 것입니다.
    
- 해결:

```
 Map<String, String> boardStorage = new HashMap<>();
 LinkedList<String> keyIndexStorage = new LinkedList<>();

```

중간 인덱스 자료를 삭제하면 자동으로 당겨지는 링크드리스트를 활용했다.

### 1-(5)

- 요구사항:
    
    게시물 기능을 요구사항을 만족하도록 수정하세요.
    
    (게시물 몇번인지 선택하기)
    
    수정될 명령어와 그 명령어에 대한 동작 명세를 따르고, 3번에서 작성한 기능을 수정한다.
    
- 해결:

```
 //조회 예시

    if (userInput.equals(i + "번")) {

        System.out.println();
        System.out.println(i + "번 게시물");
        System.out.println("제목 :[" + keyIndexStorage.get(i - 1) + "]");
        System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(i - 1)) + "]");
        okCheck = true; // 올바른 경우로 정해주기위함.
        break;
                        }

```

사용자에게 입력받은 번호를 인덱스와 일치시켰다.

### 1-(6)

- 요구사항:
    
    게시물이 존재하지 않을 경우 예외가 발생하고 그 예외를 처리할 수 있도록 구성하세요.
    
- 해결:

```
 if (okCheck == false) {

         try {
             boardStorage.remove(keyIndexStorage.get(i)); //오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
         } catch (IndexOutOfBoundsException e) {
             System.out.println();
             System.out.println(userInput + " 게시글은 존재하지 않습니다."); //
         }
     }

```

if문 위에서 boolean을 활용해서, 실제 게시물이 존재하지 않을때만 진입하도록 하였다.

여기 진입한다는것은 유저의 입력값이 "0번" 또는 존재하는 게시글을 초과한 "?번" 이라는 뜻이다.

### 1-(7)

- 요구사항:
    
    게시글 목록을 확인할 수 있는 기능을 구성해주세요.
    
- 해결:

```
 else if(userInput.equals("목록")){
     System.out.printf("총 게시글은 %d개 작성되어있습니다." , keyIndexStorage.size());
     System.out.println();
     System.out.println();

    for(int i=1; i<=keyIndexStorage.size(); i++){

        System.out.println(i + "번 게시물");
         System.out.println("제목 :[" + keyIndexStorage.get(i - 1) + "]");
         System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(i - 1)) + "]");
         System.out.println();
     }

}

```

이런식으로 for문을 이용해서 링크드리스트 저장소의 size 만큼 반복하도록 하였다.

### 2-(1)

- 요구사항:
    
    명령어를 URL과 같이 구성하고, 프로그램의 구조를 변경하세요.
    
    (1) /구분/기능?파라미터...
    
    (2) 동일한 이름의 파라미터는 맨마지막 하나의 value만 취급한다.
    
    (3) 유효하지 않은 URL은 예외가 발생.
    
- 해결:

```
if (!userInput.equals("종료")) {

                userInputPath = userInput.split("/",3); //1차 필터
                boolean firstCheck = false;

                try {
                    userInputCrud = userInputPath[2].split("\\\\?",2);
                    firstCheck = true;

                } catch (ArrayIndexOutOfBoundsException e) {

                }

                if (firstCheck && userInputPath.length == 3 && userInputPath[0].equals("") &&
                        !userInputPath[1].equals("") && !userInputPath[2].equals("") ){ //2차필터

                    if (userInputPath[1].equals("boards") && userInputPath[2].equals("add") ) { //3차필터 ...

```

여러 입력값의 경우를 하나씩 체크해보면서

위와같이 여러단계로 필터링하였다.

(예시:유저의 입력중에 "/"를 기준으로 3덩이 까지만 나눠서 저장하고,

나눈것 중에 "?"를 기준으로 2덩이까지만 나눠서 저장하고,

그게 충족안되면 예외가 발생하고,
형식이 충족이 되면 if문으로 들어가고,

진입 기능에 충족되면 최종진입 등등..)

split의 limit을 정해줌으로써 맨뒤에 기호가 추가되어도 진입되는 문제를 예방가능했다.

### 2-(2)

- 요구사항:
    
    게시판 기능 `boards` 를 구현하세요.
    
    `boards/add` , `boards/edit`, `boards/remove`, `boards/view`
    
- 해결:

이제부터는 입력값에 파라미터값이 추가됐기때문에,

만들어놓은 필터링의 구조를 변경했고, 더 깊은 단계를 부여했다.

이때 입력값의 경우를 하나씩 테스트해봤는데,

그러면서 원하는 URL의 공통점을 발견해서 활용했다.

공통점 =>

`&`로 split을 해놓고, 아래 만나는 for문에서

다시한번 `=` 스플릿을 하고난뒤의 결과

`userInputParameter` length의 2배가 `userInputParameterSplit.size()` 가 되었다.

이것을 활용해 원하는 입력값만 받도록 필터링하였다.

결과적으로 `remove`의 경우

`/boards/remove?boardId=1`  같은 입력을 받을수있다.  또한

`/boards/remove?boardId=1&boardId=2...&boardId=100` 이렇게 여러 파라미터가 반복되는 경우는 맨마지막 value `100`만 입력받게 하였다.

이것은 입력 형태가 같은 다른 진입점에도 적용하였다.

### 2-(3)

- 요구사항:
    
    게시물 기능을 수정하세요.
    
    `posts/add` , `posts/edit`, `posts/remove`, `posts/view`
    
- 해결:

```

```

여기서 부터는 파라미터값이 하나더 추가됐다.
그래서 전 단계보다 더 깊은 필터링 단계를 만들었다.

결과적으로 `remove` 의 경우

`/posts/remove?postId=1&boardId=1`  과 같은 형태를 입력 받을수있다.  또한

`/posts/remove?postId=1&boardId=2...&boardId=100` 이렇게 `boardId=숫자` 형태가 반복되는 경우는 맨마지막 `boardId`의 value `100`만 입력받게 하였다.

이것은 입력 형태가 같은 다른 진입점에도 적용하였다.

### 2-(4)

- 요구사항:
    
    회원 기능을 구현하세요.
    `accounts/signup` , `accounts/signin`, `accounts/signout`, `accounts/detail`, `accounts/edit`, `accounts/remove`
    
    로그인 후 게시물 혹은 게시판을 작성/생성하였을 경우 작성한 회원이 누구인지 저장되도록 수정하세요, 만일 로그인 되어있지 않은 사람이 글 혹은 게시판을 작성하였다면 비회원이라는 것을 표시하여야 합니다.
    
- 해결:

```
LinkedList<String> userAccount = new LinkedList<>();
LinkedList<String> userPassword = new LinkedList<>();
LinkedList<String> userName = new LinkedList<>();
LinkedList<String> userNickname = new LinkedList<>();
LinkedList<String> userEmail = new LinkedList<>();
LinkedList<String> boardWriter = new LinkedList<>();

LinkedList<LinkedList<String>> postWriter = new LinkedList<>();

LinkedList<LocalDate> userSignUpDay = new LinkedList<>();
LinkedList<LocalDate> userEditDay = new LinkedList<>();

```

이런식으로 회원과 관련된 데이터 저장소를 만들어 구현했다.

링크드리스트로 만들었고 인덱스라는 공통점으로 몇번 회원인지를 알수있도록 활용했다.

### 2-(5)와 2-(6)

- 요구사항:
    
    요구사항에 맞추어 프로그램을 수정하세요.
    
    (1) 요청(Request) 객체를 생성.
    
    (2) 명령어(URL)에 대한 정보를 포함한다.
    
    (3) 로그인 여부를 포함한다.
    
    (4) 회원의 인증 관련 내용을 요청 객체과 분리하여 세션(Session) 객체에서 다룰 수 있도록 한다.
    
    (5) 세션 객체는 요청 객체 안에 포함되도록 설계합니다.
    
    (6) 세션 객체 안에는 필요하다면 인증정보 외 다른 정보를 포함해도 무방합니다.
    
- 해결:

```
public class Request <T> {
    T inputUrl;

    public Request(T arg) {
        this.inputUrl = arg;
    }

}

public class Session<T> extends Request<T> {

    boolean userLogin;
    // 만약 user로그인에 성공했다면 session.userLogin == true인 상태로 프로그램(do문) 계속 실행됨.

    boolean masterLogin;

    String loginingAccount;
    // 현재 로그인중인 그 계정명. 만약 로그인에 성공했다면 해당 계정을 담아 활용하기위해 만듬.

    public Session(T arg){ //세션 생성자
        super(arg);
    }

}

```

이런식으로 요청 객체와 세션 객체를 각각 만들었다.

그리고 세션객체가 요청객체를 상속받도록하고,

세션 객체에만 회원 인증관련 정보를 담아서 분리하였다.

세션 객체 안에
`String loginingAccount;` 정보를 포함해서

현재 로그인중인 그 계정명을 활용 가능하도록 했다.

### 3-(1)

- 요구사항:
    
    (1)회원 기능에 등급을 추가.
    
    (2)컨테이너 객체를 도입.
    
    (3)필터 기능을 추가.
    
- 해결:

```

  // --- 관리자 계정 초기에 추가 ---
  userAccount.add("관리자");
  userPassword.add("관리자");
  userName.add("관리자");
  userNickname.add("관리자");
  userEmail.add("관리자@naver.com");
  userSignUpDay.add(LocalDate.now());
  userEditDay.add(null);
  // --- 관리자 계정 초기에 추가 ---

  Session<String> session = new Session<>(null);

  // 유저 로그인 여부와 정보를 담는 session 객체를 최상위에서 생성
  // 만약 user로그인에 성공했다면 session.userLogin == true인 상태로 프로그램(do문) 계속 실행됨.

```

이런식으로 최상위 스코프에 관리자 계정을 미리 추가하였다. (계정: 관리자 / 비밀번호: 관리자)

그래서 프로그램 실행과 동시에 바로 `관리자` 계정이 생성되도록 하였다.

그리고 관리자 계정으로 로그인하면,

Session 객체의 `boolean masterLogin;` 이 `true` 가 되어 `관리자 인증` 상태로 동작된다.

만약 일반 회원으로 로그인한다면 `boolean userLogin;`  이 `true` 가 되어 `일반 회원 인증` 상태로 동작된다.

session 객체를 최상위 스코프에서 생성해뒀기때문에,

로그인에 성공했다면 `인증` 이 계속 유지된 상태로 프로그램(do문)이 동작한다.

그리고 익명의 손님, 관리자, 회원의 인증 여부에 따라서,

기능 접근에 제한을 두어서 필터기능을 구현했다.

<br><br><br>

## 트러블 슈팅

### 1-(2) a

- 문제

```java
	 		Scanner sc = new Scanner(System.in);
       String userInput;

do {System.out.print("명령어 > ");
            userInput = sc.nextLine();
            System.out.println(userInput);
   } while( userInput != "종료");

```

위처럼 do while로 스캐너를 반복시켰는데 종료를 입력해도 종료되지않았다.

- 고민

do while의 작동 순서나, 변수의 업데이트때문인가 싶었다.

디버깅으로 확인해본 결과 그 문제가 아니었다.

- 모색

while이 종료되지않는다는건 값이 true라는 뜻이다.

그래서 while문 안의 값을 테스트해봤다.

```java
Scanner sc = new Scanner(System.in);

        String userInput;
        userInput = sc.nextLine(); //종료 입력

        boolean a = (userInput == "종료");
        System.out.println(a); //false

```

예상과 달리 true가 아니라 false 였다.

`userInput` 이 담은 값과 “종료” 라는 값이 다르다는 말이다.

원인은 문자열의 의 저장방식이었다.

“ ”를 직접 사용해서 만들면 문자 리터럴로 인식돼서 메소드 메모리 영역의 상수풀이라는 곳에 저장된다.

그러나 인스턴스 생성 방식으로 문자열을 만들면 힙 메모리 영역에 저장된다.

그래서 외모는 같은 문자열이지만 각각 저장되어있는 주소가 다르고, 그 주소를 담은 변수도 각각 다른 주소를 가리키고 있기때문에 다른 값으로 구별된 것이었다.

- 해결

문자열의 메모리 주소를 비교하지않고 문자열의 생김새 자체가 같은지 비교하는 방법이 있었다. String 클래스의 `equals` 메서드를 이용하는것이다.

- 후기

부분별로 why를 생각해보면 도움이 되는것같다.

### 1-(3)

- 문제

작성, 조회, 삭제, 수정의 동작을 구현해야했다. 추상적으로 느껴졌다. 즉 어떤 것을 만들어서 어떻게 구성해야하는지, 뭐부터 해야할지를 몰랐다.

- 고민1

일단 내가 이해할 수 있는 부분만큼만 해야할 것을 하나씩 쪼개봤야겠다고 생각했다.

- 모색1

글을 보니 명령어 라고 되어있었다. 작성, 조회, 삭제, 수정이라는 명령어였다. 그러면 내가 이전 단계에서 `종료` 라는 명령어 입력을 만들었던 것처럼 저것들을 추가하면된다고 생각했다.

그리고 `작성`의 동작을 보니, 글의 제목과 내용 2가지를 입력받아야한다고 했다. `조회` 가 있는 것을 보니 입력받은 것을 저장도 해야했다.

입력받는다는 것은 `작성` 을 하고 엔터를 쳤을때 똑같이 스캐너 스트림이 작동하고있으면되는거다.

대신에 저장을 제목, 내용 2가지를 붙여서 저장 해야했다.

### 해결1

어제 배운 Map 자료구조가 떠올랐다. Map 객체는 key와 value를 쌍으로 붙여서 저장해주는 객체였다.

- 고민2

요구사항의 조회, 삭제, 수정을 보니 ‘가장 마지막으로 저장된 자료’ 를 활용해야했다.

- 모색2

Map 자료구조 에서 어떻게 마지막 자료만 불러올수있는지 생각했다.

### 해결2

어제 배운 Stack 자료구조가 떠올랐다. Stack 객체는 마지막에 저장한것이 가장 빨리 나온다. 그리고 peek 메서드로 손쉽게 꺼낼수도 있다.

- 고민3

그런데 Stack에 저장하고, 곧바로 Map에다가 put 매서드를 통해 key, value 이 순서대로 적어서 넣어주고싶었다. 그러나 Stack은 거꾸로 value, key로 꺼내야해서 순서가 원하는대로가 아니라 거꾸로 나왔다. 그렇다고 위에 변수를 따로 선언해서 활용하기도 번거로웠다.

### 해결3

어제 배운 자료구조중에 Deque 가 떠올라서 이것을 이용했다. 앞 뒤로 문이 2개인데, 순서는 상관없이 활용가능한 자료구조였다.

- 고민4

`수정` 기능을 만들고 `조회`를 해보니 제목과 내용이 거꾸로 출력됐다.

### 해결4

Deque의 자료 구조 특징과 add, push의 명령어 차이 때문이었다.

```java
Deque.add("1")
Deque.add("2")
Deque.getFirst() // 1

Deque.push("1")
Deque.push("2")
Deque.getFirst() // 2

```

따라서 명령어를 add로 바꿔서 해결했다.

### 후기

흐름에 따라 하나하나씩 생각해보면 해결에 도움이 되는것같다.

### 1-(4)

- 문제

글을 저장할때마다 번호를 부여해야했다.

- 고민1

번호를 어떻게 부여해야할지 접근이 안됐다.

- 모색1

목적 문장을 하나적고 다시 구체적으로 풀어서 써봤다.

`작성할때 번호를 부여한다.`  ⇒ `작성한 key에 번호를 부여한다.` ⇒ `작성한 key가 저장될때마다 배열에 저장한다.` ⇒ `저장한 key를 검색하면 index 번호가 나오는 명령어를 찾는다.`

HashMap은 저장 순서대로 index에 저장되지않고 해싱으로 랜덤한 index에 저장되는걸로 알고있어서 활용하지못한다. 따라서 순차적인 index를 알고있는 저장공간이 필요했다.

마침 이미 저장소로 사용하고있는 Stack이 있었다.

### 해결

Map 말고 저장에 사용하던 Stack 으로 글번호가 나오도록 활용하였다.

### 후기

문제를 분리하고 구체화하면 도움된다.

### 1-(6)

- 문제1

`조회` 에서 try, catch 문으로 예외 처리를 하려고했다.

그런데 글번호를 출력하는 명령어가 예외상황이 맞든아니든 항상 출력되었다.

- 고민1

if문을 사용할순 없을까 생각했다.

- 모색1

문제가 없는것이 true 일때만 묶은 명령어들이 실행되게끔 하고싶었다.

그러려면 문제가 있는 경우가 구체적으로 어떤 경우인지 알아야한다고 생각했다.

그런 경우는 조회하려고 입력한 번호가 존재하지않을 경우였다.

그러면 null로 체크할수있지않을까.

결국 위처럼 해보려고 if문 코드를 작성하다가, 문득 글번호를 출력하는 명령어만 if문으로 묶고 오류가 아닐경우만 실행하고, 오류일 경우는 그것만 건너뛰게할 수 있다는 것을 알았다.

- 고민2

그런데 또 오류가 났다.

```java
if(keyIndexStorage.get(userReadIndex-1) != null) {
System.out.println(userReadIndex + "번 게시물");
   }
try{System.out.println("제목 :[" + keyIndexStorage.get(userReadIndex - 1) + "]");
   System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(userReadIndex - 1)) + "]");
   } catch (IndexOutOfBoundsException e) {
   System.out.println(userReadIndex + "번 게시물은 존재하지 않습니다.");
   }

```

if문의 조건문 자체에서 `IndexOutOfBoundsException` 오류가 난것이다.

### 해결

```java
 try{
      if(keyIndexStorage.get(userReadIndex-1) != null) {
       System.out.println(userReadIndex + "번 게시물");
      }
        System.out.println("제목 :[" + keyIndexStorage.get(userReadIndex - 1) + "]");
        System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(userReadIndex - 1)) + "]");
    } catch (IndexOutOfBoundsException e) {
        System.out.println(userReadIndex + "번 게시물은 존재하지 않습니다.");
    }

```

try 블록으로 if문까지 덮는다면 어떻게될지 생각해다. 그랬더니 예외를 잡아낼수있으면서도, 문제가 없을때만 글번호출력이 가능하게끔 되었다. 일석이조였다.

### 후기

원하는 기능을 최대한 나눠서 구체화하고, 하나하나씩 실험해보면 도움이된다.

- 문제2

`10000번`  처럼 존재하지 않는 번호를 입력하면 예외를 처리하도록 구현하기는했다.

그런데 문제가 있었다. 실수로 오타를 치거나 해서 `?번` 과 같이 입력이 안될때도 오류가났다.

- 고민

그래서 무조건 `?번` 같은 형식처럼만 입력할 수 밖에 없게끔 설정하고싶었다.

그런데 그럴수있는 방법이 떠오르지않았다.

- 모색

맨처음엔

```java
int a;
if(userInput.equals(a+"번"));

```

이런식으로 일단 코딩을 해봤는데, 당연히 불가능했다.

나는 a를 어떤 숫자든 들어갈 수 있는 미지수a로 만들었으면 좋겠다는 마음에 저렇게 적어봤는데,

내 생각만 그런것이었고 a값을 할당하지 않았기에 불가능한 문법이었다.

그러다가 문득 for문이 떠올랐다.

`(a+"번")` 이라는 형태는 고정시키되, 그 a에 들어가는 숫자 부분을

실제 존재하는지 처음부터 끝까지 반복시켜서 검사하면 되지않을까라는 생각이 들었다.

### 해결

for문 이라는 방법이 있다는 것을 알고나니까 여러가지 실험을 통해 해결했다.

for문을 활용하면 “?번” 이라는 형식으로만 입력돼야 정상 작동할 수 있도록 할수가 있었다.

- 문제3

정상 작동이 아닐 경우에만 if문에 진입시키고 싶었다.

- 고민

여러 경우의 수가 있어서 모두 충족하는 조건문을 찾기가 힘들었다.

- 모색

O인 경우, O가 아닌경우,

X인 경우, X가 아닌경우  등등 많은 것을 조건문으로 설정해봤다.

그러나 그것을 전부 해봐 벗어나는 경우가 존재했다.

### 해결

O인 경우에만 실행되는 if문을 쳐다보다가, 그곳 안에 boolean 을 넣고 true로 설정해주면 어떨까라는 생각이 들었다.

그래서 시도해본결과 true인 경우와 false 인 경우 2가지 경우로만 명확히 구분되어서 복잡한 조건문을 설정해줄 필요도 없이 잘 해결되었다.

### 후기

원하던 것처럼 해결되어서 기뻤다. 내가 공부했고, 알고있는 개념이 많을수록 그것을 떠올릴수가있고 떠오르면 활용을 시도해볼수있고 시도해보면 성공하므로 뭔가를 많이 알아두는것이 도움이 될것같다.

### 2-(1)

- 문제

URL을 분석할 수 있는 기능을 구현해야했다. `/구분/기능?파라미터...`  로 입력받아야했다. 그런데 그게 어떤 의미인지 뭐부터 구현해야하는지 잘 모르겠었다.

- 모색

저번에도 뭐부터 해야할지 잘 모르겠을때, 필요한것을 작게 구체화해보고 실험해보면서 해결이 됐었다.

이번에도 뭐가 필요한지 구체화해봤다.

일단 유저가 `/abc/abc?abc` 이런 형식으로만 입력하게끔 만들어야겠다고 생각했다.

### 해결

split을 반복해서 이용함으로써 원하는 형식대로 유저가 입력하도록 할수있었다.

### 2-(2)

- 문제

요구사항을 다시 천천히 읽어보니까 1단계처럼 게시글을 다루는게 아니라 아니라 게시판을 다루는 것이었다.

- 모색

게시판이 무엇인가 생각해봤다. 이전에 만들어놓은 key와 map 저장소 자체가 게시판이었다.

왜냐하면 거기서 유저가 원하는 몇번째의, 어떤 제목, 어떤 내용들을 가져왔기때문이다.

그래서 그런 저장소 역할을 하는 key와 map 객체를 새로 만들면 그게 새 게시판이 되는것 아닐까라는 생각이들었다.

### 해결

```java
        Map<String, Map<String, String>> mapStorage = new HashMap<>();
        LinkedList<LinkedList<String>> mapKeyStorage = new LinkedList<>();
        LinkedList<String> boardKeyStorage = new LinkedList<>();

```

이렇게 저장소들을 만들어서 해결하였다. 아래는 이렇게 만든 이유를 적었다.

이유(1)⇒

게시'글'을 저장해두는 게시'판'들의 저장소인 `mapStorage` 를 만들었다. 이렇게 한 이유는 다음과 같다.

Map 은 key와 value를 저장가능했다. 게시판 하나가있다면 제목(key),내용(value)으로 사용할수있다. 그러나 게시판 자체가 여러개면 이러한 Map이 여러개있어야했다.

그래서 처음엔

```java
Map<String, String> 게시판1 = new HashMap<>();
Map<String, String> 게시판2 = new HashMap<>();

```

이런식으로 main함수에 선언했다. 그러나 문제에 봉착했다.

미리 이렇게 변수를 여러개 선언해두자니, user의 필요에따라 훨씬  많이 만들어놔야하는 문제에 봉착했다.

그렇다고 생성 시에 블록 {  } 안에서만 그 변수를 선언하면 반복문이 한번 끝났을때 생성해둔 게시판이 메모리 문제로 사라져버렸다.

그래서 고민하다가 문득 Map 구조의 value 부분에, Map 자체를 넣으면 어떨까싶었다.

그러면 바깥 Map에 key는 게시판의 제목이고 value는 해당 게시판이 될것같았다.

그리고 해당 Map 내부 value에 접근하면 해당 게시판 속에 들어간것이고, 그때부턴 key가 게시글 제목이 되고, value가 게시글 내용이 될까싶었다.

그리고 프로그램 종료전까지는 새로 생성해둔 게시'판'을 항상 사용하고 싶은 생각에 최상위인 main 함수에 맵 자체를 저장해야한다고 생각했다.

그래서 저런 형식의  `mapStorage` 를 만들어서 main함수에 선언했다.

이유(2)⇒

만들어진 게시판(Map)의 게시글 제목들을 저장할 공간인 `mapKeyStorage` 를 만들었다.

물론 `mapStorage` 에서도 해당 게시판의 게시글 제목들을 저장할 수 있지만,

제목을 인덱스 순서와 함께 같이 저장하고 싶어서 LinkedList 구조로 만들었다.

이유(3)⇒

게시판 제목(게시글 제목X) 을 순서와 함께 저장할 공간인 `boardKeyStorage` 를 만들었다.

예를들어 게시판 삭제를 MapStorage에서 해야하는데, 해당 게시판과 연결된 Key를 알아야했다. 그래서 만들었다.

### 2-(3)

- **문제1**

| URL | 파라미터 | 동작 |
| --- | --- | --- |
| /posts/add | boardId | boardId 게시판에 게시글을 작성합니다. 제목과 내용을 입력받으며, 작성된 시점이 저장됩니다. |
| /posts/remove | postId | postId 에 해당하는 게시글을 삭제합니다. |
| /posts/edit | postId | postId 에 해당하는 게시글을 수정합니다. 수정시 제목과 내용 모두 수정하며, 수정된 시점이 저장됩니다. |
| /posts/view | postId | postId 에 해당하는 게시글을 조회하며 다음과 같이 출력됩니다. |

[postId]번 게시글
작성일 : …

수정일 : …

제목 : …

내용 : …

위처럼 구현해햐했는데 고민이 들었다.

받는 입력을 예를들면 `/posts/edit?postId=1` 이런 식으로 입력을 받아야한다고 이해했다.

그리고 그 기능은 `1` 에 해당하는 게시글을 수정하는 것이었다. 즉 게시글의 제목과 내용, 시점을 수정하는 것이었다.

그런데 그 게시글은 어떤 게시판의 글이어야할지가 의문이었다.

즉 게시판이 먼저 정해지고나서야, 그 게시판 속의 게시물을 수정할 수 있다고 생각했는데

`/posts/edit?postId=1`  이라는 입력값으로만 입력을 받아야하는 상태라면, 그 게시판이 어떤 게시판인지 정해질 시점이 없었다. 이것이 고민이었다.

- **모색1**

**첫번째 방법**

그래서 생각한 첫번째 방법은  `/boards/edit?boardId=1` 로 게시판 수정으로 먼저 진입을 성공해야만 `/posts/edit?postId=1` 를 입력할 수 있게끔 하는것이었다.

즉 어떤 게시글을 수정하려면 게시판 수정 기능을 먼저 진입해야만 가능하도록 하는것이다.

이러면 이미 어떤 게시판인지도 정해진 상태이기때문에 괜찮을거라 생각했다.

그런데 왠지 그렇게 하는것이 아닐것같았다.

2단계-(1)의 요구사항에보면 파라미터 규칙이 실제 URL 규칙을 따른다고하였기 때문이다.

내가 알고있는 파라미터 규칙은 `parameterA=10&parameterB=value` 이렇게 &로 여러가지 기능을 처리할 수 있는것이었다.

그리고 첫번째 방법처럼 하면 항상 어떤 게시글을 수정하고싶으면 `/boards/edit?boardId=1` 를 먼저 입력하고나서야 가능한거라 번거로울것같았다.

그래서 다른 요구사항들을 보면서 고민하고있다가 우연히 아래의 표를 보게됐다.

| URL | 인증 여부 | 인가 범위 |
| --- | --- | --- |
| /accounts/signin | X | 익명 |
| /accounts/signup | X | 익명 |
| /boards/add | O | 관리자 |
| /boards/edit | O | 관리자 |
| /boards/remove | O | 관리자 |
| /posts/add | O | 회원 |
| /posts/edit | O | 회원 |
| /posts/remove | O | 회원 |
| /accounts/remove | O | 회원 |
| /accounts/edit | O | 회원 |
| /accounts/signout | O | 회원 |
| 그외 | - | - |

이것은 3단계에 있는 표였다.  여길 보면 `/boards/edit` 는 관리자만 가능하게 만들었고,

`/posts/edit` 는 회원만 가능하게 만들었다.

그러면 내가 생각한 첫번째 방법대로 구현하면 안되는것이었다.

게시글 수정 등을 하기위해서 항상 /boards/edit 로 먼저 진입하도록 구현해놨다면

위 요구사항 단계에서 회원은 항상 게시글에는 접근을 못하기때문이다.

### 해결1

위에서 그러한 힌트를 얻어서  `/posts/edit?postId=1&boardId=1` 과 같이 파라미터 &를 이용해 해당게시판을 같이 입력받아야겠다고 생각했다.

### **문제2**

수정된 작성일을 인덱스와 연결해서 저장하고싶은데 방법이 떠오르지않았다.

- **모색2**

원래 작성일을 저장해둔 링크드리스트를 똑같이 만들면 가능했지만 그러면 수정하지 않았을때도 메모리가 많이 사용되기때문에 다른 방법을 찾아보았다.

### 해결2

수정일을 게시글 인덱스와 함께 저장만 하면 된다는것을 생각해보았다.

그러다가 Map이 떠올랐고 key에 인덱스자체를 저장하고 value에 수정일을 저장해두면,

인덱스와 수정일이 연결될것같았다.
