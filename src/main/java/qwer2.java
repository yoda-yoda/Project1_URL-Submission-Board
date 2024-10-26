
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

//241024_18:37~ 2단계3 예외빼고 마무리.
public class qwer2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;
        String[] userInputPath;// userInput을 split 한 경로 문자열 저장용.
        String[] userInputCrud; // split한 경로 문자열에서 파라미터부분을 따로 또 분리해서 그것을 저장할 용도.
        String[] userInputParameter;
        String[] temporaryParameterSplit = {"a"}; // 나중에 if조건문 활용하려하는데 초기화가 안되어있다는 오류가자꾸떠서 적어주었다.
        ArrayList<String> userInputParameterSplit;



        Map<String, Map<String, String>> mapStorage = new HashMap<>();// 게시'글'을 저장해두는 게시'판'들의 저장소다. 이렇게 한 이유=>
        // 고민해보다가 문득 맵속에 맵을 저장하면 게시판의 저장소가 될것같았다. 그리고 메모리 문제로 사라지지않게 최상위에 저장하고 싶었다.
        // 그리고 그 게시판의 이름도 정해야하는것같아서 이름(key)을 붙일수있는 Map 자료구조로 이렇게 만들었다.
        LinkedList<LinkedList<String>> mapKeyStorage = new LinkedList<>(); // 만들어진 게시판(Map)의 게시글제목들을 저장할 공간. 인덱스로 몇번째 생성게시판인지 알수있다.
        LinkedList<String> boardKeyStorage = new LinkedList<>(); // 예를들어 MapStorage에서 게시판을 삭제할때, Key를 알아야하는데 그 key를 저장할 공간.
        //그리고 이것과 mapKeyStorage도 인덱스를 통해 연결가능하다.
        LinkedList<LinkedList<LocalDate>> originalLocalDate = new LinkedList<>();
        LinkedList<LinkedList<LocalDate>> editLocalDate = new LinkedList<>(); // 수정된 작성일을 인덱스와 연결해서 저장하고싶었다.


        LinkedList<String> userAccount = new LinkedList<>();
        LinkedList<String> userPassword = new LinkedList<>();
        LinkedList<String> userName = new LinkedList<>();
        LinkedList<String> userNickname = new LinkedList<>();
        LinkedList<String> userEmail = new LinkedList<>();
        LinkedList<String> boardWriter = new LinkedList<>();
        LinkedList<LinkedList<String>> postWriter = new LinkedList<>();

        LinkedList<LocalDate> userSignUpDay = new LinkedList<>();
        LinkedList<LocalDate> userEditDay = new LinkedList<>();



        boolean loginCheck1 = false; // 만약 로그인에 성공했다면 true인 상태로 프로그램(do문) 계속 실행됨. 초기화 안해두면 if문 조건문안에서 오류가나서 해둠.
        String loginingAccount = null; // 만약 로그인에 성공했다면 해당 계정을 담아 활용하기위해 만듬. 초기화 안해두면 if문 조건문안에서 오류가나서 해둠.

















        // 똑같은 이름, 입력 등에대해 체크해보기.

        do {
            System.out.print("손님 ");
            userInput = sc.nextLine();
            System.out.println();
            userInputPath = null;
            userInputCrud = null;
            userInputParameter = null;
            temporaryParameterSplit = null;
            userInputParameterSplit = new ArrayList<>();
            boolean elseCheck1 =false;

            // 기능이 한번끝나서 다시 다음에 명령어에 재사용하기위해 null로 안의 값들을 삭제.

            if (!userInput.equals("종료")) {

                    userInputPath = userInput.split("/",3); // 유저의 입력중에 "/"를 기준으로 3덩이까지만 나눠서 저장함.
                    // 테스트결과 여기변수에 담는것까진 "/"든, 공백이든 뭐라고 입력해도 오류가안남.
                    // 예시 입력값=> /boards/add?parameter=10&b=value...
                    // userInputPath[0] => 빈 부분
                    // userInputPath[1] => boards 부분
                    // userInputPath[2] => add?parameter=10&b=value... 부분

                    boolean firstCheck = false;

                try {
                userInputCrud = userInputPath[2].split("\\?",2); // userInputPath[2] 중에 "?"를 기준으로 2덩이까지만 나눠서 저장함.
                    // limit 2를 정해줌으로써 맨뒤 ?가 추가되어도 진입되는 문제를 예방가능.
                    // 아마 2번인덱스(3번째배열)에는 add?parameter=10&b=value... 이런식으로 저장되어있을것이다. 따라서 그것을 한번더 ? 를 기준으로 나눠서 userInputCrud에 담는다.
                    // userInputCrud[0] => add 부분
                    // userInputCrud[1] => parameter=10&b=value... 부분

                    firstCheck = true;
                    // 여기서 오류나면 무조건 else에 도달하게끔 하기위해 만들었다. try안의 오류코드다음은 아예실행 안되는것을 boolean 과같이 활용하였다.
                    //아닌가 어차피그렇게되나.

                } catch (ArrayIndexOutOfBoundsException e) {
                   //맨밑쯤 출력처리함.
                }

                if (firstCheck && userInputPath.length == 3 && userInputPath[0].equals("") &&
                        !userInputPath[1].equals("") && !userInputPath[2].equals("") ){ // 여기 진입하는 입력은 최소한 [/abc/abc] 여기까지는 abc내용이 무엇이든 충족되도록 시도함.
                    // 원하는 입력값들은 전부 userInputPath.length == 3 이므로 그것을 적용.
                    //  firstCheck=true만 진입된다면 userInputCrud != null 인것도 보장받음.



                    if (userInputPath[1].equals("boards") && userInputPath[2].equals("add")) { //   게시판 작성. 입력이 /boards/add   일경우에만 진입할것이다.
                        // 원래는 맨마지막에 /를 더 추가해도 진입이 가능해서 문제였지만 맨 윗줄에서 userInputPath = userInput.split("/",3); 처럼
                        // limit을 3 설정하면서 뒤의 덩어리자체를 저장시켰기에 필터링이 가능해졌다.

                        boolean existingName = false;
                        System.out.print("생성할 게시판의 이름을 입력해주세요 :");
                        userInput = sc.nextLine();

                        for(int i=0; i < boardKeyStorage.size(); i++){ // 이름이 기존게시판과 같은지 체크하기위한 for문.

                            if( userInput.equals(boardKeyStorage.get(i)) ) {   // 예외처리는 안해도된다. 사이즈0이면 for자체를실행안하니까.
                                existingName = true; //생성에 진입못하게만듬.

                                System.out.println();
                                System.out.println("이미 해당 게시판이 존재합니다.");

                                elseCheck1 = true; // 맨밑 유효하지않은 URL 이라는 출력을 막기위함.
                                break;
                            }

                        }
                        
                        if(!existingName){

                            mapKeyStorage.add(new LinkedList<>()); // 이것으로 방금 만든 게시판이 몇번째 게시판인지 그 인덱스를 활용해 알수있음.
                            mapStorage.put(userInput, new HashMap<>()); // 게시판 저장소용 Map 에다가, 방금만든 게시판 이름을 key로 잡고 그 key에 붙는 새로운 게시판을 생성함.
                            //이제 해당 key(게시판)으로 접근하면, 해당 게시판만의 게시물들을 String타입으로 제목(key)과 내용(vlaue)을 저장할수있음.
                            boardKeyStorage.add(userInput); // 추가한 Map의 키를 저장.
                            originalLocalDate.add(new LinkedList<>()); // 작성일 저장소에 인스턴스를 추가하고 인스턴스 인덱스를통해 몇번째게시판인지 알수있음. 또 인스턴스안에 링크드리스트로는 작성일과
                            //게시글 번호랑 연결되어서 그 게시글과 작성일을 연결할수있다.
                            editLocalDate.add(new LinkedList<>()); // 수정일 저장소도 마찬가지.
                            postWriter.add(new LinkedList<>()); // postWriter 를 mapKeyStorage 처럼 인덱스와 연결해서 활용한다.

                            if(loginCheck1){
                                boardWriter.add(loginingAccount);
                            } else {
                                boardWriter.add("비회원"); // boardWriter도 boardKeyStorage 처럼 인덱스와 연결해서 활용한다.
                            }

                            System.out.println();
                            System.out.printf("[%s]이 [%d]번 게시판에 저장되었습니다.", userInput, boardKeyStorage.size());
                            // 방금 게시판을 저장했다면 boardKeyStorage의 사이즈가 곧 그 게시판의 번호다.
                            System.out.println();

                            elseCheck1 = true;

                        }


                    } else if (userInputPath[1].equals("boards") &&
                            userInputCrud[0].equals("edit") && userInputCrud.length == 2) {    //   게시판 수정 진입시도.
                        //   지금까지 테스트해본결과로는 /boards/edit?abc 여기까지는 확정되어야 진입가능하다.
                        //  원래  /boards/edit?abc???? 같은 입력도 진입되어서 문제였지만, userInputCrud 변수에 담을때 limit 2를 정해줌으로써 맨뒤 ?가 추가되어도 진입되는 문제를 해결했다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                         int splitLimit = userInputCrud[1].split("&").length;
                         userInputParameter = userInputCrud[1].split("&",splitLimit);
                        // 맨마지막에 붙이는 "&"를 필터링하려면 limit을 알아야했다.
                        //// 지금까지 테스트 결과 limit의 숫자는 split한 것의 length 숫자로 대입하면 맨뒤 "&"필터링은 해결되었다.

                        // 우선 userInputParameter.length() 는 최소 1이상 일수밖에없다. 앞의 조건문 때문이다.
                        // 예를들면 userInputCrud[1]은 => parameter=10&b=value... 부분이다.
                        // 아래는 userInputCrud[1]이 어떤값일때, 그걸 "&"기준으로 split한 userInputParameter 경우의 수와 밑의 for문을 한번 분석해본 것이다.

                        // ?abc 였다면 userInputParameter의 length 가 1. 이때 userInputParameter[0] 은 => abc 이고 [1]은 없음.
                        // 그러면 밑의 for문에서 userInputParameterSplit.get(0) 은 => abc 이고, (1)은 없음.
                        //분석: userInputParameter 렝스는1, Split.size()는 1.

                        // ?abc= 였다면 length가 1. 이때 userInputParameter[0] 은 => abc= 이고 [1]은 없음. userInputParameterSplit.get(0) 은 => abc 이고, (1)은 없음
                        // 분석: userInputParameter 렝스는1, Split.size()는 1.

                        // ?abc== 였다면 length가 1. 이때 userInputParameter[0] 은 => abc== 이고 [1]은 없음. userInputParameterSplit.get(0) 은 => abc 이고, (1)은 없음
                        // 분석: userInputParameter 렝스는1, Split.size()는 1.

                        // ?abc==abc 였다면 length가 1. 이때 userInputParameter[0] 은 => abc==abc 이고 [1]은 없음. userInputParameterSplit.get(0) 은 => abc, (1)은 "", (2)는 abc
                        // 분석: userInputParameter 렝스는1, Split.size()는 3.

                        // ?aaa&bbb=bbb 였다면 length가 2. 이때 userInputParameter[0]은 aaa [1] bbb=bbb 이다. userInputParameterSplit.get(0) 은 aaa (1)은 bbb (2)는 bbb 이다.
                        // 분석: userInputParameter 렝스는2, Split.size()는 3.

                        // ?aaa=aaa&bbb 였다면 length가 2. 이때 userInputParameter[0]은 aaa=aaa [1] bbb 이다. userInputParameterSplit.get(0)은 aaa (1)은 aaa (2)는 bbb 이다.
                        // 분석: userInputParameter 렝스는2, Split.size()는 3.

                        // ?aaa=aaa&bbb&ccc 였다면 length가 3. 이때 userInputParameter[0]은 aaa=aaa [1] bbb [2] ccc 이다. userInputParameterSplit.get(0)은 aaa (1)은 aaa (2)는 bbb (3)ccc 이다.
                        // 분석: userInputParameter 렝스는3, Split.size()는 4.

                        /////바라는 입력이다. ?aaa=aaa 였다면 length가 1. => 이때 userInputParameter[0]은 aaa=aaa이고, [1]은 없음. userInputParameterSplit.get(0) 은 =>aaa, (1)은 aaa.
                        //분석: userInputParameter 렝스는1, Split.size()는 2.
                        // 그러면 &스플릿하고 for = 하고난뒤에 userInputParameter.length==1 && userInputParameterSplit.size()==2 면 통과.

                        //////바라는 입력이다. ?aaa=aaa&bbb=bbb 였다면 length가 2. => 이경우 userInputParameter.get(0)은 aaa=aaa이고, (1)은 bbb=bbb.
                        //////  userInputParameterSplit.get(0) 은 aaa (1) aaa (2) bbb (3) bbb.
                        //분석: userInputParameter 렝스는2, Split.size()는 4.
                        // 그러면 &스플릿하고 for = 하고난뒤에 userInputParameter.length==2 && userInputParameterSplit.size()==4 면 통과.

                        //////바라는 입력이다. ?aaa=aaa&bbb=bbb&ccc=ccc 였다면 length가 3. => 이경우 userInputParameter.get(0)은 aaa=aaa이고, (1)은 bbb=bbb. (2)는 ccc=ccc.
                        ////// 위의 userInputParameterSplit.get(0) 은 aaa (1) aaa (2) bbb (3) bbb. (4) ccc (5) ccc.
                        //분석: userInputParameter 렝스는3, Split.size()는 6.
                        // 그러면 &스플릿하고 for = 하고난뒤에 userInputParameter.length==3 && userInputParameterSplit.size()==6 면 통과.

                        // 경우의 수를 하나씩 실험해본결과 원하는 입력들은 공통점이 있었다.
                        // 공통점 => & 스플릿을하고 아래 for문 = 스플릿을 하고난뒤의 결과가 userInputParameter 렝스의 2배가 userInputParameterSplit.size() 가 되었다.

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /boards/edit?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /boards/edit?boardId=1...   => 처럼 입력받으면 해당 게시판 edit모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                            boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                if (!(parameterNames.get(i).equals("boardId"))) {
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                    //만약 전부 "boardId" 가 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>   /boards/edit?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                String userBoardIdValueString;
                                Integer userBoardIdValueInteger;

                                userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                try {
                                    userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                    okCheck2 = true;
                                } //오류안나면 true
                                // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음.
                                // 우선유저가  /boards/edit?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판 수정모드로 진입할수있는것이다.
                                catch (NumberFormatException e) {
                                    //맨밑쯤에 출력처리함.
                                }

                                if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                    userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                    if (userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size()) { // 드디어 진입. /boards/edit?boardId=1 처럼과같음.

                                        // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                        // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                        // 그럼 이 공간에서 드디어 해당 게시판을 수정할 수 있도록 진입된다.

                                        // 그러면 이제 해당 게시판의 이름을 수정하도록해야겠다. 실험중.

                                        String afterTitle; // userInput 변수를 안쓰고 새로 name으로 선언해준이유는 만약 "종료" 로 게시판이름을 적으면 반복문이끝나고 프로그램이 종료될까봐.
                                        String beforeTitle = boardKeyStorage.get(userBoardIdValueInteger - 1); // 수정전 게시판 제목(key)
                                        Map<String, String> beforeMapValue = mapStorage.get(beforeTitle);  // 수정전 게시판의 value. 즉 수정전 게시판의 게시글 제목과 내용 모음들.
                                        // 그래서 이때 beforeMapValue 는 수정전의 그 게시판 인스턴스의 메모리 주소를 담고있다.

                                        boolean existingName = false;
                                        boolean equalName = true;

                                        do{ //만약 수정한 게시판이름이 기존 게시판이름과 같다면 map저장 구조때문에 기존게시판 밸류가 삭제되므로 제한을 해주기위해 만든 do while 이다.

                                            existingName = false;

                                            System.out.printf("기존 게시판 이름 :[%s]", beforeTitle);
                                            System.out.println();
                                            System.out.print("새로운 게시판 이름을 입력해주세요 :");

                                            afterTitle = sc.nextLine();
                                            System.out.println();

                                            for(int i=0; i < boardKeyStorage.size(); i++){ // 게시판제목 저장소 사이즈만큼 실행하겠다.

                                                if( afterTitle.equals(boardKeyStorage.get(i)) ){
                                                    // 만약 변경할 제목이 기존제목과 하나라도 일치하는게 있다면 막겠다.
                                                    if( afterTitle.equals(beforeTitle) ){
                                                        System.out.println("기존 이름과 같습니다.");
                                                        equalName = false;
                                                        elseCheck1 = true; // 이거안하고 나가면 맨밑에 유효하지않은 URL이 출력되니까 써준다.
                                                        break; //이름을 기존이름 그대로 바꾸면 그건 문제없으니 통과하도록 허용.
                                                    }

                                                    System.out.println("이미 해당 게시판이 존재합니다. 다른 이름을 입력해주세요");
                                                    System.out.println();
                                                    existingName = true;
                                                    break;
                                                }


                                            }

                                        } while (existingName);

                                        if(equalName){

                                        mapStorage.put(afterTitle, mapStorage.get(beforeTitle)); // 기존의 게시판 인스턴스 메모리주소를 그대로 복사해서 가져왔기때문에 게시판값이 그대로다.
                                        mapStorage.remove(beforeTitle); // 맵의 맵에서 기존 key(게시판제목) 를 가진 노드를 삭제. 실험결과 이렇게해도 afterTitle의 밸류는 그대로있음.
                                        boardKeyStorage.remove(userBoardIdValueInteger - 1); // 게시판 제목 보관함에서도 기존것 삭제.
                                        boardKeyStorage.add(userBoardIdValueInteger - 1, afterTitle); // 삭제한 그 인덱스 자리에 새로운 게시판 제목 추가.

                                        System.out.println("게시판 이름이 [" + afterTitle + "] 로 수정되었습니다!");
                                        elseCheck1 = true;
                                        }

                                    }

                                }

                            }

                        }


                    } else if (userInputPath[1].equals("boards") &&
                            userInputCrud[0].equals("remove") && userInputCrud.length == 2) {  //게시판 remove 진입시도.
                        //   지금까지 테스트해본결과로는 /boards/remove?abc 여기까지는 확정되어야 진입가능하다. 물론 /boards/remove?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            // 맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /boards/remove?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /boards/remove?boardId=1...   => 처럼 입력받으면 해당 게시판 삭제 모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                            boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                if (!(parameterNames.get(i).equals("boardId"))) {
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                    //만약 전부 "boardId" 가 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>  /boards/remove?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                String userBoardIdValueString;
                                Integer userBoardIdValueInteger;

                                userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                try {
                                    userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                    okCheck2 = true;
                                } //오류안나면 true
                                // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                // 우선유저가  /boards/remove?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판 삭제모드로 진입할수있는것이다.
                                catch (NumberFormatException e) {
                                    //맨밑쯤 출력처리함.
                                }

                                if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                    userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                    if (userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size()) { // 드디어 진입. /boards/remove?boardId=1 처럼입력한값이 들어옴.

                                        // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                        // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.
                                        // boardKeyStorage 도 게시판 생성마다 1씩늘어난다.
                                        // 그럼 이 공간에서 드디어 해당 게시판을 삭제할 수 있도록 진입된다.

                                        //boardId의 게시판 삭제진입 성공.

                                        String removeKey = boardKeyStorage.get(userBoardIdValueInteger - 1); //삭제할 게시판의 제목키값
                                        mapStorage.remove(removeKey); // 해당 게시판의 제목과 내용을 묶어서 저장해놓은 공간을 삭제.
                                        mapKeyStorage.remove(userBoardIdValueInteger - 1); // 해당게시판의 게시글 제목들을 순서대로 저장한 공간을 삭제.
                                        originalLocalDate.remove(userBoardIdValueInteger - 1); // 작성일 보관소에도 해당게시판 저장소를 삭제.
                                        editLocalDate.remove(removeKey); // 수정일 보관소에도 해당게시판 저장소를 삭제.
                                        boardWriter.remove(userBoardIdValueInteger - 1); // 해당 게시판 생성자 저장소 삭제
                                        postWriter.remove(userBoardIdValueInteger - 1); // 각 게시판마다 게시글 생성을 저장하는 곳에서 해당 게시판도 삭제

                                        System.out.printf("[%d번] 게시판 [%s] 삭제가 완료되었습니다.", userBoardIdValueInteger, boardKeyStorage.get(userBoardIdValueInteger - 1));
                                        System.out.println();

                                        boardKeyStorage.remove(userBoardIdValueInteger - 1);
                                        //마지막으로, 게시판 생성 순서와 해당 게시판의 제목을 이어서 저장해둔 공간(게시판 순서와, 맵의 키를 이어붙일 용도의 공간)에서 해당 게시판 제목을 삭제.

                                        elseCheck1 = true;

                                    }
                                }

                            }

                        }


                    } else if (userInputPath[1].equals("boards") &&
                            userInputCrud[0].equals("view") && userInputCrud.length == 2) { //게시판 뷰 진입시도 /boards/view?abc..

                        //   /boards/view?abc.. 같은 입력이면 통과 됐을거임.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거고 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);
                        // userInputCrud[1] => parameter=10&b=value... 부분

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /boards/view?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /boards/view?boardName=자유게시판...   => 처럼 입력받으면 해당 게시판 edit모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardName" 인지 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { //parameterNames.size()는 무조건1이상일것임.

                                if (!(parameterNames.get(i).equals("boardName"))) {
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardName" 이 아니라면 false.
                                    //만약 전부 "boardName" 이 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>   /boards/view?boardName=aaa&boardName=bbb ... 와 같은 입력만 여기에 진입함.

                                String userBoardIdValueString;

                                userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardName의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                // 우선유저가  /boards/view?boardName=자유게시판  이런식으로 게시판 생성할때 정한 key를 입력하길원함.
                                // 그럼 이제 userBoardIdValueString 는 뭐냐면, 게시판의 이름(=제목=key)인것이고, 존재한다면 해당 게시판 뷰모드로 진입하게끔 한다.

                                boolean okCheck2 = false;
                                int boardKeyIndex = 0; // 밑의 for문에서 boardKeyStorage 에서의 어떤 저장된 키 값이 몇번인덱스인지 알고싶었는데 get메서드 인자 타입이 index밖에없어서 어떻게 할까하다가
                                // for문의 해당 i값을 여기 저장해서 이 변수를 활용하면어떨까 생각이들었다. 0은 초기화안됐다고 오류날까봐 일단 미리 초기화해둔것.

                                for (int i = 0; i < boardKeyStorage.size(); i++) { //오류는 구현하고 나중에 체크하자.넘복잡.

                                    if (boardKeyStorage.get(i).equals(userBoardIdValueString)) {
                                        okCheck2 = true; // boardKeyStorage 안의 키값들 중에 입력한 게시판 value 키값이 하나라도 실존한다면 true.
                                        boardKeyIndex = i; // 실존하는 해당 키 자리의 인덱스번호인 i를 이 변수에 저장. 이 i는 게시판의 생성 순서임. i를 MapKeyStorage 인덱스와 연결하면 그곳의 자료가 곧 그 게시판의 게시글들임.
                                        break;
                                    }
                                }


                                if (okCheck2) { // 드디어 진입. /boards/view?boardName=...&boardName=자유게시판.. 처럼 입력했고, 입력한 그 마지막 게시판 키가 실존해야 진입가능.

                                    int writeNumber = mapKeyStorage.get(boardKeyIndex).size(); // 이러면 이 변수에 해당게시판의 게시물 글 수가 담김.

                                    if (writeNumber != 0) {

                                        for (int i = 0; i < writeNumber; i++) { //해당 게시판의 글 수 만큼 실행하겠다. 이때 boardKeyIndex 는 해당 게시판의 인덱스번호(게시판 생성순서)임.

                                            System.out.printf("[%s] / ",userBoardIdValueString); // 게시판 이름도 출력한다.
                                            System.out.print((i + 1) + "번글 / ");
                                            System.out.print(mapKeyStorage.get(boardKeyIndex).get(i) + " / "); // 해당 게시판의 게시글중에 0번째(첫번째) 글제목부터 출력.
                                            System.out.print(originalLocalDate.get(boardKeyIndex).get(i) + " / "); // 해당 게시판의 0번째 게시글부터의 작성일부터 출력.
                                            System.out.print("작성자 :" + postWriter.get(boardKeyIndex).get(i)); // 해당 게시글 작성당시 작성자도 출력.
                                            System.out.println();
                                        }
                                        System.out.println();
                                    } else {
                                        System.out.println("해당 게시판에 작성된 게시글이 없습니다.");
                                    }
                                    elseCheck1 = true;

                                }

                            }

                        }

                    } else if (userInputPath[1].equals("posts") &&
                            userInputCrud[0].equals("add") && userInputCrud.length == 2) {  //   해당 게시판의 게시글작성 진입시도.

                        //   지금까지 테스트해본결과로는 /posts/add?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/add?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                           //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/add?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/add?boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 작성모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                            boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                if (!(parameterNames.get(i).equals("boardId"))) { // 파라미터이름들을 검사하도록 진입.
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                    //만약 전부 "boardId" 가 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>   /posts/add?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                String userBoardIdValueString;
                                Integer userBoardIdValueInteger;

                                userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                try {
                                    userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                    okCheck2 = true;
                                } //오류안나면 true
                                // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                // 우선유저가  /posts/add?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 작성모드로 진입할수있는것이다.
                                catch (NumberFormatException e) {
                                    //맨밑쯤 출력처리.
                                }

                                if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                    userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                    if (userBoardIdValueInteger > 0 && userBoardIdValueInteger <= mapKeyStorage.size()) { // 드디어 진입. /posts/add?boardId=1 같은 형식과 같음.
                                        //  /posts/add?boardId=1&boardId=12... 형식이면 맨 마지막 12로 활용됨.
                                        // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이면 진입.
                                        // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.

                                        // 그러면 이제 해당 게시판의 게시글 작성이다.

                                        String title = boardKeyStorage.get(userBoardIdValueInteger - 1); // 해당 게시판 제목(key)

                                        System.out.printf("[%d]번게시판 [%s]에 게시글을 작성합니다.", userBoardIdValueInteger, title);
                                        System.out.println();
                                        System.out.print("제목을 입력해주세요 :");
                                        String key = sc.nextLine(); //게시글 제목(key)

                                        System.out.print("내용을 입력해주세요 :");
                                        String value = sc.nextLine(); //게시글 내용(value)
                                        System.out.println();

                                        mapKeyStorage.get(userBoardIdValueInteger - 1).add(key); // 게시글 제목 저장 공간에 제목 저장.
                                        mapStorage.get(title).put(key, value); //해당 맵에 제목과 내용 저장완료
                                        originalLocalDate.get(userBoardIdValueInteger - 1).add(LocalDate.now()); //게시글의 로컬데이트도 저장완료.
                                        editLocalDate.get(userBoardIdValueInteger - 1).add(null);// 생성시 수정일도 인덱스를 맞춰주기위해 null로 저장해놓고,
                                        // 수정할때만 그 인덱스에 날짜를 넣어주려한다.

                                        if(loginCheck1){
                                            postWriter.get(userBoardIdValueInteger - 1).add(loginingAccount);
                                        } else {
                                            postWriter.get(userBoardIdValueInteger - 1).add("비회원");
                                        }

                                        System.out.printf("해당 글이 [%s]의 [%d]번글로 저장되었습니다.", title, mapKeyStorage.get(userBoardIdValueInteger - 1).size());
                                        // mapKeyStorage.get(userBoardIdValueInteger-1).size() 는 해당게시판의 방금저장한 게시글의 번호다.
                                        System.out.println();
                                        elseCheck1 = true;

                                    }
                                }
                            }
                        }

                    } else if (userInputPath[1].equals("posts") &&
                            userInputCrud[0].equals("remove") && userInputCrud.length == 2) { //   해당 게시판의 게시글삭제 진입시도.

                        //   지금까지 테스트해본결과로는 /posts/remove?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/remove?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                           //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/remove?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/remove?postId=1&boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 삭제모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.


                            // 분석중
                            // 예시 입력값 => /posts/remove?postId=1&boardId=1 ... 이라고치자. 그러면
                            // userInputParameter[0] postId=무엇         여기부터
                            // userInputParameter[1] boardId=무엇     여기까진 최소한 확정돼야한다.
                            // userInputParameter[2] aaa=aaa...
                            // userInputParameterSplit.get(0) postId   그리고 여기부터
                            // userInputParameterSplit.get(1) 무엇
                            // userInputParameterSplit.get(2) boardId
                            // userInputParameterSplit.get(3) 무엇      최소한 이것까진 확정돼야한다.
                            // userInputParameterSplit.get(4) aaa
                            // userInputParameterSplit.get(5) 무엇
                            // ...

                            try{ //   postId=1 까지만입력하면 parameterNames.get(1) 이 IndexOutOfBoundsException 일수있다.
                            if (parameterNames.get(0).equals("postId") && parameterNames.get(1).equals("boardId")) {
                                // userInputParameterSplit.get(0) postId 이고
                                // userInputParameterSplit.get(2) boardId 이면 진입가능하다. 즉 /posts/remove?postId=무엇&boardId=무엇   이면 진입한다. 그것은
                                // parameterNames.get(0) postId
                                // parameterNames.get(1) boardId 만 진입하는것.

                                boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들을 체크하기위함.
                                boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                                for (int i = 1; i < parameterNames.size(); i++) { // 파라미터네임들 검사 진입. 이때 parameterNames.size() 가 최소한 2이다.
                                    // 위 이프문으로인해 최소한 parameterNames.get(0) postId
                                    //                      parameterNames.get(1) boardId 이다.

                                    if (!(parameterNames.get(i).equals("boardId"))) {
                                        okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 2번째인덱스부터(번호1부터) 1개라도 "boardId" 가 아니라면 false.
                                        //만약 전부 "boardId" 가 맞다면 true.
                                        break;
                                    }
                                }

                                if (okCheck1) { // 유저URL 입력이 =>   /posts/remove?postId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함.

                                    String userPostIdValueString;
                                    String userBoardIdValueString;

                                    Integer userPostIdValueInteger;
                                    Integer userBoardIdValueInteger;

                                    userPostIdValueString = userInputParameterSplit.get(1); // 이렇게하면  /posts/remove?postId=aaa 에서 aaa값이 userPostIdValueString 에 저장.
                                    userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                    // 이렇게하면  /posts/remove?postId=aaa&boardId=bbb... 라는 입력값 중에서,
                                    // 맨마지막에있는 value만 userBoardIdValueString 에 담는다.
                                    // 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용한다는 규칙을 구현하고싶었다.

                                    try { // userPostIdValueString과 userBoardIdValueString 이 숫자여야한다.
                                        userPostIdValueInteger = Integer.parseInt(userPostIdValueString);
                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                        okCheck2 = true;

                                    } //오류안나면 true
                                    // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                    // 우선유저가  /posts/remove?postId=1&boardId=1 이런식으로 게시판의 순서를 입력하길원함.
                                    // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 유저가 입력한 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 삭제모드로 진입할수있는것이다.
                                    catch (NumberFormatException e) {
                                        //맨밑쯤 출력처리함.
                                    }

                                    if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 아래 if문안의 변수가 초기화안됐다며 오류가뜸.
                                        userPostIdValueInteger = Integer.parseInt(userPostIdValueString);

                                        if (userBoardIdValueInteger > 0 && userPostIdValueInteger > 0 &&
                                                userBoardIdValueInteger <= mapKeyStorage.size() &&
                                                userPostIdValueInteger <= mapKeyStorage.get(userBoardIdValueInteger - 1).size()) {
                                            // 드디어 여기서  /posts/remove?postId=1&boardId=1 같은 형식이 진입.
                                            // 그리고 예를들어 /posts/remove?postId=1&boardId=15&...boardId=12 이런 형식이면  맨 마지막 boardId의 값인 12로 활용됨.
                                            // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이고,
                                            // 입력 게시글 번호가 실제 생성되어있는 번호면 진입한것이다.

                                            // 그러면 이제 해당 게시판의 해당 게시글 삭제다.

                                            // userBoardIdValueInteger-1  = 해당 게시판의 실제 인덱스번호
                                            // userPostIdValueInteger-1  = 해당 게시글의 실제 인덱스번호

                                            String title = boardKeyStorage.get(userBoardIdValueInteger - 1); // 해당 게시판의 제목키
                                            String articleKey = mapKeyStorage.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1); //해당 게시판의 해당 게시글의 제목키

                                            mapStorage.get(title).remove(articleKey); // 맵의 해당맵에서 그 게시글(+내용)을 삭제.
                                            mapKeyStorage.get(userBoardIdValueInteger - 1).remove(userPostIdValueInteger - 1); // 게시글 제목 저장소에서 해당게시글 삭제.
                                            originalLocalDate.get(userBoardIdValueInteger - 1).remove(userPostIdValueInteger - 1); // 작성일 저장소에서 해당 게시글 작성일도 삭제.
                                            editLocalDate.get(userBoardIdValueInteger - 1).remove(userPostIdValueInteger - 1);

                                            postWriter.get(userBoardIdValueInteger - 1).remove(userPostIdValueInteger-1); // 게시글 작성자 저장소에서도 해당 저장삭제.

                                            System.out.printf("[%s]의 [%d]번 글이 삭제되었습니다.", title, userPostIdValueInteger);
                                            System.out.println();
                                            elseCheck1 = true;

                                        }

                                    }

                                }

                            }}catch (IndexOutOfBoundsException e){
                               //맨밑에 출력처리.
                            }

                        }

                    } else if (userInputPath[1].equals("posts") &&
                            userInputCrud[0].equals("edit") && userInputCrud.length == 2) {

                        //   해당 게시판의 게시글수정 진입시도.
                        //   지금까지 테스트해본결과로는 /posts/edit?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/edit?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/edit?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/edit?postId=1&boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 수정모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.


                            // 예시 입력값 => /posts/edit?postId=1&boardId=1 ... 이라고치자. 그러면
                            // userInputParameter[0] postId=무엇         여기부터
                            // userInputParameter[1] boardId=무엇 이다. 이렇게 최소한 이것까진 확정돼야한다.
                            // userInputParameter[2] aaa=aaa...
                            // userInputParameterSplit.get(0) postId   여기부터
                            // userInputParameterSplit.get(1) 무엇
                            // userInputParameterSplit.get(2) boardId
                            // userInputParameterSplit.get(3) 무엇      최소한 이것까진 확정돼야한다.
                            // userInputParameterSplit.get(4) aaa
                            // userInputParameterSplit.get(5) 무엇
                            // ...

                            try{
                            if (parameterNames.get(0).equals("postId") && parameterNames.get(1).equals("boardId")) {
                                // userInputParameterSplit.get(0) postId 이고
                                // userInputParameterSplit.get(2) boardId 이면 진입가능하다. 즉 /posts/edit?postId=무엇&boardId=무엇   이면 진입한다. 따라서
                                // parameterNames.get(0) postId
                                // parameterNames.get(1) boardId 만 진입.

                                boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들을 체크하기위함.
                                boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                                for (int i = 1; i < parameterNames.size(); i++) { // 파라미터이름 검사진입. parameterNames.size() 는 최소한 2이다. (postId 와 boardId)

                                    if (!(parameterNames.get(i).equals("boardId"))) {
                                        okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 2번째인덱스부터(번호1부터) 1개라도 "boardId" 가 아니라면 false.
                                        //만약 전부 "boardId" 가 맞다면 true.
                                        break;
                                    }
                                }

                                if (okCheck1) { // 유저URL 입력이 =>  /posts/edit?postId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함.

                                    String userPostIdValueString;
                                    String userBoardIdValueString;

                                    Integer userPostIdValueInteger;
                                    Integer userBoardIdValueInteger;

                                    userPostIdValueString = userInputParameterSplit.get(1); // 이렇게하면  /posts/edit?postId=aaa 에서 aaa값이 userPostIdValueString 에 저장.
                                    userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                    // 이렇게하면  /posts/remove?postId=aaa&boardId=bbb... 라는 입력값 중에서,
                                    // 맨마지막에있는 value만 userBoardIdValueString 에 담는다.
                                    // 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용한다는 규칙을 구현하고싶었다.

                                    try { // userPostIdValueString과 userBoardIdValueString 이 숫자여야한다.
                                        userPostIdValueInteger = Integer.parseInt(userPostIdValueString);
                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                        okCheck2 = true;

                                    } //오류안나면 true
                                    // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                    // 우선유저가 /posts/edit?postId=1&boardId=1 이런식으로 게시판의 순서를 입력하길원함.
                                    // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 유저가 입력한 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 수정모드로 진입할수있는것이다.
                                    catch (NumberFormatException e) {
                                        //맨밑쯤 출력처리함.
                                    }

                                    if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.
                                        userPostIdValueInteger = Integer.parseInt(userPostIdValueString);

                                        if (userBoardIdValueInteger > 0 && userPostIdValueInteger > 0 &&
                                                userBoardIdValueInteger <= mapKeyStorage.size() &&
                                                userPostIdValueInteger <= mapKeyStorage.get(userBoardIdValueInteger - 1).size()) {
                                            // 드디어 여기서  /posts/edit?postId=1&boardId=1 같은 형식이 진입.
                                            // 그리고 예를들어 /posts/edit?postId=1&boardId=15&...boardId=12 이런 형식이면  맨 마지막 boardId의 값인 12로 활용됨.
                                            // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이고,
                                            // 입력 게시글 번호가 실제 생성되어있는 번호면 진입한것이다.

                                            // 그러면 이제 해당 게시판의 해당 게시글 수정이다.

                                            // userBoardIdValueInteger-1  = 해당 게시판의 실제 인덱스번호
                                            // userPostIdValueInteger-1  = 해당 게시글의 실제 인덱스번호

                                            String title = boardKeyStorage.get(userBoardIdValueInteger - 1); // 해당 게시판의 제목키
                                            String articleKey = mapKeyStorage.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1); //해당 게시판의 기존 게시글의 제목키
                                            String changeTitle; //바꿀 게시글 제목
                                            String changeContents; //바꿀 게시글 내용

                                            System.out.printf("[%s]의 [%d]번글을 수정합니다.", title, userPostIdValueInteger);
                                            System.out.println();
                                            System.out.print("바꿀 글제목 :");
                                            changeTitle = sc.nextLine();
                                            System.out.print("바꿀 글내용 :");
                                            changeContents = sc.nextLine();
                                            System.out.println();

                                            mapStorage.get(title).remove(articleKey); // 맵의 해당맵에서 기존 게시글(제목,내용)을 삭제.
                                            mapKeyStorage.get(userBoardIdValueInteger - 1).remove(userPostIdValueInteger - 1); // 게시글 제목 저장소에서 해당게시글의 제목 삭제.
                                            // 밑은 하면안된다. 기존 작성일 보존필요.
                                            // originalLocalDate.get(userBoardIdValueInteger-1).remove(userPostIdValueInteger-1); // 시간저장소에서 해당 게시글 original시간도 삭제.
                                            //
                                            mapStorage.get(title).put(changeTitle, changeContents); // 맵의 해당맵에 바꾼 게시글(제목,내용)을 새로 저장.
                                            mapKeyStorage.get(userBoardIdValueInteger - 1).add(userPostIdValueInteger - 1, changeTitle); // 해당게시판 제목 저장소에서 기존게시글 삭제한 자리에 새 제목 저장.

                                            editLocalDate.get(userBoardIdValueInteger - 1).remove(userPostIdValueInteger - 1);
                                            editLocalDate.get(userBoardIdValueInteger - 1).add(userPostIdValueInteger - 1, LocalDate.now());
                                            // 해당 게시글 번호의 현재 수정일 값이 있을텐데, 그 자리를 삭제한뒤 다시 그자리에 LocalDate.now() 를 넣어줌으로써 수정일을 갱신한다.

                                            System.out.printf("[%s]의 [%d]번글이 수정되었습니다.", title, userPostIdValueInteger);
                                            System.out.println();
                                            elseCheck1 = true;

                                        }

                                    }

                                }

                            }} catch(IndexOutOfBoundsException e){
                                //맨밑 출력처리.
                            }

                        }

                    } else if (userInputPath[1].equals("posts") &&
                            userInputCrud[0].equals("view") && userInputCrud.length == 2) {

                        //   해당 게시판의 게시글뷰 진입시도.
                        //   지금까지 테스트해본결과로는 /posts/view?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/view?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                           //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/view?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/view?postId=1&boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 뷰모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.


                            // 예시 입력값 => /posts/view?postId=1&boardId=1 ... 이라고치자. 그러면
                            // userInputParameter[0] postId=무엇         여기부터
                            // userInputParameter[1] boardId=무엇 이다. 이렇게 최소한 이것까진 확정돼야한다.
                            // userInputParameter[2] aaa=aaa...
                            // userInputParameterSplit.get(0) postId   여기부터
                            // userInputParameterSplit.get(1) 무엇
                            // userInputParameterSplit.get(2) boardId
                            // userInputParameterSplit.get(3) 무엇      최소한 이것까진 확정돼야한다.
                            // userInputParameterSplit.get(4) aaa
                            // userInputParameterSplit.get(5) 무엇
                            // ...

                            try{
                            if (parameterNames.get(0).equals("postId") && parameterNames.get(1).equals("boardId")) {
                                // userInputParameterSplit.get(0) postId 이고
                                // userInputParameterSplit.get(2) boardId 이면 진입가능하다. 즉 /posts/view?postId=무엇&boardId=무엇   이면 진입한다. 따라서
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

                                if (okCheck1) { // 유저URL 입력이 =>  /posts/view?postId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함.

                                    String userPostIdValueString;
                                    String userBoardIdValueString;

                                    Integer userPostIdValueInteger;
                                    Integer userBoardIdValueInteger;

                                    userPostIdValueString = userInputParameterSplit.get(1); // 이렇게하면  /posts/view?postId=aaa 에서 aaa값이 userPostIdValueString 에 저장.
                                    userBoardIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                    // 이렇게하면  /posts/view?postId=aaa&boardId=bbb... 라는 입력값 중에서,
                                    // 맨마지막에있는 value만 userBoardIdValueString 에 담는다.
                                    // 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용한다는 규칙을 구현하고싶었다.

                                    try { // userPostIdValueString과 userBoardIdValueString 이 숫자여야한다.
                                        userPostIdValueInteger = Integer.parseInt(userPostIdValueString);
                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString);
                                        okCheck2 = true;

                                    } //오류안나면 true
                                    // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                    // 우선유저가 /posts/view?postId=1&boardId=1 이런식으로 게시판의 순서를 입력하길원함.
                                    // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 유저가 입력한 게시판의 번호인것이고, 존재한다면 해당 게시판의 해당 게시글 뷰모드로 진입할수있는것이다.
                                    catch (NumberFormatException e) {
                                        //맨밑쯤 출력처리함.
                                    }

                                    if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                        userBoardIdValueInteger = Integer.parseInt(userBoardIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.
                                        userPostIdValueInteger = Integer.parseInt(userPostIdValueString);

                                        if (userBoardIdValueInteger > 0 && userPostIdValueInteger > 0 &&
                                                userBoardIdValueInteger <= mapKeyStorage.size() &&
                                                userPostIdValueInteger <= mapKeyStorage.get(userBoardIdValueInteger - 1).size()) {
                                            // 드디어 여기서  /posts/view?postId=1&boardId=1 같은 형식이 진입.
                                            // 그리고 예를들어 /posts/view?postId=1&boardId=15&...boardId=12 이런 형식이면  맨 마지막 boardId의 값인 12로 활용됨.
                                            // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 게시판번호가 실제 생성되어있는 게시판 번호이고,
                                            // 입력 게시글 번호가 실제 생성되어있는 번호면 진입한것이다.

                                            // 그러면 이제 해당 게시판의 해당 게시글 뷰모드다.

                                            // userBoardIdValueInteger-1  = 해당 게시판의 실제 인덱스번호
                                            // userPostIdValueInteger-1  = 해당 게시글의 실제 인덱스번호

                                            String title = boardKeyStorage.get(userBoardIdValueInteger - 1); // 해당 게시판의 제목키
                                            String articleKey = mapKeyStorage.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1); //해당 게시판의 해당 게시글의 제목키
                                            String articleContents = mapStorage.get(title).get(articleKey);

                                            System.out.printf("[%s]",title); // 해당 게시판도 출력
                                            System.out.println();
                                            System.out.printf("[%d]번 게시글", userPostIdValueInteger);
                                            System.out.println();
                                            System.out.println("작성자 : " + postWriter.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1));
                                            System.out.println("작성일 : " + originalLocalDate.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1));

                                            if (editLocalDate.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1) != null) { //수정일이 null이 아니라면.

                                                System.out.println("수정일 : " + editLocalDate.get(userBoardIdValueInteger - 1).get(userPostIdValueInteger - 1));
                                            } else {

                                                System.out.println("수정일 : X");

                                            }
                                            System.out.println("제목 : " + articleKey);
                                            System.out.println("내용 : " + articleContents);
                                            System.out.println();

                                            elseCheck1 = true;

                                        }

                                    }

                                }

                            }} catch(IndexOutOfBoundsException e){
                                //맨밑 출력처리.
                            }

                        }

                    } else if (userInputPath[1].equals("accounts") && userInputPath[2].equals("signup")) { //   회원 등록 진입. 입력이 /accounts/signup   일경우에만 진입할것이다.
                        // 원래는 맨마지막에 /를 더 추가해도 진입이 가능해서 문제였지만 맨 윗줄에서 userInputPath = userInput.split("/",3); 처럼
                        // limit을 3 설정하면서 뒤의 덩어리자체를 저장시켰기에 필터링이 가능해졌다.

                        boolean existingName = false;
                        System.out.print("생성할 계정 이름을 입력해주세요 :");
                        userInput = sc.nextLine();

                        for(int i=0; i < userAccount.size(); i++){ // 이름이 기존게시판과 같은지 체크하기위한 for문.

                            if( userInput.equals(userAccount.get(i)) ) {   // 예외처리는 안해도된다. 사이즈0이면 for자체를실행안하니까.
                                existingName = true; //생성에 진입못하게만듬.

                                System.out.println();
                                System.out.println("이미 존재하는 계정입니다.");

                                elseCheck1 = true; // 맨밑 유효하지않은 URL 이라는 출력을 막기위함.
                                break;
                            }

                        }

                        if(!existingName){

                            userAccount.add(userInput);

                            System.out.print("계정 비밀번호를 입력해주세요 :");
                            userInput = sc.nextLine();
                            userPassword.add(userInput);

                            System.out.print("회원님의 이름을 입력해주세요 :");
                            userInput = sc.nextLine();
                            userName.add(userInput);

                            System.out.print("닉네임을 입력해주세요 :");
                            userInput = sc.nextLine();
                            userNickname.add(userInput);

                            System.out.print("이메일을 입력해주세요 :");
                            userInput = sc.nextLine();
                            userEmail.add(userInput);

                            userSignUpDay.add(LocalDate.now()); // 유저 가입일 저장소에 가입일 저장.
                            userEditDay.add(null); // 유저 계정 수정일 저장소에 null로 저장. 인덱스를 맞추기위해서.

                            System.out.println();
                            System.out.printf("[%s] 계정이 생성되었습니다. 회원번호 :[%d]번", userAccount.get(userAccount.size()-1) , userAccount.size());
                            // 방금 계정을 저장했다면 userAccount의 사이즈가 곧 그 계정 번호다.
                            System.out.println();

                            elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.
                        }


                    } else if (userInputPath[1].equals("accounts") && userInputPath[2].equals("signin")) { //  로그인 진입. 입력이 /accounts/signin   일경우에만 진입할것이다.
                        // 원래는 맨마지막에 /를 더 추가해도 진입이 가능해서 문제였지만 맨 윗줄에서 userInputPath = userInput.split("/",3); 처럼
                        // limit을 3 설정하면서 뒤의 덩어리자체를 저장시켰기에 필터링이 가능해졌다.

                        if(!loginCheck1) { //로그인 안된 상태에서만 진입.
                            // 현재까지 loginCheck1 을 true로 만들어주는 시점은 signin 밖에없음.
                            // 현재까지 loginCheck1 이 true인걸 false로 만들어주는 시점은 signout밖에없음.

                            // 이 if문 안에 들어오면 예외처리는 끝. 해놨음.
                            //아니다. 아무 계정없는상태에서 로그인하는것도 막아야한다.

                            boolean existingAccount = false;

                            String inputAccount;
                            String inputPassword;
                            Integer userAccountIndex = 0; //초기화안해두면 if조건문에서 오류나서 해줌.

                            System.out.print("로그인할 계정을 입력해주세요 :");
                            inputAccount = sc.nextLine();

                            if( userAccount.size() == 0 ){ //  로그인 명령어후, 계정입력후 만약 가입되어있는 계정자체가 아예 없는 상태라면 여기 진입.
                                // 현재까진 여기진입시 else 무시하므로 여기서 출력 처리해주면됨.

                                System.out.println();
                                System.out.println("존재하지 않는 계정입니다.");
                                elseCheck1 = true;

                            }

                            for(int i=0; i < userAccount.size(); i++){ // 입력 계정이 존재 계정인지 체크하기위한 for문.

                                if( inputAccount.equals(userAccount.get(i)) ) {// 예외처리는 안해도된다. 사이즈0이면 for자체를실행안하니까.
                                    existingAccount = true; // 로그인 조건하나 충족.
                                    userAccountIndex = i; // 해당 계정이 계정보관소에서 몇번인덱스인지 알기위함. 실제 인덱스번호임.
                                    break;
                                }

                                if( i == userAccount.size()-1 ){ //여기 온다는것은 존재하지 않은 계정이라는것이다.

                                    System.out.println();
                                    System.out.println("존재하지 않는 계정입니다.");
                                    elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.

                                }
                            }

                            if(existingAccount){ // 입력 계정명이 실존하면 진입.

                                System.out.print("해당 계정의 비밀번호를 입력해주세요 :");
                                inputPassword = sc.nextLine();

                                if( inputPassword.equals(userPassword.get(userAccountIndex)) ){ // 해당 계정의 패스워드가 맞으면 진입. 계정보관소 인덱스와 패스워드 보관소의 인덱스가 같은걸 활용.

                                    loginCheck1 = true; //로그인 최종성공. 변수가 최상위 스코프에 있기때문에 다음턴에도 로그인 유지되고있음.
                                    loginingAccount = inputAccount; // 로그인한 계정을 최상위 스코프에 담아둬서 활용할수있도록함.
                                    elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.

                                    System.out.println();
                                    System.out.printf("[%s] 계정으로 로그인 되었습니다. 회원번호:[%d]번", inputAccount, userAccountIndex+1 ); // 유저는 0부터세지않고 1부터세기때문.
                                    System.out.println();

                                } else {
                                    System.out.println();
                                    System.out.println("올바른 비밀번호가 아닙니다.");
                                    elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.
                                }

                            }

                        } else { // 로그인 되어있는 상태에서 로그인 명령 입력시에만 여기 진입.

                            try{
                                throw new RuntimeException("이미 로그인 되어있습니다.");
                            } catch (RuntimeException e) {

                                System.out.println(e.getMessage());
                                elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.
                            }
                        }


                    } else if (userInputPath[1].equals("accounts") && userInputPath[2].equals("signout")) { //  로그아웃 진입. 입력이 /accounts/signout 일경우에만 진입할것이다.
                        // 원래는 맨마지막에 /를 더 추가해도 진입이 가능해서 문제였지만 맨 윗줄에서 userInputPath = userInput.split("/",3); 처럼
                        // limit을 3 설정하면서 뒤의 덩어리자체를 저장시켰기에 필터링이 가능해졌다.

                        if(loginCheck1){ // 이 시점에 계정 로그인이 되어있는 상태일때만 진입가능.

                            loginCheck1 = false; // 최상위스코프에서 로그아웃 상태로 바꾼다.
                            System.out.printf("[%s] 계정에서 로그아웃 되었습니다.", loginingAccount);
                            loginingAccount = null; // 로그아웃했으니 초기값 null로 재할당.
                            System.out.println();

                            elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.

                        } else { // 로그인이 안되어있는 상태라면 진입하고 예외처리.

                            try {
                                throw new RuntimeException("이미 로그아웃 상태입니다.");
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                                elseCheck1 = true; // 유효하지앟은 URL 출력처리를 막기위함.
                            }

                        }

                    } else if (userInputPath[1].equals("accounts") &&
                            userInputCrud[0].equals("detail") && userInputCrud.length == 2) {  //   해당 게시판의 게시글작성 진입시도.

                        //   지금까지 테스트해본결과로는 /posts/add?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/add?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/add?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/add?boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 작성모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                            boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                if (!(parameterNames.get(i).equals("accountId"))) { // 파라미터이름들을 검사하도록 진입.
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                    //만약 전부 "boardId" 가 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>   /posts/add?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                String accountIdValueString;
                                Integer accountIdValueInteger;

                                accountIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                try {
                                    accountIdValueInteger = Integer.parseInt(accountIdValueString);
                                    okCheck2 = true;
                                } //오류안나면 true
                                // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                // 우선유저가  /posts/add?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 작성모드로 진입할수있는것이다.
                                catch (NumberFormatException e) {
                                    //맨밑쯤 출력처리.
                                }

                                if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                    accountIdValueInteger = Integer.parseInt(accountIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                    if (accountIdValueInteger > 0 && accountIdValueInteger <= userAccount.size()) { // 드디어 진입. /accounts/detail?accountId=1 같은 형식과 같음.
                                        //  /accounts/detail?accountId=1&accountId=7... 형식이면 맨 마지막 7로 활용됨.
                                        // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 계정번호가 실제 생성되어있는 계정번호이면 진입.
                                        // 게시판이 생성되면 mapKeyStorage.size()가 1씩늘어나게되어있기때문이다.


                                        System.out.printf("[%d]번 회원", accountIdValueInteger);
                                        System.out.println();
                                        System.out.println("계정 :"  +  userAccount.get(accountIdValueInteger-1) );
                                        System.out.println("이메일 :" + userEmail.get(accountIdValueInteger-1) );
                                        System.out.println("가입일 :" + userSignUpDay.get(accountIdValueInteger-1) );
                                        System.out.println();
                                        elseCheck1 = true;

                                    }
                                }
                            }
                        }
                    } else if (userInputPath[1].equals("accounts") &&
                            userInputCrud[0].equals("edit") && userInputCrud.length == 2) {  //   해당 게시판의 게시글작성 진입시도.

                        //   지금까지 테스트해본결과로는 /posts/add?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/add?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/add?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/add?boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 작성모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                            boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                if (!(parameterNames.get(i).equals("accountId"))) { // 파라미터이름들을 검사하도록 진입.
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                    //만약 전부 "boardId" 가 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>   /posts/add?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                String accountIdValueString;
                                Integer accountIdValueInteger;

                                accountIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                try {
                                    accountIdValueInteger = Integer.parseInt(accountIdValueString);
                                    okCheck2 = true;
                                } //오류안나면 true
                                // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                // 우선유저가  /posts/add?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 작성모드로 진입할수있는것이다.
                                catch (NumberFormatException e) {
                                    //맨밑쯤 출력처리.
                                }

                                if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                    accountIdValueInteger = Integer.parseInt(accountIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                    if (accountIdValueInteger > 0 && accountIdValueInteger <= userAccount.size()) { // 드디어 진입. /accounts/edit?accountId=1 같은 형식과 같음.
                                        //  /accounts/edit?accountId=1&accountId=7... 형식이면 맨 마지막 7로 활용됨.
                                        // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 계정번호가 실제 생성되어있는 계정번호이면 진입.

                                        String thisAccount = userAccount.get(accountIdValueInteger-1); // 해당 계정이름.
                                        String newPassword;
                                        String newEmail;

                                        System.out.printf("[%s] 계정 정보를 수정합니다.", thisAccount);
                                        System.out.println();

                                        System.out.print("바꿀 비밀번호를 입력해주세요 :");
                                        newPassword = sc.nextLine();

                                        System.out.println("바꿀 이메일을 입력해주세요 :");
                                        newEmail = sc.nextLine();

                                        userPassword.remove(accountIdValueInteger-1); //해당회원번호의 비밀번호 저장소에서 기존 값을 삭제
                                        userEmail.remove(accountIdValueInteger-1); // 해당회원번호의 이메일 저장소에서 기존 값을 삭제
                                        userEditDay.remove(accountIdValueInteger-1); // 해당회원번호의 수정일 저장소에 기존 null값을 삭제

                                        userPassword.add(accountIdValueInteger-1,newPassword); //다시 그곳에 비밀번호 저장
                                        userEmail.add(accountIdValueInteger-1,newEmail); //다시 그곳에 이메일 저장
                                        userEditDay.add(accountIdValueInteger-1, LocalDate.now()); //다시 그곳에 수정일 저장

                                        System.out.printf("[%s] 계정 정보가 수정되었습니다.", thisAccount);
                                        System.out.println();
                                        elseCheck1 = true; //출력 처리

                                    }
                                }
                            }
                        }

                    } else if (userInputPath[1].equals("accounts") &&
                            userInputCrud[0].equals("remove") && userInputCrud.length == 2) {  //   해당 게시판의 게시글작성 진입시도.

                        //   지금까지 테스트해본결과로는 /posts/add?abc 여기까지는 확정되어야 진입가능하다. 물론 /posts/add?abc? 같은 입력도 들어와지긴한다. 그 이후의 값들은 이 밑에서부터 필터링해야겠다.
                        //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거기때문에 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

                        int splitLimit = userInputCrud[1].split("&").length;
                        userInputParameter = userInputCrud[1].split("&",splitLimit);

                        try { // 만약입력값이 /boards/edit?aaa&bbb=bbb&ccc 같다면 for문 내에서 예외가 발생한다.
                            for (int i = 0; i < userInputParameter.length; i++) { // 필터링전의 필요한 값을 갖기위한 반복문.
                                //userInputParameter.length() 는 최소 1이상 일수밖에없다. 이전의 조건때문이다.
                                //따라서 for에 도달하면 무조건 한번은 실행된다.
                                // userInputParameter[0] 은 ?뒤부터 & 이전까지.

                                temporaryParameterSplit = userInputParameter[i].split("=",2); // limit 2를 준이유는 abc=abc= 처럼 맨끝에 =를붙이면 필터하기위해.
                                userInputParameterSplit.add(temporaryParameterSplit[0]);
                                userInputParameterSplit.add(temporaryParameterSplit[1]); // 만약 ?aaa&bbb=bbb&ccc=ccc 라면 여기서 예외가 발생.
                                // 테스트결과 매 반복마다 userInputParameterSplit은 새로운 배열로 덮어씌워지기때문에 따로 저장해놓을 링크드리스트에 저장.

                                //정상 입력이라면
                                //userInputParameterSplit.get(0) => boardId 부분
                                //userInputParameterSplit.get(1) => 1 부분
                                //          ...

                            } //   어떤 값이 왔더라도 userInputParameterSplit.size()는 무조건 2이상일것이다.
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //맨밑쯤 출력처리함.
                        }

                        //이제 위에서 발견한, 원하는 입력값들의 공통점을 활용해서 아래 if문으로 더 필터링한다.
                        if ((userInputParameter.length) * 2 == (userInputParameterSplit.size())) { //여기 진입하는것은 /posts/add?abc=aaa&bbb=bbb... 이런식으로 입력 받은것들이다.
                            //만약 맨끝이 &이어도 여길 통과한다. 그러나 위쪽 int splitLimit 변수로 맨마지막 &를 필터한뒤에 더 아래쪽에서 필터링하게끔 만들었다.
                            //그리고 이 시점부턴 userInputParameterSplit 에 드디어 파라미터값과, =뒤의 값만 따로따로 순차적으로 존재하게됐다.
                            //원하는것: /posts/add?boardId=1...   => 처럼 입력받으면 해당 게시판에서 게시글 작성모드로 진입하게끔하고싶은것이다.
                            // 분석중에 또다른 공통점 발견: 여기 진입한 입력값의 파라미터의 이름값들은 전부 split의 짝수에 저장됨.(get(0)을 포함)  ex) get(0),get(2),get(4) ....

                            LinkedList<String> parameterNames = new LinkedList<>();

                            for (int i = 0; i < userInputParameterSplit.size(); i += 2) { //파라미터 이름이 저장되는 parameterNames 에 짝수만 뽑아 저장한다.
                                // 이 위 조건문들에따라 userInputParameterSplit.size() 는 무조건 2이상일것이다.

                                parameterNames.add(userInputParameterSplit.get(i));

                            } // 이제 userInputParameterSplit 에서 파라미터 이름들만 다 뽑아서 parameterNames 라는 링크드리스트에 저장끝.

                            boolean okCheck1 = true; // parameterNames 링크드리스트에 저장한 파라미터네임들이 전부 "boardId" 인지 확인하기위해서 만듬.
                            boolean okCheck2 = false; // 파라미터 value들이 숫자인지(?번 게시판) 확인하기위해서 만듬.

                            for (int i = 0; i < parameterNames.size(); i++) { // parameterNames.size() 는 최소한 1이다. 최소 aaa라는 파라미터네임1개.

                                if (!(parameterNames.get(i).equals("accountId"))) { // 파라미터이름들을 검사하도록 진입.
                                    okCheck1 = false; // parameterNames 링크드리스트에 저장한 파라미터네임들중에 1개라도 "boardId" 가 아니라면 false.
                                    //만약 전부 "boardId" 가 맞다면 true.
                                    break;
                                }
                            }

                            if (okCheck1) { // 유저URL 입력이 =>   /posts/add?boardId=aaa&boardId=bbb ... 방식과 같은 입력만 여기에 진입함. &가 없어도됨.

                                String accountIdValueString;
                                Integer accountIdValueInteger;

                                accountIdValueString = userInputParameterSplit.get(userInputParameterSplit.size() - 1);
                                // 이렇게하면 맨마지막 boardId의 value만 가져올수있다. 입력 URL 파라미터에, 같은 이름의 파라미터가 여러개있을때 맨 마지막 값만 활용하고싶었다.

                                try {
                                    accountIdValueInteger = Integer.parseInt(accountIdValueString);
                                    okCheck2 = true;
                                } //오류안나면 true
                                // 유저가 밸류에 숫자입력을 안했으면 오류가능성 있음. 나중에 예외 관리하기.
                                // 우선유저가  /posts/add?boardId=1  이런식으로 게시판의 순서를 입력하길원함.
                                // 그럼 이제 userBoardIdValueInteger 는 뭐냐면, 게시판의 번호인것이고, 존재한다면 해당 게시판의 게시글 작성모드로 진입할수있는것이다.
                                catch (NumberFormatException e) {
                                    //맨밑쯤 출력처리.
                                }

                                if (okCheck2) { //오류가 안나야 true. try블록안에서 오류코드 다음의 코드는 진행이 안되는것을 활용.

                                    accountIdValueInteger = Integer.parseInt(accountIdValueString); //다시 적은이유 => 이걸안하면 if문안의 변수가 초기화안됐다며 오류가뜸.

                                    if (accountIdValueInteger > 0 && accountIdValueInteger <= userAccount.size()) { // 드디어 진입. /accounts/edit?accountId=1 같은 형식과 같음.
                                        //  /accounts/edit?accountId=1&accountId=7... 형식이면 맨 마지막 7로 활용됨.
                                        // 입력 value값이 숫자이고, 그것이 0이 아니고, 입력 계정번호가 실제 생성되어있는 계정번호이면 진입.

                                        String thisAccount = userAccount.get(accountIdValueInteger-1); // 해당 계정이름.

                                        if(loginCheck1) { //로그인 되어있는 상태에서 remove 진입하면 여기에 진입.

                                            loginCheck1 = false; // 최상위 스코프에서 로그아웃처리.
                                            loginingAccount = null; // 로그아웃처리했으니 이 변수도 재할당.

                                            System.out.println("[" + thisAccount + "] 계정이 로그아웃 되었습니다.");
                                        }

                                            userAccount.remove(accountIdValueInteger-1); // 해당회원번호의 계정 저장소에서 기존 값을 삭제
                                            userPassword.remove(accountIdValueInteger-1); //해당회원번호의 비밀번호 저장소에서 기존 값을 삭제
                                            userEmail.remove(accountIdValueInteger-1); // 해당회원번호의 이메일 저장소에서 기존 값을 삭제
                                            userName.remove(accountIdValueInteger-1); // 해당회원번호의 이름 저장소에서 기존 값을 삭제
                                            userNickname.remove(accountIdValueInteger-1); // 해당회원번호의 닉네임 저장소에서 기존 값을 삭제
                                            userSignUpDay.remove(accountIdValueInteger-1); // 해당회원번호의 가입일 저장소에서 기존 값을 삭제
                                            userEditDay.remove(accountIdValueInteger-1); // 해당회원번호의 수정일 저장소에 기존 null값을 삭제

                                            System.out.printf("[%s] 계정이 삭제되었습니다.", thisAccount); // 스트링 객체를 참조하고있으니 널포인트가 아님.
                                            System.out.println();

                                            elseCheck1 = true; //출력 처리

                                    }
                                }
                            }
                        }

                    }












                    else{ // 이 else엔 각 URL 맨처음 유효성 검사들에 진입하지 않은 입력만 올수있고 여기 진입한경우는 밑의 else를 무시함.
                            // 만약 URL 맨처음 유효성 검사들에 한번이라도 진입한 이후라면 여기에 진입하지못함.
                        System.out.println("유효하지 않은 URL 입니다.");
                        elseCheck1 = true;
                    }

                } else { // 1. URL 입력후 나오는 가장 처음의 if단계에 진입하지않으면 무조건 이 else로 진입함.
                    // 2. 가장 처음의 if단계에 들어가기만하면 여기는 무조건 진입하지못함.
                    System.out.println("유효하지 않은 URL 입니다.");
                    elseCheck1 = true; // 여기서 ture 를 안해주면 첫단게 if 진입전 오류가나서 내려온경우엔 메시지를 2번 출력하게된다.
                }
                if(!elseCheck1){ // 이걸안하면 정상입력인 경우에도 밑내용이 출력돼서 elseCheck1이 false일때만 통과하게 만들었다.
                    System.out.println("유효하지 않은 URL 입니다.");

                } // 입력이 "종료"가 아닌 경우엔 무조건 이 공간에 도달한다.

                userInput = "시작"; // 안에서 혹시나 userInput이 "종료"라고 할당되어있는 경우, 종료하지않기 위해서 "시작"으로 다시 재할당해준다.

            }
            // 입력이 "종료" 일때만 바로 이 공간에 진입.

        } while (!userInput.equals("종료"));

        System.out.println("프로그램이 종료됩니다.");
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

