import java.util.*;

public class qwer2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;
        String[] userInputIndex; // split 한 문자열 저장용.

        Map<String, String> boardStorage = new HashMap<>();
        LinkedList<String> keyIndexStorage = new LinkedList<>(); // LinkgedList로 한 이유.
        // 원랜 Deque 로 만들려했는데, Deque는 인덱스번호만으로 값을가져오는 메서드가 없었음. key값을 직접 입력해야했음.


        do {
            System.out.print("명령어 > ");
            userInput = sc.nextLine();

            if (!userInput.equals("종료")) {

                if (userInput.equals("작성")) {

                    System.out.print("제목을 작성해주세요 :");
                    String key = sc.nextLine(); //굳이 userInput말고 다른 변수를 선언한 이유: 제목, 내용 둘다 한번에 저장하기위해서.
                    // 혹시나 제목, 내용이 전부 작성되지도않았는데 도중에 프로그램이 종료되면 엉키기때문에 한번에 처리하고싶다는 생각이 문득 들었다.
                    System.out.print("내용을 작성해주세요 :");
                    String value = sc.nextLine();
                    keyIndexStorage.add(key); //key와 index를 저장하기위한 영구저장 역할.
                    boardStorage.put(key, value); //Map에 제목(키), 내용(밸류) 로 저장.
                    System.out.println("게시물이 저장되었습니다."); //

                } else if (userInput.equals("조회")) {
                    System.out.print("어떤 게시물을 조회할까요? ");
                    userInput = sc.nextLine(); //사용자가 꼭 "1번" 처럼 입력해줘야함. 일단 다른방법못찾았음.
                    userInputIndex = userInput.split("번"); // split에서 숫자만 따오고싶기때문.
                    Integer userReadIndex = Integer.parseInt(userInputIndex[0]); // split된 String 숫자를 정수로 활용하고싶어서 정수로 바꿔줌.
                    System.out.println("글번호 :[" + userReadIndex + "번]");
                    System.out.println("제목 :[" + keyIndexStorage.get(userReadIndex-1) + "]");
                    System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(userReadIndex-1)) + "]");
                    

                } else if (userInput.equals("삭제")) {
                    System.out.print("어떤 게시물을 삭제할까요? ");
                    userInput = sc.nextLine();
                    userInputIndex = userInput.split("번"); // 숫자만 따오고싶기때문.
                    Integer userReadIndex = Integer.parseInt(userInputIndex[0]); // split된 String 숫자를 정수로 활용해야해서 정수로 바꿔줌.
                    boardStorage.remove(keyIndexStorage.get(userReadIndex-1));// 해당 인덱스의 해당 키값을 가진 데이터를 Map에서 제거.
                    keyIndexStorage.remove(userReadIndex-1); // 해당 인덱스의 해당 키값을 가진 데이터를 KeyIndex 에서도 제거.
                    //그러면 뒤의 인덱스의 키들은 앞자리로 하나씩 당겨짐.

                    System.out.printf("%d번 게시물이 성공적으로 삭제되었습니다!", userReadIndex);
                    System.out.println();

                } else if (userInput.equals("수정")) {
                    System.out.print("어떤 게시물을 수정할까요? ");
                    userInput = sc.nextLine();
                    userInputIndex = userInput.split("번"); // 숫자만 따오고싶기때문.
                    Integer userReadIndex = Integer.parseInt(userInputIndex[0]); // split된 String 숫자를 정수로 활용하고싶어서 정수로 바꿔줌.
                    System.out.printf("%d번 게시물을 수정합니다.", userReadIndex);
                    System.out.println();
                    //편의를 위해 기존 제목 표시 고려해보기.

                    System.out.print("바꿀 제목 :"); //굳이 userInput말고 다른 변수를 선언한 이유: 내용까지 수정했을때 제목, 내용 둘다 한번에 바꾸기위해서.
                    // 혹시나 제목, 내용이 전부 수정되지도않았는데 도중에 프로그램이 종료되면 엉키기때문에 한번에 처리하고싶다는 생각이 문득 들었다.
                    String changeKey = sc.nextLine();
                    System.out.print("바꿀 내용 :");
                    String changeValue = sc.nextLine();

                    boardStorage.remove(keyIndexStorage.get(userReadIndex-1)); // 수정전 게시글을 Map에서 영구삭제
                    keyIndexStorage.remove(userReadIndex-1); //보관돼있던 수정전 key도 영구삭제
                    keyIndexStorage.add(userReadIndex-1, changeKey); // 수정한 key 영구보관
                    boardStorage.put(keyIndexStorage.get(userReadIndex-1), changeValue); // 수정게시글을 Map에 영구저장
                    //편의를 위해 기존 내용 보여주기 고려.
                    System.out.printf("%d번 게시물이 성공적으로 수정되었습니다!", userReadIndex);
                    System.out.println();

                } else {
                    System.out.println("존재하지 않는 명령어 입니다.");
                }
            }

        } while (!userInput.equals("종료"));

        System.out.println("프로그램이 종료됩니다.");

    }
}
