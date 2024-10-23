



import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

//241023_16:42~ 2단계3시작. posts시작.
public class qwer2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;
        String[] userInputPath;// userInput을 split 한 경로 문자열 저장용.
        String[] userInputCrud; // split한 경로 문자열에서 파라미터부분을 따로 또 분리해서 그것을 저장할 용도.
        String[] userInputParameter;
        String[] userInputParameterSplit = {"a"}; // 나중에 if조건문 활용하려하는데 초기화가 안되어있다는 오류가자꾸떠서 적어주었다.

       // Map<String, String> boardStorage = new HashMap<>(); //같은 제목이면 Map에서 덮어씌워지는 문제는 일단 나중에생각하자.
        //LinkedList<String> boardId = new LinkedList<>(); // LinkgedList로 한 이유.
        // 원랜 Deque 로 만들려했는데, Deque는 인덱스번호만으로 값을가져오는 메서드가 없었음. key값을 직접 입력해야했음.





        Map<String, Map<String, String>> mapStorage = new HashMap<>();// 게시'글'을 저장해두는 게시'판'들의 저장소다. 이렇게 한 이유=>
        // 고민해보다가 문득 맵속에 맵을 저장하면 게시판의 저장소가 될것같았다. 그리고 메모리 문제로 사라지지않게 최상위에 저장하고 싶었다.
        // 그리고 그 게시판의 이름도 정해야하는것같아서 이름(key)을 붙일수있는 Map 자료구조로 이렇게 만들었다.
        LinkedList<LinkedList<String>> mapKeyStorage = new LinkedList<>(); // 만들어진 게시판(Map)의 게시글제목들을 저장할 공간. 인덱스로 몇번째 생성게시판인지 알수있다.
        LinkedList<String> boardKeyStorage = new LinkedList<>(); // 예를들어 MapStorage에서 게시판을 삭제할때, Key를 알아야하는데 그 key를 저장할 공간.
        //그리고 이것과 mapKeyStorage도 인덱스를 통해 연결가능하다.
        LinkedList<LinkedList<LocalDate>> originalLocalDate = new LinkedList<>();





        do {
            System.out.print("손님 ");
            userInput = sc.nextLine();
            System.out.println();
            userInputPath = null;
            userInputCrud = null;
            userInputParameter = null;
            userInputParameterSplit = null;
            // 기능이 한번끝나서 다시 다음에 명령어에 재사용하기위해 null로 안의 값들을 삭제.

            if (!userInput.equals("종료")) {

                userInputPath = userInput.split("/"); // 유저의 입력중에 "/"를 기준으로 나눠서 저장함.
                // 예시 입력값=> /boards/add?parameter=10&b=value...
                // userInputPath[0] => 빈 부분
                // userInputPath[1] => boards 부분
                // userInputPath[2] => add?parameter=10&b=value... 부분

                userInputCrud = userInputPath[2].split("\\?"); // 첫 입력에서 정상적으로 입력되었다면 3번째배열을 바탕으로 "?"를 기준으로 나눈다.
                // 아마 2번인덱스(3번째배열)에는 add?parameter=10&b=value... 이런식으로 저장되어있을것이다. 따라서 그것을 한번더 ? 를 기준으로 나눠서 userInputCrud에 담는다.
                // userInputCrud[0] => add 부분
                // userInputCrud[1] => parameter=10&b=value... 부분





                if (userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                        userInputPath[2].equals("add") && userInputPath.length == 3) { //   게시판 작성. 입력이 /boards/add   일경우에만 진입할것이다.

                    System.out.print("게시판 이름을 입력해주세요 :");
                    userInput = sc.nextLine();

                    mapKeyStorage.add(new LinkedList<>()); // 이것으로 방금 만든 게시판이 몇번째 게시판인지 그 인덱스를 활용해 알수있음.
                    mapStorage.put(userInput, new HashMap<>()); // 게시판 저장소용 Map 에다가, 방금만든 게시판 이름을 key로 잡고 그 key에 붙는 새로운 게시판을 생성함.
                    //이제 해당 key(게시판)으로 접근하면, 해당 게시판만의 게시물들을 String타입으로 제목(key)과 내용(vlaue)을 저장할수있음.
                    boardKeyStorage.add(userInput); // 추가한 Map의 키를 저장.
                    originalLocalDate.add(new LinkedList<>()); // 작성일 저장소에 인스턴스를 추가하고 인스턴스 인덱스를통해 몇번째게시판인지 알수있음. 또 인스턴스안에 링크드리스트로는 작성일과
                    //게시글 번호랑 연결되어서 그 게시글과 작성일을 연결할수있다.

                    System.out.println();
                    System.out.println("게시판이 성공적으로 저장되었습니다.");




                } else if ( userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                        userInputCrud[0].equals("edit") && userInputCrud.length == 2 ) {    //   게시판 수정 진입시도.
                    //   지금까지 테스트해본결과로는 /boards/edit?abc 여기까지는 확정되어야 진입가능하다. 물론 /boards/edit?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                    //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                    userInputParameter = userInputCrud[1].split("&"); // 첫 "?"를 기준으로 나눈 그 뒤의 전부를 "&"로 나눈걸 변수에 담는다.
                    // 우선 userInputParameter.length() 는 최소 1이상 일수밖에없다. 앞의 else if 조건때문이다.
                    // 예를들면 userInputCrud[1]은 => parameter=10&b=value... 부분이다.
                    // 예시 입력값 => /boards/edit?abc 이라고치자.
                    // 아래는 userInputCrud[1]이 어떤값일때, 그걸 "&"기준으로 split한 userInputParameter 가 어떨까를 경우의 수를 한번 분석해본 것이다.

                    // ?abc 였다면 userInputParameter의 length 가 1. 이때 userInputParameter[0] 은 => abc 이고 [1]은 없음.
                    // 그러면 밑의 for문에서 userInputParameterSplit[0] 은 => abc 이고, [1]은 없음.
                    //분석: userInputParameter 렝스는1, Split렝스는 1.

                    // ?abc= 였다면 length가 1. 이때 userInputParameter[0] 은 => abc= 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc 이고, [1]은 없음
                    // 분석: userInputParameter 렝스는1, Split렝스는 1.

                    // ?abc== 였다면 length가 1. 이때 userInputParameter[0] 은 => abc== 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc 이고, [1]은 없음
                    // 분석: userInputParameter 렝스는1, Split렝스는 1.

                    // ?abc==abc 였다면 length가 1. 이때 userInputParameter[0] 은 => abc==abc 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc, [1]은 "", [2]는 abc
                    // 분석: userInputParameter 렝스는1, Split렝스는 3.

                    // ?aaa&bbb=bbb 였다면 length가 2. 이때 userInputParameter[0]은 aaa [1] bbb=bbb 이다. userInputParameterSplit[0] 은 aaa [1]은 bbb [2]는 bbb 이다.
                    // 분석: userInputParameter 렝스는2, Split렝스는 3.

                    // ?aaa=aaa&bbb 였다면 length가 2. 이때 userInputParameter[0]은 aaa=aaa [1] bbb 이다. userInputParameterSplit[0]은 aaa [1]은 aaa [2]는 bbb 이다.
                    // 분석: userInputParameter 렝스는2, Split렝스는 3.

                    // ?aaa=aaa&bbb&ccc 였다면 length가 3. 이때 userInputParameter[0]은 aaa=aaa [1] bbb [2] ccc 이다. userInputParameterSplit[0]은 aaa [1]은 aaa [2]는 bbb [3]ccc 이다.
                    // 분석: userInputParameter 렝스는3, Split렝스는 4.

                    /////바라는 입력이다. ?aaa=aaa 였다면 length가 1. => 이때 userInputParameter[0]은 aaa=aaa이고, [1]은 없음. userInputParameterSplit[0] 은 =>aaa, [1]은 aaa.
                    //분석: userInputParameter 렝스는1, Split렝스는 2.
                    // 그러면 &스플릿하고 for = 하고난뒤에 userInputParameter.length==1 && userInputParameterSplit.length==2 면 통과.

                    //////바라는 입력이다. ?aaa=aaa&bbb=bbb 였다면 length가 2. => 이경우 userInputParameter[0]은 aaa=aaa이고, [1]은 bbb=bbb.
                    ////// 위의 userInputParameterSplit[0] 은 aaa [1] aaa [2] bbb [3] bbb.
                    //분석: userInputParameter 렝스는2, Split렝스는 4.
                    // 그러면 &스플릿하고 for = 하고난뒤에 userInputParameter.length==2 && userInputParameterSplit.length==4 면 통과.

                    //////바라는 입력이다. ?aaa=aaa&bbb=bbb&ccc=ccc 였다면 length가 3. => 이경우 userInputParameter[0]은 aaa=aaa이고, [1]은 bbb=bbb. [2]는 ccc=ccc.
                    ////// 위의 userInputParameterSplit[0] 은 aaa [1] aaa [2] bbb [3] bbb. [4] ccc [5] ccc.
                    //분석: userInputParameter 렝스는3, Split렝스는 6.
                    // 그러면 &스플릿하고 for = 하고난뒤에 userInputParameter.length==3 && userInputParameterSplit.length==6 면 통과.

                    // 경우의 수를 하나씩 실험해본결과 원하는 입력들은 공통점이 있었다.
                    // 공통점 => & 스플릿을하고 아래 for문 = 스플릿을 하고난뒤의 결과가 userInputParameter 렝스의 2배가 userInputParameterSplit 렝스가 되었다.

                        for (int i = 0; i < userInputParameter.length; i++) { //일단 못잡은 예외는 나중에 생각해보자.  예시입력값=> /boards/edit?parameter=10&b=value...
                            //userInputParameter.length() 는 최소 1이상 일수밖에없다. 위 게시판 수정 진입 시점인 else if 조건때문이다.
                            //따라서 for에 도달하면 무조건 한번은 실행된다.
                           
                            userInputParameterSplit = userInputParameter[i].split("=");
                            //userInputParameterSplit[0] => parameter 부분
                            //userInputParameterSplit[1] => 10 부분
                            //userInputParameterSplit[2] => b 부분
                            //userInputParameterSplit[3] => value 부분 ...
                        } // 어떤 값이 왔더라도 userInputParameterSplit length는 무조건 1이상 이다.

                            //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                            if ( (userInputParameter.length)*2 == (userInputParameterSplit.length) ) { //여기 진입하는것은 /board/edit?aaa=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                                //그리고 이 시점부턴 userInputParameterSplit 변수에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                                //원하는것: /board/edit?boardId=게시판번호   => 를 입력받으면 해당 게시판 수정모드로 진입하게끔하고싶은것이다. 따라서 파라미터이름에는 boardId만 오게하고싶음.
                                // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수배열에 저장됨.([0]을 포함)  ex) [0],[2],[4] ....
                                // 그러면 그 짝수값은 전부 boardId 여야함.

                                LinkedList<String> parameterNames = new LinkedList<>();

                                for (int i = 0; i < userInputParameterSplit.length; i = +2) { //파라미터 이름이 저장되는 parameterNames 에서 짝수만 뽑아 boardId인지 확인한다.
                                    // 이 위 조건문들에따라 userInputParameterSplit.length 는 무조건 2이상이다.

                                    String parameterName;
                                    parameterName = userInputParameterSplit[i]; // userInputParameterSplit[i] 를 String[] 변수에 바로 집어넣으려하니 안돼서 이렇게 나눠서 저장해보았음.
                                    parameterNames.add(parameterName);

                                } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                                boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                                boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                                for (int i=0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                    if (!(parameterNames.get(i).equals("boardId"))) {
                                        okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                        //만약 전부 "boardId" 가 맞다면 true.
                                        break;
                                    }
                                }

                                if (okCheck1) { // 유저URL 입력이 =>   /boards/edit?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                    String userBoardIdValueString;
                                    Integer userBoardIdValueInteger;

                                    userBoardIdValueString = userInputParameterSplit[(userInputParameterSplit.length) - 1];
                                    // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                    try {
                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                        okCheck2 = true;
                                    } //오류안나면 true
                                    // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                    // 우선유저가  /boards/edit?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                    // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판 수정모드로 진입할수있는것이다.
                                    catch (NumberFormatException e) {
                                        System.out.println("수정할 게시판 번호를 입력해주세요.");
                                    }

                                    if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                        if ( userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size() ) { // 드디어 진입. /boards/edit?boardId=1 처럼과같음.

                                            // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                            // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                            // 그럼 이 공간에서 드디어 해당 게시판을 수정할 수 있도록 진입된다.

                                            // 그러면 이제 해당 게시판의 이름을 수정하도록해야겠다. 실험중.
                                            System.out.println();
                                            System.out.print("바꿀 게시판 이름을 입력해주세요 :");
                                            System.out.println();
                                            String afterTitle; // userInput 변수를 안쓰고 새로 name으로 선언해준이유는 만약 "종료" 로 게시판이름을 적으면 반복문이끝나고 프로그램이 종료될까봐.
                                            String beforeTitle = boardKeyStorage.get(userBoardIdValueInteger-1); // 수정전 게시판 제목(key)
                                            Map<String, String> beforeMapValue = mapStorage.get(beforeTitle);  // 수정전 게시판의 value. 즉 수정전 게시판의 게시글 제목과 내용 모음들.
                                            // 그래서 이때 beforeMapValue 는 수정전의 그 게시판 인스턴스의 메모리 주소를 담고있다.

                                            afterTitle = sc.nextLine();

                                            // boardKeyStorage.remove(userBoardIdValueInteger-1);
                                            mapStorage.put(afterTitle, mapStorage.get(beforeTitle)); // 기존의 게시판 인스턴스 메모리주소를 그대로 복사해서 가져왔기때문에 게시판값이 그대로다.
                                            mapStorage.remove(beforeTitle); // 맵의 맵에서 기존 key(게시판제목) 를 가진 노드를 삭제. 실험결과 이렇게해도 afterTitle의 밸류는 그대로있음.
                                            boardKeyStorage.remove(userBoardIdValueInteger-1); // 게시판 제목 보관함에서도 기존것 삭제.
                                            boardKeyStorage.add(userBoardIdValueInteger-1,afterTitle); // 삭제한 그 인덱스 자리에 새로운 게시판 제목 추가.

                                            System.out.println("게시판 이름이 [" + afterTitle  + "] 로 변경되었습니다!");

                                        }

                                    }

                                }

                            }


                } else if(userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                        userInputCrud[0].equals("remove") && userInputCrud.length == 2 ){  //게시판 remove 진입시도 => /boards/remove?abc..

                    //   /boards/remove?abc.. 같은 입력이면 통과 됐을거임.
                    //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거고 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                    userInputParameter = userInputCrud[1].split("&");
                    // userInputCrud[1] => parameter=10&b=value... 부분

                    for (int i = 0; i < userInputParameter.length; i++) { //일단 못잡은 예외는 나중에 생각해보자. //  /boards/remove?parameter=10&b=value...
                        userInputParameterSplit = userInputParameter[i].split("=");
                        //userInputParameterSplit[0] => parameter 부분
                        //userInputParameterSplit[1] => 10 부분
                        //userInputParameterSplit[2] => b 부분
                        //userInputParameterSplit[3] => value 부분 ...
                    }

                    if ( (userInputParameter.length)*2 == (userInputParameterSplit.length) ) { //여기 진입하는것은 /board/remove?aaa=aaa.. 이런식으로 입력 받은것들이다.
                        //그리고 Split에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                        // 원하는것: /board/remove?boardId=게시판번호 또는 게시판이름   => 을 입력받으면 해당 게시판 삭제모드로 진입하게끔하고싶은것.
                        // 따라서 파라미터이름에는 boardId만 오게하고싶음.
                        // 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값은 split의 [0]을 포함한 짝수값에 저장됨. ex) [0],[2],[4] ..등등
                        // 그리고 그 짝수값은 전부 boardId 여야함.

                        LinkedList<String> parameterNames = new LinkedList<>();

                        for (int i = 0; i < userInputParameterSplit.length; i = +2) {
                            //파라미터이름이 저장되는 장소인 짝수만 뽑아서 검사해서 boardId인지 확인하고싶음.

                            String parameterName;
                            parameterName = userInputParameterSplit[i]; // userInputParameterSplit[i] 를 String[] 변수에 바로 집어넣으려하니 안돼서 이렇게 나눠서 저장해보았음.
                            parameterNames.add(parameterName);

                        } // Split의 파라미터이름을 다 뽑아서 전부 parameterNames 라는 링크드리스트에 저장끝.

                        boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                        boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                        for (int i=0; i < parameterNames.size(); i++) {

                            if (!(parameterNames.get(i).equals("boardId"))) {
                                okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                //만약 전부 "boardId" 가 맞다면 true.
                                break;
                            }
                        }

                        if (okCheck1) { // 유저URL 입력이 =>   /boards/remove?boardId=aaa&boardId=bbb ... 와 같은 입력만 여기에 진입함.

                            String userBoardIdValueString;
                            Integer userBoardIdValueInteger;

                            userBoardIdValueString = userInputParameterSplit[(userInputParameterSplit.length) - 1];
                            // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                            try {
                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                okCheck2 = true;
                            } //오류안나면 true
                            // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                            // 우선유저가  /boards/remove?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                            // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판 삭제모드로 진입할수있는것이다.
                            catch (NumberFormatException e) {
                                System.out.println("삭제할 게시판 번호를 입력해주세요.");
                            }

                            if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                if ( userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size() ) { // 드디어 진입. /boards/remove?boardId=1 처럼입력한값이 들어옴.

                                    // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                    // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                    // boardKeyStorage 도 게시판 생성마다 1씩늘어난다.
                                    // 그럼 이 공간에서 드디어 해당 게시판을 삭제할 수 있도록 진입된다.

                                    //boardId의 게시판 삭제진입 성공.

                                    String removeKey = boardKeyStorage.get(userBoardIdValueInteger-1);
                                    mapStorage.remove(removeKey); // 해당 게시판의 제목과 내용을 묶어서 저장해놓은 공간을 삭제.
                                    mapKeyStorage.remove(userBoardIdValueInteger-1); // 해당게시판의 게시글 제목들을 순서대로 저장한 공간을 삭제.
                                    originalLocalDate.remove(userBoardIdValueInteger-1); // 작성일 보관소에도 해당게시판 저장소를 삭제.
                                    boardKeyStorage.remove(userBoardIdValueInteger-1);
                                    //마지막으로, 게시판 생성 순서와 해당 게시판의 제목을 이어서 저장해둔 공간(게시판 순서와, 맵의 키를 이어붙일 용도의 공간)에서 해당 게시판 제목을 삭제.

                                    System.out.printf("%d번 게시판을 삭제했습니다!", userBoardIdValueInteger);
                                    System.out.println();

                                }
                            }

                        }

                    }


                } else if(userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                        userInputCrud[0].equals("view") && userInputCrud.length == 2 ){ //게시판 뷰 진입시도 /boards/view?abc..

                    //   /boards/view?abc.. 같은 입력이면 통과 됐을거임.
                    //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거고 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                    userInputParameter = userInputCrud[1].split("&");
                    // userInputCrud[1] => parameter=10&b=value... 부분

                    for (int i = 0; i < userInputParameter.length; i++) { //일단 못잡은 예외는 나중에 생각해보자. //  /boards/view?parameter=10&b=value...
                        userInputParameterSplit = userInputParameter[i].split("=");
                        //userInputParameterSplit[0] => parameter 부분
                        //userInputParameterSplit[1] => 10 부분
                        //userInputParameterSplit[2] => b 부분
                        //userInputParameterSplit[3] => value 부분 ...
                    }

                    if ( (userInputParameter.length)*2 == (userInputParameterSplit.length) ) { //여기 진입하는것은 /board/view?aaa=aaa.. 이런식으로 입력 받은것들이다.
                        //그리고 Split에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                        // 원하는것: /board/view?boardName=게시판번호 또는 게시판이름   => 을 입력받으면 해당 게시판 뷰모드로 진입하게끔하고싶은것.
                        // 따라서 파라미터이름에는 boardName만 오게하고싶음.
                        // 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값은 split의 [0]을 포함한 짝수값에 저장됨. ex) [0],[2],[4] ..등등
                        // 그리고 그 짝수값은 전부  boardName 여야함.

                        LinkedList<String> parameterNames = new LinkedList<>();

                        for (int i = 0; i < userInputParameterSplit.length; i = +2) {
                            //파라미터이름이 저장되는 장소인 짝수만 뽑아서 검사해서  boardName인지 확인하고싶음.

                            String parameterName;
                            parameterName = userInputParameterSplit[i]; // userInputParameterSplit[i] 를 String[] 변수에 바로 집어넣으려하니 안돼서 이렇게 나눠서 저장해보았음.
                            parameterNames.add(parameterName);

                        } // Split의 파라미터이름을 다 뽑아서 전부 parameterNames 라는 링크드리스트에 저장끝.

                        boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardName" 인지 확인하기위해서 만듬.

                        for (int i=0; i < parameterNames.size(); i++) {

                            if (!(parameterNames.get(i).equals("boardName"))) {
                                okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardName" 이 아니라면 false.
                                //만약 전부 "boardName" 이 맞다면 true.
                                break;
                            }
                        }

                        if (okCheck1) { // 유저URL 입력이 =>   /boards/view?boardName=aaa&boardName=bbb ... 와 같은 입력만 여기에 진입함.

                            String userBoardIdValueString;
                            // Integer userBoardIdValueInteger; // boardName 은 숫자가 아니라 String 값으로 받을거고 굳이 이 변수를 위에서처럼 사용안할거니까 주석처리.

                            userBoardIdValueString = userInputParameterSplit[(userInputParameterSplit.length) - 1];
                            // 이렇게하면 맨마지막 boardName의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                            // 우선유저가  /boards/view?boardName=자유게시판  이런식으로 게시판 생성할때 정한 key를 입력하길원함.
                            // 그럼 이제 userBoardIdValueString 는 뭐냐면, 게시판의 이름(=제목=key)인것이고, 존재한다면 해당 게시판 뷰모드로 진입하게끔 한다.

                            boolean okCheck2 = false;
                            int boardKeyIndex = 0; // 밑의 for문에서 boardKeyStorage 에서의 어떤 저장된 키 값이 몇번인덱스인지 알고싶었는데 get메서드 인자 타입이 index밖에없어서 어떻게 할까하다가
                            // for문의 해당 i값을 여기 저장해서 이 변수를 활용하면어떨까 생각이들었다. 0은 초기화안됐다고 오류날까봐 일단 미리 초기화해둔것.

                            for(int i=0; i < boardKeyStorage.size(); i++) { //오류는 구현하고 나중에 체크하자.넘복잡.

                                if( boardKeyStorage.get(i).equals(userBoardIdValueString) ){
                                    okCheck2 = true; // boardKeyStorage 안의 키값들 중에 입력한 게시판 value 키값이 하나라도 실존한다면 true.
                                    boardKeyIndex = i; // 실존하는 해당 키 자리의 인덱스번호인 i를 이 변수에 저장. 이 i는 게시판의 생성 순서임. i를 MapKeyStorage 인덱스와 연결하면 그곳의 자료가 곧 그 게시판의 게시글들임.
                                    break;
                                }
                            }


                            if(okCheck2) { // 드디어 진입. /boards/view?boardName=...&boardName=자유게시판.. 처럼 입력했고, 입력한 그 마지막 게시판 키가 실존해야 진입가능.

                                int writeNumber = mapKeyStorage.get(boardKeyIndex).size(); // 이러면 이 변수에 해당게시판의 게시물 글 수가 담김.

                                for(int i=0; i<writeNumber; i++) { //해당 게시판의 글 수 만큼 실행하겠다. 이때 boardKeyIndex 는 해당 게시판의 인덱스번호(게시판 생성순서)임.

                                    System.out.print((i + 1) + "번글 / ");
                                    System.out.print( mapKeyStorage.get(boardKeyIndex).get(i) + " / "); // 해당 게시판의 게시글중에 0번째(첫번째) 글제목부터 출력.
                                    System.out.print( originalLocalDate.get(boardKeyIndex).get(i) ); // 해당 게시판의 0번째 게시글부터의 작성일부터 출력.
                                    System.out.println();
                                }

                            }

                        }

                    }

                } else if(userInputPath[0].equals("") && userInputPath[1].equals("posts") &&
                        userInputCrud[0].equals("add") && userInputCrud.length == 2) {

                    //   해당 게시판의 게시글작성 진입시도.
                    //   지금까지 테스트해본결과로는 /posts/add?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/add?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                    //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                    userInputParameter = userInputCrud[1].split("&"); // 첫 "?"를 기준으로 나눈 그 뒤의 전부를 "&"로 나눈걸 변수에 담는다.
                    // 우선 userInputParameter.length() 는 최소 1이상 일수밖에없다. 앞의 else if 조건때문이다.
                    // 예를들면 userInputCrud[1]은 => parameter=10&b=value... 부분이다.
                    // 예시 입력값 => /posts/add?abc 이라고치자.

                    // 경우의 수를 하나씩 실험해본결과 원하는 입력들은 공통점이 있었다.
                    // 공통점 => & 스플릿을하고 아래 for문 = 스플릿을 하고난뒤의 결과가 userInputParameter 렝스의 2배가 userInputParameterSplit 렝스가 되었다.

                    for (int i = 0; i < userInputParameter.length; i++) {
                        //userInputParameter.length() 는 최소 1이상 일수밖에없다. 위 게시판 수정 진입 시점인 else if 조건때문이다.
                        //따라서 for에 도달하면 무조건 한번은 실행된다.

                        userInputParameterSplit = userInputParameter[i].split("=");
                        //userInputParameterSplit[0] => parameter 부분
                        //userInputParameterSplit[1] => 10 부분
                        //userInputParameterSplit[2] => b 부분
                        //userInputParameterSplit[3] => value 부분 ...
                    } // 어떤 값이 왔더라도 userInputParameterSplit length는 무조건 1이상 이다.

                    //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                    if ( (userInputParameter.length)*2 == (userInputParameterSplit.length) ) { //여기 진입하는것은 /posts/add?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                        //그리고 이 시점부턴 userInputParameterSplit 변수에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                        //원하는것: /posts/add?boardId=게시판번호   => 를 입력받으면 해당 게시판에서 게시글 작성모드로 진입하게끔하고싶은것이다. 따라서 파라미터이름에는 boardId만 오게하고싶음.
                        // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수배열에 저장됨.([0]을 포함)  ex) [0],[2],[4] ....
                        // 그러면 그 짝수값은 전부 boardId 여야함.

                        LinkedList<String> parameterNames = new LinkedList<>();

                        for (int i = 0; i < userInputParameterSplit.length; i = +2) { //파라미터 이름이 저장되는 parameterNames 에서 짝수만 뽑아 boardId인지 확인한다.
                            // 이 위 조건문들에따라 userInputParameterSplit.length 는 무조건 2이상이다.

                            String parameterName;
                            parameterName = userInputParameterSplit[i]; // userInputParameterSplit[i] 를 String[] 변수에 바로 집어넣으려하니 안돼서 이렇게 나눠서 저장해보았음.
                            parameterNames.add(parameterName);

                        } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                        boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                        boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                        for (int i=0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                            if (!(parameterNames.get(i).equals("boardId"))) {
                                okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                //만약 전부 "boardId" 가 맞다면 true.
                                break;
                            }
                        }

                        if (okCheck1) { // 유저URL 입력이 =>   /posts/add?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                            String userBoardIdValueString;
                            Integer userBoardIdValueInteger;

                            userBoardIdValueString = userInputParameterSplit[(userInputParameterSplit.length) - 1];
                            // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                            try {
                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                okCheck2 = true;
                            } //오류안나면 true
                            // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                            // 우선유저가  /posts/add?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                            // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 작성모드로 진입할수있는것이다.
                            catch (NumberFormatException e) {
                                System.out.println("수정할 게시판 번호를 입력해주세요.");
                            }

                            if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                if ( userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size() ) { // 드디어 진입. /posts/add?boardId=1 같은 형식과 같음.
                                    //  /posts/add?boardId=1&boardId=12... 형식이면 맨 마지막 12로 활용됨.
                                    // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                    // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.

                                    // 그러면 이제 해당 게시판의 게시글 작성이다. 실험중.


                                    String title = boardKeyStorage.get(userBoardIdValueInteger-1); // 해당 게시판 제목(key)

                                    System.out.print("제목을 작성해주세요 :");
                                    String key = sc.nextLine(); //게시글 제목(key)

                                    System.out.print("내용을 작성해주세요 :");
                                    String value = sc.nextLine(); //게시글 내용(value)

                                    mapKeyStorage.get(userBoardIdValueInteger-1).add(key); // 게시글 제목 저장 공간에 제목 저장.
                                    mapStorage.get(title).put(key,value); //해당 맵에 제목과 내용 저장완료
                                    originalLocalDate.get(userBoardIdValueInteger-1).add(LocalDate.now()); //게시글의 로컬데이트도 저장완료.

                                    System.out.println(userBoardIdValueInteger-1 + "번 게시판에 게시글이 저장되었습니다.");
                                    System.out.println();
                                    

                                }

                            }

                        }

                    }



                } else if( userInputPath[0].equals("") && userInputPath[1].equals("posts") &&
                        userInputCrud[0].equals("remove") && userInputCrud.length == 2) {

                    //   해당 게시판의 게시글삭제 진입시도.
                    //   지금까지 테스트해본결과로는 /posts/remove?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/remove?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                    //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                    userInputParameter = userInputCrud[1].split("&"); // 첫 "?"를 기준으로 나눈 그 뒤의 전부를 "&"로 나눈걸 변수에 담는다.
                    // 우선 userInputParameter.length() 는 최소 1이상 일수밖에없다. 앞의 else if 조건때문이다.
                    // 예를들면 userInputCrud[1]은 => parameter=10&b=value... 부분이다.
                    // 예시 입력값 => /posts/remove?postId=1&boardId=1 이라고치자. 그러면
                    // userInputParameter[0] postId=1
                    // userInputParameter[1] boardId=1 이다. 이렇게 최소한 가져가야한다.

                    // 경우의 수를 하나씩 실험해본결과 원하는 입력들은 공통점이 있었다.
                    // 공통점 => & 스플릿을하고 아래 for문 = 스플릿을 하고난뒤의 결과가 userInputParameter 렝스의 2배가 userInputParameterSplit 렝스가 되었다.

                    for (int i = 0; i < userInputParameter.length; i++) {
                        //userInputParameter.length() 는 최소 1이상 일수밖에없다. 위 게시판 수정 진입 시점인 else if 조건때문이다.
                        //따라서 for에 도달하면 무조건 한번은 실행된다.

                        userInputParameterSplit = userInputParameter[i].split("=");
                        //userInputParameterSplit[0] => parameter 부분
                        //userInputParameterSplit[1] => 10 부분
                        //userInputParameterSplit[2] => b 부분
                        //userInputParameterSplit[3] => value 부분 ...
                    } // 어떤 값이 왔더라도 userInputParameterSplit length는 무조건 1이상 이다.

                    //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                    if ( (userInputParameter.length)*2 == (userInputParameterSplit.length) ) { //여기 진입하는것은 /posts/remove?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                        //그리고 이 시점부턴 userInputParameterSplit 변수에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                        //원하는것: /posts/remove?postId=1&boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 삭제모드로 진입하게끔하고싶은것이다.
                        // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수배열에 저장됨.([0]을 포함)  ex) [0],[2],[4] ....

                        LinkedList<String> parameterNames = new LinkedList<>();

                        for (int i = 0; i < userInputParameterSplit.length; i = +2) { //파라미터 이름이 저장되는 parameterNames 에서 짝수만 뽑아 저장한다.
                            // 이 위 조건문들에따라 userInputParameterSplit.length 는 무조건 2이상이다.

                            String parameterName;
                            parameterName = userInputParameterSplit[i]; // userInputParameterSplit[i] 를 String[] 변수에 바로 집어넣으려하니 안돼서 이렇게 나눠서 저장해보았음.
                            parameterNames.add(parameterName);

                        } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.


                        // 예시 입력값 => /posts/remove?postId=1&boardId=1 ... 이라고치자. 그러면
                        // userInputParameter[0] postId=무엇         여기부터
                        // userInputParameter[1] boardId=무엇 이다. 이렇게 최소한 이것까진 확정돼야한다.
                        // userInputParameter[2] aaa=aaa...
                        // userInputParameterSplit[0] postId   여기부터
                        // userInputParameterSplit[1] 무엇
                        // userInputParameterSplit[2] boardId
                        // userInputParameterSplit[3] 무엇      최소한 이것까진 확정돼야한다.
                        // userInputParameterSplit[4] Id
                        // userInputParameterSplit[5] 무엇
                        // ...

                        if(parameterNames.get(0).equals("postId") && parameterNames.get(1).equals("boardId")  ) {
                            // userInputParameterSplit[0] postId 이고
                            // userInputParameterSplit[2] boardId 이면 진입가능하다. 즉 /posts/remove?postId=무엇&boardId=무엇   이면 진입한다. 따라서
                            // parameterNames.get(0) postId
                            // parameterNames.get(1) boardId 만 진입.


                        boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들을 체크하기위함.
                        boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                        for (int i = 1; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 2이다. (postId 와 boardId)

                            if (!(parameterNames.get(i).equals("boardId"))) {
                                okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 2번째인덱스부터(번호1부터) 1개라도 "boardId" 가 아니라면 false.
                                //만약 전부 "boardId" 가 맞다면 true.
                                break;
                            }
                        }

                        if (okCheck1) { // 유저URL 입력이 =>   /posts/remove?postId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함.

                            String userBoardIdValueString;
                            Integer userBoardIdValueInteger;

                            userBoardIdValueString = userInputParameterSplit[(userInputParameterSplit.length) - 1];
                            // 이렇게하면  /posts/remove?postId=aaa&boardId=bbb... 라는 입력값 중에서,
                            // 맨마지막에있는 value만 userBoardIdValueString 에 담는다.
                            // 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용한다는 규칙을 구현하고싶었다.

                            try { // userBoardIdValueString 이 숫자여야한다.
                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                okCheck2 = true;
                            } //오류안나면 true
                            // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                            // 우선유저가  /posts/remove?postId=1&boardId=1 이런식으로 게시판의 순서를 입력하길원함.
                            // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 유저가 입력한 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 수정모드로 진입할수있는것이다.
                            catch (NumberFormatException e) {
                                System.out.println("수정할 게시판 번호를 입력해주세요.");
                            }

                            if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                if (userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size()) { // 드디어 진입. /posts/remove?postId=1&boardId=1 같은 형식이 진입.
                                    //  /posts/remove?postId=1&boardId=12... 형식이면 boardId는 맨 마지막 12로 활용됨.
                                    // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                    // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.

                                    // 그러면 이제 해당 게시판의 게시글 삭제다. 실험중.

                                    String title = boardKeyStorage.get(userBoardIdValueInteger - 1); // 해당 게시판 제목(key)

                                    System.out.print("제목을 작성해주세요 :");
                                    String key = sc.nextLine(); //게시글 제목(key)

                                    System.out.print("내용을 작성해주세요 :");
                                    String value = sc.nextLine(); //게시글 내용(value)

                                    mapKeyStorage.get(userBoardIdValueInteger - 1).add(key); // 게시글 제목 저장 공간에 제목 저장.
                                    mapStorage.get(title).put(key, value); //해당 맵에 제목과 내용 저장완료
                                    originalLocalDate.get(userBoardIdValueInteger - 1).add(LocalDate.now()); //게시글의 로컬데이트도 저장완료.

                                    System.out.println(userBoardIdValueInteger - 1 + "번 게시판에 게시글이 저장되었습니다.");
                                    System.out.println();


                                }

                            }

                        }

                      }

                    }



                }
                         else { // @@@@@@@@@@@@@@@@@@@@@@@@@@@
                    System.out.println("존재하지 않는 명령어 입니다.");
                }


            }

        } while (!userInput.equals("종료"));
    }
}













                


