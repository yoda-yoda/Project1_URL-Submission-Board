import javax.print.DocFlavor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;



public class qwer {

    public static void main(String[] args) {


        Map<String, Map<Integer, LocalDate>> editLocalDate = new HashMap<>(); // 수정된 작성일을 인덱스와 연결해서 저장하고싶었다.

        editLocalDate.put("자유게시판", new HashMap<>());
        editLocalDate.


        /*String[] aaa;
        String bbb = "짱짱짱=짱짱짱=짱짱짱";
        String ccc = "굿굿굿=굿굿굿";

        aaa = bbb.split("=");
        System.out.println(aaa);

        aaa=ccc.split("=");
        System.out.println(aaa);

        System.out.println(aaa[0]);*/


        /*Map<String, Map<Integer, LocalDate>> editLocalDate = new HashMap<>();

        editLocalDate.put("자유게시판", new HashMap<>());
        editLocalDate.get("자유게시판").put(0,LocalDate.now()); //수정일이 자유게시판맵 속의 0번인덱스에 연결돼서 저장.
        System.out.println( editLocalDate.get("자유게시판").get(0));
*/


        /*Scanner sc = new Scanner(System.in);
        String userInput;
        String[] userInputPath;// userInput을 split 한 경로 문자열 저장용.
        String[] userInputCrud; // split한 경로 문자열에서 파라미터부분을 따로 또 분리해서 그것을 저장할 용도.
        String[] userInputParameter;
        String[] userInputParameterSplit = {"a"}; // 나중에 if조건문 활용하려하는데 초기화가 안되어있다는 오류가자꾸떠서 적어주었다.


            System.out.print("손님 ");
            userInput = sc.nextLine();
            System.out.println();
            userInputPath = null;
            userInputCrud = null;
            userInputParameter = null;
            userInputParameterSplit = null;
            // 기능이 한번끝나서 다시 다음에 명령어에 재사용하기위해 null로 안의 값들을 삭제.

                userInputPath = userInput.split("/"); // 유저의 입력중에 "/"를 기준으로 나눠서 저장함.
                // 예시 입력값=> /boards/add?parameter=10&b=value...
                // userInputPath[0] => 빈 부분
                // userInputPath[1] => boards 부분
                // userInputPath[2] => add?parameter=10&b=value... 부분

                userInputCrud = userInputPath[2].split("\\?"); // 맨처음 입력을 형식대로 입력했으면 2번인덱스(3번째배열)에는 add?parameter=10&b=value... 이런식으로 저장되어있을것이다.
                //따라서 그것을 한번더 ? 를 기준으로 나눠서 저장한다.
                // userInputCrud[0] => add 부분
                // userInputCrud[1] => parameter=10&b=value... 부분

        if ( userInputPath[0].equals("") && userInputPath[1].equals("boards") &&
                userInputCrud[0].equals("edit") && userInputCrud.length == 2 ) {    //   게시판 수정 진입시도.
            //   지금까지 테스트해본결과로는 /boards/edit?abc 까지는 확정되어야 진입가능하다.
            //  userInputCrud.length == 2 가 true 라는건 ?가 무조건 있는거다. 이걸활용해 예외없이 원하는 입력을 받을수있을것같다.

            
            // /boards/edit => 안댐
            // /boards/edit? =>안댐.
            // /boards/edit?? => 안댐
            // /boards/edit?abc => 됨
            // /boards/edit??abc => 안됨.
            // /boards/edit?asd? => 됨
            // /boards/edit?abc=1 => 됨

            System.out.println("굿");
        }*/













        /*Map<String, Map<String, String>> MapStorage = new HashMap<>();
        LinkedList<LinkedList<String>> MapKeyStorage = new LinkedList<>();
        LinkedList<String> boardKeyStorage = new LinkedList<>();



        MapStorage.put("자유게시판", new HashMap<>());
        MapStorage.put("정보게시판", MapStorage.get("자유게시판"));
        MapStorage.remove("자유게시판");


        MapStorage.get("정보게시판").put("제목1","내용1");
        System.out.println(MapStorage.get("정보게시판"));*/




/*
        LinkedList<LinkedList<String>> MapKeyStorage = new LinkedList<>();
        System.out.println(MapKeyStorage.size());;
        MapKeyStorage.add(new LinkedList<>());
        System.out.println(MapKeyStorage.size());;
        MapKeyStorage.remove(0);
        System.out.println(MapKeyStorage.size());;      */

        /*LinkedList<LinkedList<String>> MapKeyStorage = new LinkedList<>();
        MapKeyStorage.add(new LinkedList<>());
        MapKeyStorage.get(0).add("짱짱");
        System.out.println(MapKeyStorage.get(0).get(0));*/

        /*LinkedList<String> aaa = new LinkedList<>();
        aaa.add("1입니다");*/

        /*LinkedList<Map<String, String>> MapStorage = new LinkedList<>(); //
        MapStorage.add(new HashMap<>());
        MapStorage.get(0).put("제목1","내용1");
        System.out.println(MapStorage.get(0).get("제목1"));*/


        /*Scanner sc = new Scanner(System.in);
        String userInput;
        String[] userInputPath;// userInput을 split 한 경로 문자열 저장용.
        String[] userInputCrud; // split한 경로 문자열에서 파라미터부분을 따로 또 분리해서 그것을 저장할 용도.
        String[] userInputParameter;
        String[] userInputParameterSplit;

        userInput = sc.nextLine();
        userInputPath = userInput.split("="); // 유저의 입력중에 "/"를 기준으로 나눠서 저장함.
        // 예시 입력값=> /boards/add?parameter=10&b=value...
        // userInputPath[0] => 빈 부분
        // userInputPath[1] => boards 부분
        // userInputPath[2] => add?parameter=10&b=value... 부분

        System.out.println(userInputPath.length);
        System.out.println(userInputPath[0]);
        System.out.println(userInputPath[1]);
        System.out.println(userInputPath[2]);*/







        /*Scanner sc = new Scanner(System.in);
        String userInput;
        userInput = sc.nextLine();
        LinkedList<String> keyIndexStorage = new LinkedList<>();
        keyIndexStorage.add("a");
        keyIndexStorage.add("b");
        keyIndexStorage.add("c");
        System.out.println(keyIndexStorage.get(0));
        System.out.println(keyIndexStorage.indexOf("a"));*/





        /*Integer.parseInt(userInput);
        System.out.println(userInput);*/


        /*int a = 1;
        String b = (a+"번");
        String c = (a+"번");
        boolean d = b == c;
        System.out.println("d = " + d);*/

       /* System.out.println(   b  );
        System.out.println(   (a+"번")   );*/



        /*if(userInput == (a+"번") && a>=1){
            System.out.println("축하햐");
        }
        else{
            System.out.println("비완벽적인 실행");
        }*/

        /*String[] userReadIndex;
        userInput = sc.nextLine();
        userReadIndex = userInput.split("번");
        System.out.println(userReadIndex[0]);
        System.out.println(userReadIndex[0].getClass()); //String
        System.out.println(userReadIndex[0].getClass().getName()); //
        System.out.println(userReadIndex[1]);
        Integer q = Integer.parseInt(userReadIndex[0]);
        System.out.println(q.getClass());
        System.out.println(q.getClass().getName());
        System.out.println(q);*/


        /*Map<String, String> storage = new HashMap<>();
        storage.put("제목1", "내용내용내용1");
        storage.put("제목2", "내용내용내용2");
        System.out.println(storage.get("제목1"));
        System.out.println(storage.get("제목2"));*/

       /*Stack<String> storage2 = new Stack<>(); // 여기 저장되면 제일 마지막에 들어간 값을 꺼낼수있음.
        storage2.push("aaa");
        storage2.push("bbb");
        storage2.push("ccc");
        System.out.println(storage2.peek());
        //storage2.pop();
       // System.out.println(storage2.peek()); //bbb
        System.out.println(storage2.indexOf("bbb"));
        System.out.println(storage2.indexOf("ccc"));
        storage2.remove("bbb");
        System.out.println(storage2.indexOf("ccc"));*/


        //System.out.println("테스트 :" + (1+2) +"=3");


       /*Deque<String> qq1 = new LinkedList<>();
        qq1.add("1");
        qq1.add("2");
        qq1.add("3");



        System.out.println(qq1.getFirst());;*/

        /*Map<String, String> boardStorage = new HashMap<>();
        boardStorage.put("A", "1");
        boardStorage.put("B", "2");
        boardStorage.put("C", "3");
        boardStorage.put("D", "4");
        boardStorage.put("E", "5");
        System.out.println(boardStorage.get("C"));; //3
        boardStorage.remove("E");
        System.out.println(boardStorage.get("E"));
        System.out.println(boardStorage.values());
        */

        /*LinkedList<String> qq1 = new LinkedList<>();

        qq1.add("a");
        qq1.add("b");
        qq1.add("c");
        qq1.add("d");
        qq1.add("e");
        System.out.println(qq1.get(2));*/

    }

    public void test1(){

    }

}