import java.util.*;

public class UserInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;

        Map<String, String> boardStorage = new HashMap<>();
        Deque<String> dequeStorage = new LinkedList<>();
        Stack<String> stackStorage = new Stack<>();

        do {
            System.out.print("명령어 > ");
            userInput = sc.nextLine();

            if (!userInput.equals("종료")) {

                if (userInput.equals("작성")) {
                    System.out.print("제목을 작성해주세요 :");
                    userInput = sc.nextLine();
                    dequeStorage.add(userInput); // 디큐에 제목(key) 저장
                    stackStorage.push(userInput); // 스택에 제목(key) 저장
                    //System.out.println("작성한 제목이 저장되었습니다."); //지워도될듯
                    System.out.print("내용을 작성해주세요 :");
                    userInput = sc.nextLine();
                    dequeStorage.add(userInput); //디큐에 내용 저장
                    boardStorage.put(dequeStorage.getFirst(), dequeStorage.getLast()); //Map에 제목(키), 내용(밸류) 로 저장.
                    dequeStorage.removeAll(dequeStorage); // 디큐 내용삭제. 작성 명령어가 끝난뒤 다시 작성으로 진입하면 나중에 dequeStorage 에서 값을 꺼내서 Map에 저장할때 다른것이 저장되기때문.
                    System.out.println("글이 저장되었습니다.");

                } else if (userInput.equals("조회")) { // 스택에 key를 넣은 상태임.
                    System.out.println("제목 :[" + stackStorage.peek() + "]");
                    System.out.println("내용 :[" + boardStorage.get(stackStorage.peek()) + "]");

                } else if (userInput.equals("삭제")) {
                    boardStorage.remove(stackStorage.peek());
                    stackStorage.pop(); //  Map에서는 지워졌지만 Stack에는 남아있으므로 Stack도 삭제.
                    System.out.println("마지막 글이 삭제되었습니다.");

                } else if (userInput.equals("수정")) {
                    System.out.println("마지막 글을 수정합니다. "); // 수정되는 글이 마지막 글이라는것을 표시해줌
                    System.out.println("기존 제목 :[" + stackStorage.peek() + "]"); // 편의를 위해 기존 제목을 보여줌
                    System.out.print("바꿀 제목을 입력해주세요 :");
                    dequeStorage.add(sc.nextLine()); //굳이 이때 수정한 Key를 Stack이 아니라 Deque에 저장한이유: 수정의 순서때문이다.
                    // 즉 제목(key)을 먼저 수정한다음에 내용(value)을 수정하기때문이다. 먼저 수정한다는 것은 표준입력 엔터를 먼저 한번 받아야한다는말이다.
                    // 그말은 먼저 새 Key 입력을 한번 저장을 해야한다. 근데 만약 이 시점에 Stack에다 저장했다면 나중에 Map의 내용 삭제가 어렵다.
                    // 기존의 마지막 Key를 Stack이 보유하고 있어줘야하기때문이다.
                    //System.out.println("바꾼 제목이 입력되었습니다."); 지워도될듯
                    System.out.println("기존 내용 :[" + boardStorage.get(stackStorage.peek()) + "]"); // 편의를 위해 기존 내용을 보여줌
                    System.out.print("바꿀 내용을 입력해주세요 :");
                    dequeStorage.add(sc.nextLine()); // Deque에 수정한 value 값도 저장
                    boardStorage.remove(stackStorage.peek()); // 기존 Map의 마지막글(key와 value) 삭제
                    stackStorage.pop(); // 기존 Stack의 마지막 key 삭제
                    stackStorage.push(dequeStorage.peek()); // Deque에 저장되어있는 수정된 key를 Stack에 저장한다.
                    // 그이유는 나중에 게시글의 최근값을 불러올때 Stack의 구조를 활용하기위함.
                    boardStorage.put(dequeStorage.getFirst(), dequeStorage.getLast());
                    dequeStorage.removeAll(dequeStorage); // Deque를 깨끗이 삭제해서 비워줌. 그 이유는 작성 시에 Deque가 쓰일때 다른 데이터가 남아있으면 오작동하기때문.
                    System.out.println("수정이 완료되었습니다.");

                } else {
                    System.out.println("존재하지 않는 명령어 입니다.");
                }
            }

        } while (!userInput.equals("종료"));

        System.out.println("프로그램이 종료됩니다.");

    }
}