/*


                if (userInput.equals("작성")) {

                    System.out.print("제목을 작성해주세요 :");
                    String key = sc.nextLine(); //굳이 userInput말고 다른 변수를 선언한 이유: 제목, 내용 둘다 한번에 저장하기위해서.
                    // 혹시나 제목, 내용이 전부 작성되지도않았는데 도중에 프로그램이 종료되면 엉키기때문에 한번에 처리하고싶다는 생각이 문득 들었다.
                    System.out.print("내용을 작성해주세요 :");
                    String value = sc.nextLine();
                    boardId.add(key); //key와 index를 저장하기위한 영구저장 역할.
                    boardStorage.put(key, value); //Map에 제목(키), 내용(밸류) 로 저장.
                    System.out.println();
                    System.out.println("게시물이 저장되었습니다.");

                } else if (userInput.equals("조회")) {

                    System.out.print("어떤 게시물을 조회할까요? ");
                    userInput = sc.nextLine(); //사용자가 꼭 "1번" 같은 형식처럼 입력하도록 하고싶었음.
                    int i; // for 문 밖으로 나가도 i를 그대로 활용할수있게끔 밖에 선언해둠.
                    boolean okCheck = false; // 올바른 경우가 아닐경우의 조건문을 생각하기가 복잡해서, boolean 변수로 활용하고 싶었음.

                    for (i = 1; i <= boardId.size(); i++) { // size는 실제 데이터개수가 그 size 개수만큼 실제 존재한다는 것이고, 이 for는 실제 범위만큼만 일일이 세보겠다는 뜻이다.
                        // 그리고 유저가 입력한 숫자 값이, 실제 존재하는 인덱스만큼만 일일이 세는 i값과 일치한다면, 그 번째의 배열에는 어떤 데이터가 실존한다는 뜻이다.
                        // "0번" 입력한 경우도 나중에 밑에 처리하기
                        if (userInput.equals(i + "번")) { // 유저가 "i번" 형식으로만 입력해야만 코드가 실행되게끔함. 그리고 입력한 "?번" 중에 ?가 실제로 존재해야만 수행하도록 함.
                            System.out.println();
                            System.out.println(i + "번 게시물");
                            System.out.println("제목 :[" + boardId.get(i - 1) + "]"); //실제 인덱스는 0부터 세므로 -1을 해줌.
                            System.out.println("내용 :[" + boardStorage.get(boardId.get(i - 1)) + "]");
                            okCheck = true; // 올바른 경우로 정해주기위함.
                            break;
                        }
                    }
                    if (okCheck == false) { // 값이 존재안할 경우만 이 코드를 실행 하고싶음. 이런것을 안하면, 멀쩡한 코드도 밑에 부분을 수행하게되기때문이다.
                        //여기에 온다는것은 유저의 입력값이 "0번" 또는 존재하는 게시글을 초과한 "?번" 이라는 뜻이다.
                        try {
                            System.out.println("제목 :[" + boardId.get(i) + "]"); // 오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println(userInput + " 게시글은 존재하지 않습니다.");
                        }
                    }

                } else if (userInput.equals("삭제")) {
                    System.out.print("어떤 게시물을 삭제할까요? ");
                    userInput = sc.nextLine(); //사용자가 꼭 "1번" 같은 형식처럼 입력하도록 하고싶었음.
                    int i; // for 문 밖으로 나가도 i를 그대로 활용할수있게끔 밖에 선언해둠.
                    boolean okCheck = false; // 올바른 경우가 아닐경우의 조건문을 생각하기가 복잡해서, boolean 변수로 활용하고 싶었음.

                    for (i = 1; i <= boardId.size(); i++) { // size는 실제 데이터개수가 그 size 개수만큼 실제 존재한다는 것이고, 이 for는 실제 범위만큼만 일일이 세보겠다는 뜻이다.
                        // 그리고 유저가 입력한 숫자 값이, 실제 존재하는 인덱스만큼만 일일이 세는 i값과 일치한다면, 그 번째의 배열에는 어떤 데이터가 실존한다는 뜻이다.

                        if (userInput.equals(i + "번")) { // 유저가 "i번" 형식으로만 입력해야만 코드가 실행되게끔함. 그리고 입력한 "?번" 중에 ?가 실제로 존재해야만 수행하도록 함.

                            System.out.println();
                            boardStorage.remove(boardId.get(i - 1));// 해당 인덱스의 해당 키와 데이터를 쌍으로 Map에서 제거.
                            boardId.remove(i-1); // 해당 인덱스의 해당 키값을 가진 데이터를 KeyIndexStorage 에서도 제거.
                            //그러면 뒤의 인덱스의 키들은 앞자리로 하나씩 당겨짐.
                            okCheck = true; // 올바른 경우라고 정해주기위함.
                            System.out.printf("%d번 게시물이 성공적으로 삭제되었습니다!", i);
                            System.out.println();
                            break;
                        }
                    }
                    if (okCheck == false) { // 값이 존재안할 경우만 이 코드를 실행 하고싶음. 이런것을 안하면, 멀쩡한 코드도 밑에 부분을 수행하게되기때문이다.
                        //여기에 온다는것은 유저의 입력값이 "0번" 또는 존재하는 게시글을 초과한 "?번" 이라는 뜻이다.
                        try {
                            boardStorage.remove(boardId.get(i)); //오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println(userInput + " 게시글은 존재하지 않습니다."); //
                        }
                    }

                } else if (userInput.equals("수정")) {
                    System.out.print("어떤 게시물을 수정할까요? ");
                    userInput = sc.nextLine(); //사용자가 꼭 "1번" 같은 형식처럼 입력하도록 하고싶었음.
                    int i; // for 문 밖으로 나가도 i를 그대로 활용할수있게끔 밖에 선언해둠.
                    boolean okCheck = false; // 올바른 경우가 아닐경우의 조건문을 생각하기가 복잡해서, boolean 변수로 활용하고 싶었음.

                    for (i = 1; i <= boardId.size(); i++) { // size는 실제 데이터개수가 그 size 개수만큼 실제 존재한다는 것이고, 이 for는 실제 범위만큼만 일일이 세보겠다는 뜻이다.
                        // 그리고 유저가 입력한 숫자 값이, 실제 존재하는 인덱스만큼만 일일이 세는 i값과 일치한다면, 그 번째의 배열에는 어떤 데이터가 실존한다는 뜻이다.

                        if (userInput.equals(i + "번")) { // 유저가 "i번" 형식으로만 입력해야만 코드가 실행되게끔함. 그리고 입력한 "?번" 중에 ?가 실제로 존재해야만 수행하도록 함.
                            System.out.println();
                            System.out.printf("%d번 게시물을 수정합니다.", i);
                            System.out.println();
                            //편의를 위해 기존 제목 표시 고려해보기.
                            System.out.print("바꿀 제목 :");
                            String changeKey = sc.nextLine(); //굳이 userInput말고 다른 변수를 선언한 이유: 내용까지 수정했을때 제목, 내용 둘다 한번에 바꾸기위해서.
                            // 혹시나 제목, 내용이 전부 수정되지도않았는데 도중에 프로그램이 종료되면 엉키기때문에 한번에 처리하고싶다는 생각이 문득 들었기 때문이다.
                            System.out.print("바꿀 내용 :");
                            String changeValue = sc.nextLine();

                            boardStorage.remove(boardId.get(i - 1)); // 수정전 게시글을 Map에서 영구삭제
                            boardId.remove(i - 1); //보관돼있던 수정전 key도 영구삭제한다. 그러면 뒤의 인덱스의 키들은 앞자리로 하나씩 당겨짐.
                            boardId.add(i - 1, changeKey); // 수정한 key를 다시 삭제한 그 자리에 저장한다. 그러면 그 뒤의 인덱스 키들은 한칸씩 밀림.
                            boardStorage.put(boardId.get(i - 1), changeValue); // 수정게시글을 Map에 영구저장
                            //편의를 위해 기존 내용 보여주기 고려.
                            okCheck = true; // 올바른 경우라고 정해주기위함.
                            System.out.println();
                            System.out.printf("%d번 게시물이 성공적으로 수정되었습니다!", i);
                            System.out.println();
                            break;
                        }
                    }
                    if (okCheck == false) { // 값이 존재안할 경우만 이 코드를 실행 하고싶음. 이런것을 안하면, 멀쩡한 코드도 밑에 부분을 수행하게되기때문이다.
                        //여기에 온다는것은 유저의 입력값이 "0번" 또는 존재하는 게시글을 초과한 "?번" 이라는 뜻이다.
                        try {
                            boardStorage.remove(boardId.get(i)); //오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println(userInput + " 게시글은 존재하지 않습니다.");
                        }
                    }
                }  else if(userInput.equals("목록")){
                    System.out.printf("총 게시글은 %d개 작성되어있습니다." , boardId.size());
                    System.out.println();
                    System.out.println();

                    for(int i=1; i<=boardId.size(); i++){

                        System.out.println(i + "번 게시물");
                        System.out.println("제목 :[" + boardId.get(i - 1) + "]"); //실제 인덱스는 0부터 세므로 -1을 해줌.
                        System.out.println("내용 :[" + boardStorage.get(boardId.get(i - 1)) + "]");
                        System.out.println();
                    }

                } else {
                    System.out.println("존재하지 않는 명령어 입니다.");
                }
            }

        } while (!userInput.equals("종료"));


        System.out.println("프로그램이 종료됩니다.");

    }
}


*/
