


import java.lang.reflect.Array;
import java.util.*;

//241022_~01:30 // 2단계1하는중. 아침부터 그거하면됨. 내가원하는 입력받는패턴 구하기.
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
        // 게시판 생성에 대해 구현하다가 do {}가 한번 끝나면 생성해둔 게시판이 스코프 문제로 사라져버림.
        // 그 문제로 고민하다가, 프로그램 종료전까지는 새로 생성해둔 게시'판'을 항상 사용하고싶은 생각에, 맵자체를 저장해두면 어떨까라는 생각이들었음.
        // 그리고 그 게시판의 이름도 정해야하는것같아서 이름(key)을 붙일수있는 Map 자료구조로 이렇게 만들어봄.
        LinkedList<LinkedList<String>> mapKeyStorage = new LinkedList<>(); // 만들어진 게시판(Map)의 게시글제목들을 저장할 공간.
        LinkedList<String> boardKeyStorage = new LinkedList<>(); // 게시판 삭제를 MapStorage에서 게시판을 삭제할때, Key를 알아야하는데 그 key를 저장할 공간.




        do {
            System.out.print("손님 ");
            userInput = sc.nextLine();
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

                userInputCrud = userInputPath[2].split("\\?"); // 형식대로 입력했으면 2번째인덱스(3번째배열)에는 add?parameter=10&b=value... 이런식으로 저장되어있을것이다.
                //따라서 그것을 한번더 ? 기준으로 나눠서 저장한다.
                // userInputCrud[0] => add 부분
                // userInputCrud[1] => parameter=10&b=value... 부분





                if (userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                        userInputPath[2].equals("add") && userInputPath.length == 3) { //   게시판 작성. /boards/add

                    System.out.println("게시판의 이름을 정해주세요.");
                    userInput = sc.nextLine();

                    mapKeyStorage.add(new LinkedList<>()); // 이것으로 방금 만든 게시판이 몇번째 게시판인지 그 인덱스를 활용해 알수있음.
                    mapStorage.put(userInput, new HashMap<>()); // 게시판 저장소용 Map 에다가, 방금만든 게시판 이름을 key로 잡고 그 key에 붙는 새로운 게시판을 생성함.
                    //이제 해당 key(게시판)으로 접근하면, 해당 게시판만의 게시물들을 String타입으로 제목(key)과 내용(vlaue)을 저장할수있음.
                    boardKeyStorage.add(userInput); // 추가한 Map의 키를 저장.

                    System.out.println("게시판이 저장되었습니다.");




                } else if ( userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                        userInputCrud[0].equals("edit") && userInputCrud.length == 2 ) {    //   게시판 수정 진입시도 /boards/edit?abc..
                    //   /boards/edit?abc.. 같은 입력이면 통과 됐을거임.
                    //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거고 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                    userInputParameter = userInputCrud[1].split("&");
                    // userInputCrud[1] => parameter=10&b=value... 부분

                    // ?abc 였다면 length가 1. 이때 userInputParameter[0] 은 => abc 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc 이고, [1]은 없음
                    //분석: userInputParameter 렝스는1, Split렝스는 1.

                    // ?abc= 였다면 length가 1. 이때 userInputParameter[0] 은 => abc= 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc 이고, [1]은 없음
                    // 분석: userInputParameter 렝스는1, Split렝스는 1.

                    // ?abc== 였다면 length가 1. 이때 userInputParameter[0] 은 => abc== 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc 이고, [1]은 없음
                    // 분석: userInputParameter 렝스는1, Split렝스는 1.

                    // ?abc==abc 였다면 length가 1. 이때 userInputParameter[0] 은 => abc==abc 이고 [1]은 없음. userInputParameterSplit[0] 은 => abc, [1]은 "", [2]는 abc
                    // 분석: userInputParameter 렝스는1, Split렝스는 3.

                    /////ㅇㅇㅇ ?aaa=aaa 였다면 length가 1. => 이때 userInputParameter[0]은 aaa=aaa이고, [1]은 없음. userInputParameterSplit[0] 은 =>aaa, [1]은 aaa.
                    //분석: userInputParameter 렝스는1, Split렝스는 2.
                    // 그러면 &스플릿때리고 for = 때리고난뒤에 userInputParameter.length==1 && userInputParameterSplit.length==2 면 통과.

                    //////ㅇㅇㅇ ?aaa=aaa&bbb=bbb 였다면 length가 2. => 이경우 userInputParameter[0]은 aaa=aaa이고, [1]은 bbb=bbb.
                    ////// 위의 userInputParameterSplit[0] 은 aaa [1] aaa [2] bbb [3] bbb.
                    //분석: userInputParameter 렝스는2, Split렝스는 4.
                    // 그러면 &스플릿때리고 for = 때리고난뒤에 userInputParameter.length==2 && userInputParameterSplit.length==4 면 통과.

                    //////ㅇㅇㅇ ?aaa=aaa&bbb=bbb&ccc=ccc 였다면 length가 3. => 이경우 userInputParameter[0]은 aaa=aaa이고, [1]은 bbb=bbb. [2]는 ccc=ccc.
                    ////// 위의 userInputParameterSplit[0] 은 aaa [1] aaa [2] bbb [3] bbb. [4] ccc [5] ccc.
                    //분석: userInputParameter 렝스는3, Split렝스는 6.
                    // 그러면 &스플릿때리고 for = 때리고난뒤에 userInputParameter.length==3 && userInputParameterSplit.length==6 면 통과.

                    //// 경우의 수를 하나씩 실험해본결과 내가 원하는 입력들은 공통점이 있었다. => &스플릿때리고 for = 때리고난뒤에, userInputParameter 렝스의 2배가 Split렝스였다.


                    // ?aaa&bbb=bbb 였다면 length가 2. => 이경우 userInputParameter[0]은 aaa. [1]bbb=bbb. userInputParameterSplit[0] 은 aaa, [1]은 bbb. [2]는 bbb.
                    // 분석: userInputParameter 렝스는2, Split렝스는 3.

                    //ㄴㄴㄴㄴ ?aaa=aaa&bbb 였다면 length가 2. => 이경우 userInputParameter[0]은 aaa=aaa이고, [1은] bbb.
                    // 위의 userInputParameterSplit[0]은 aaa, [1]은 aaa, [2]는 bbb.
                    // 분석: userInputParameter 렝스는2, Split렝스는 3.

                    //ㄴㄴㄴㄴ ?aaa=aaa&bbb&ccc 였다면 length가 3. => 이경우 userInputParameter[0]은 aaa=aaa이고, [1은] bbb [2] ccc.
                    // 위의 userInputParameterSplit[0]은 aaa, [1]은 aaa, [2]는 bbb [3]ccc.
                    // 분석: userInputParameter 렝스는3, Split렝스는 4.

                        for (int i = 0; i < userInputParameter.length; i++) { //일단 못잡은 예외는 나중에 생각해보자. //  /boards/edit?parameter=10&b=value...
                            userInputParameterSplit = userInputParameter[i].split("=");
                            //userInputParameterSplit[0] => parameter 부분
                            //userInputParameterSplit[1] => 10 부분
                            //userInputParameterSplit[2] => b 부분
                            //userInputParameterSplit[3] => value 부분 ...
                        }


                            if ( (userInputParameter.length)*2 == (userInputParameterSplit.length) ) { //여기 진입하는것은 /board/edit?aaa=aaa.. 이런식으로 입력 받은것들이다.
                                //그리고 Split에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                                //원하는것: /board/edit?boardId=게시판번호 또는 게시판이름   => 을 입력받으면 해당 게시판 수정모드로 진입하게끔하고싶은것.
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

                                if (okCheck1) { // 유저URL 입력이 =>   /boards/edit?boardId=aaa&boardId=bbb ... 와 같은 입력만 여기에 진입함.

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

                                        if ( userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size() ) { // 드디어 진입. /boards/edit?boardId=1 처럼.

                                            // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                            // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                            // 그럼 이 공간에서 드디어 해당 게시판을 수정할 수 있도록 진입된다.

                                            //boardId의 게시판 수정진입 성공.
                                            System.out.printf("%d번 게시판을 수정합니다", userBoardIdValueInteger);


                                        }


                                    }


                                }

                                //여기면 오류인가?


                            } //오류


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
                            // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판 수정모드로 진입할수있는것이다.
                            catch (NumberFormatException e) {
                                System.out.println("수정할 게시판 번호를 입력해주세요.");
                            }

                            if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                if ( userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size() ) { // 드디어 진입. /boards/remove?boardId=1 처럼입력한값이 들어옴.

                                    // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                    // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                    // boardKeyStorage 도 게시판 생성마다 1씩늘어난다.
                                    // 그럼 이 공간에서 드디어 해당 게시판을 수정할 수 있도록 진입된다.

                                    //boardId의 게시판 삭제진입 성공.

                                    //실험중. 키가존재안할수도.
                                    String removeKey = boardKeyStorage.get(userBoardIdValueInteger-1);
                                    mapStorage.remove(removeKey); // 해당 게시판의 제목과 내용을 묶어서 저장해놓은 공간을 삭제.
                                    mapKeyStorage.remove(userBoardIdValueInteger-1); // 해당게시판의 게시글 제목들을 순서대로 저장한 공간을 삭제.
                                    boardKeyStorage.remove(userBoardIdValueInteger-1);
                                    //마지막으로, 게시판 생성 순서와 해당 게시판의 제목을 이어서 저장해둔 공간(게시판 순서와, 맵의 키를 이어붙일 용도의 공간)에서 해당 게시판 제목을 삭제.

                                    System.out.printf("%d번 게시판을 삭제합니다", userBoardIdValueInteger); //

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
                        boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                        for (int i=0; i < parameterNames.size(); i++) {

                            if (!(parameterNames.get(i).equals("boardName"))) {
                                okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardName" 이 아니라면 false.
                                //만약 전부 "boardName" 이 맞다면 true.
                                break;
                            }
                        }

                        if (okCheck1) { // 유저URL 입력이 =>   /boards/view?boardName=aaa&boardName=bbb ... 와 같은 입력만 여기에 진입함.

                            String userBoardIdValueString;
                            Integer userBoardIdValueInteger;

                            userBoardIdValueString = userInputParameterSplit[(userInputParameterSplit.length) - 1];
                            // 이렇게하면 맨마지막 boardName의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                            try {
                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                okCheck2 = true;
                            } //오류안나면 true
                            // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                            // 우선유저가  /boards/view?boardName=1  이런식으로 게시판의 순서를 입력하길원함.
                            // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판 뷰모드로 진입할수있는것이다.
                            catch (NumberFormatException e) {
                                System.out.println("목록을 확인할 게시판 번호를 입력해주세요.");
                            }

                            if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                if ( userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size() ) { // 드디어 진입. /boards/view?boardName=1 처럼입력한값이 들어옴.

                                    // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                    // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                    // boardKeyStorage 도 게시판 생성마다 1씩늘어난다.
                                    // 그럼 이 공간에서 드디어 해당 게시판 목록을 확인할할 수 있도록 진입된다.

                                    //boardName 의 게시판 목록확인 진입 성공.

                                    //실험중



                                }
                            }

                        }

                    }


















                } else { // @@@@@@@@@@@@@@@@@@@@@@@@@@@
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
