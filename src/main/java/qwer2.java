import java.util.*;

public class qwer2 { // testing class before UserInput

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;

        Map<String, String> boardStorage = new HashMap<>();
        Deque<String> dequeStorage = new LinkedList<>();
        Stack<String> stackStorage = new Stack<>();

        do { System.out.print("명령어 > ");
            userInput = sc.nextLine();

            if ( !userInput.equals("종료")) {

                if(userInput.equals("작성")) {
                    System.out.print("제목을 작성해주세요 :");
                    userInput = sc.nextLine();
                    dequeStorage.add(userInput); // 디큐에 제목(key) 저장
                    stackStorage.push(userInput); // 스택에 제목(key) 저장
                    System.out.println("작성한 제목이 저장되었습니다.");
                    System.out.print("내용을 작성해주세요 :");
                    userInput = sc.nextLine();
                    dequeStorage.add(userInput); //디큐에 내용 저장
                    boardStorage.put(dequeStorage.getFirst(), dequeStorage.getLast()); //Map에 제목(키), 내용(밸류) 로 저장.
                    dequeStorage.removeAll(dequeStorage); // 디큐 내용삭제. 작성 명령어가 끝난뒤 다시 작성으로 진입하면 나중에 dequeStorage 에서 값을 꺼내서 Map에 저장할때 다른것이 저장되기때문.
                    System.out.println("작성한 내용이 저장되었습니다.");

                }   else if(userInput.equals("조회")) { // 스택에 key를 넣은 상태임.
                    System.out.println("제목 :[" + stackStorage.peek() + "]");
                    System.out.println("내용 :[" + boardStorage.get(stackStorage.peek()) + "]");

                }   else if(userInput.equals("삭제")) {
                    boardStorage.remove(stackStorage.peek());
                    stackStorage.pop(); //  Map에서는 지워졌지만 Stack에는 남아있으므로 Stack도 삭제.
                    System.out.println("마지막 글이 삭제되었습니다.");

                }   else if(userInput.equals("수정")) {
                    System.out.println("마지막 글을 수정합니다.");
                    System.out.println("기존 제목 :[" + stackStorage.peek() +"]");
                    System.out.print("제목을 수정해주세요 :");
                    userInput = sc.nextLine();
                    dequeStorage.add(userInput); // 디큐에 제목(key) 저장
                    stackStorage.push(userInput); // 스택에 제목(key) 저장
                    System.out.println("작성한 제목이 저장되었습니다.");
                    System.out.print("내용을 작성해주세요 :");
                    userInput = sc.nextLine();
                    dequeStorage.add(userInput); //디큐에 내용 저장



                }

                else {
                    System.out.println("존재하지 않는 명령어 입니다.");
                }
            }

        } while( !userInput.equals("종료") );

        System.out.println("프로그램이 종료됩니다.");
    }

}
