import java.util.*;

public class Crud1 {

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
                    System.out.println();
                    System.out.println("게시물이 저장되었습니다.");

                } else if (userInput.equals("조회")) {

                    System.out.print("어떤 게시물을 조회할까요? ");
                    userInput = sc.nextLine(); //사용자가 꼭 "1번" 같은 형식처럼 입력하도록 하고싶었음.
                    int i; // for 문 밖으로 나가도 i를 그대로 활용할수있게끔 밖에 선언해둠.
                    boolean okCheck = false; // 올바른 경우가 아닐경우의 조건문을 생각하기가 복잡해서, boolean 변수로 활용하고 싶었음.

                    for (i = 1; i <= keyIndexStorage.size(); i++) { // size는 실제 데이터개수가 그 size 개수만큼 실제 존재한다는 것이고, 이 for는 실제 범위만큼만 일일이 세보겠다는 뜻이다.
                        // 그리고 유저가 입력한 숫자 값이, 실제 존재하는 인덱스만큼만 일일이 세는 i값과 일치한다면, 그 번째의 배열에는 어떤 데이터가 실존한다는 뜻이다.
                        // "0번" 입력한 경우도 나중에 밑에 처리하기
                        if (userInput.equals(i + "번")) { // 유저가 "i번" 형식으로만 입력해야만 코드가 실행되게끔함. 그리고 입력한 "?번" 중에 ?가 실제로 존재해야만 수행하도록 함.
                            System.out.println();
                            System.out.println(i + "번 게시물");
                            System.out.println("제목 :[" + keyIndexStorage.get(i - 1) + "]"); //실제 인덱스는 0부터 세므로 -1을 해줌.
                            System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(i - 1)) + "]");
                            okCheck = true; // 올바른 경우로 정해주기위함.
                            break;
                        }
                    }
                    if (okCheck == false) { // 값이 존재안할 경우만 이 코드를 실행 하고싶음. 이런것을 안하면, 멀쩡한 코드도 밑에 부분을 수행하게되기때문이다.
                        //여기에 온다는것은 유저의 입력값이 "0번" 또는 존재하는 게시글을 초과한 "?번" 이라는 뜻이다.
                        try {
                            System.out.println("제목 :[" + keyIndexStorage.get(i) + "]"); // 오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
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

                    for (i = 1; i <= keyIndexStorage.size(); i++) { // size는 실제 데이터개수가 그 size 개수만큼 실제 존재한다는 것이고, 이 for는 실제 범위만큼만 일일이 세보겠다는 뜻이다.
                        // 그리고 유저가 입력한 숫자 값이, 실제 존재하는 인덱스만큼만 일일이 세는 i값과 일치한다면, 그 번째의 배열에는 어떤 데이터가 실존한다는 뜻이다.

                        if (userInput.equals(i + "번")) { // 유저가 "i번" 형식으로만 입력해야만 코드가 실행되게끔함. 그리고 입력한 "?번" 중에 ?가 실제로 존재해야만 수행하도록 함.

                            System.out.println();
                            boardStorage.remove(keyIndexStorage.get(i - 1));// 해당 인덱스의 해당 키와 데이터를 쌍으로 Map에서 제거.
                            keyIndexStorage.remove(i-1); // 해당 인덱스의 해당 키값을 가진 데이터를 KeyIndexStorage 에서도 제거.
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
                            boardStorage.remove(keyIndexStorage.get(i)); //오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
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

                    for (i = 1; i <= keyIndexStorage.size(); i++) { // size는 실제 데이터개수가 그 size 개수만큼 실제 존재한다는 것이고, 이 for는 실제 범위만큼만 일일이 세보겠다는 뜻이다.
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

                            boardStorage.remove(keyIndexStorage.get(i - 1)); // 수정전 게시글을 Map에서 영구삭제
                            keyIndexStorage.remove(i - 1); //보관돼있던 수정전 key도 영구삭제한다. 그러면 뒤의 인덱스의 키들은 앞자리로 하나씩 당겨짐.
                            keyIndexStorage.add(i - 1, changeKey); // 수정한 key를 다시 삭제한 그 자리에 저장한다. 그러면 그 뒤의 인덱스 키들은 한칸씩 밀림.
                            boardStorage.put(keyIndexStorage.get(i - 1), changeValue); // 수정게시글을 Map에 영구저장
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
                            boardStorage.remove(keyIndexStorage.get(i)); //오류일수밖에 없다. 무조건 범위를 벗어난 i 이기때문이다.
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println(userInput + " 게시글은 존재하지 않습니다.");
                        }
                    }
                }  else if(userInput.equals("목록")){
                    System.out.printf("총 게시글은 %d개 작성되어있습니다." , keyIndexStorage.size());
                    System.out.println();
                    System.out.println();

                    for(int i=1; i<=keyIndexStorage.size(); i++){

                        System.out.println(i + "번 게시물");
                        System.out.println("제목 :[" + keyIndexStorage.get(i - 1) + "]"); //실제 인덱스는 0부터 세므로 -1을 해줌.
                        System.out.println("내용 :[" + boardStorage.get(keyIndexStorage.get(i - 1)) + "]");
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