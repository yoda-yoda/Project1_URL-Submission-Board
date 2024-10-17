import java.util.*;

//마지막 자료부터 뭔가 이뤄진다는걸 보고 스택구조 배운것이 떠올랐음.

public class qwer {

    public static void main(String[] args) {

        /*Map<String, String> storage = new HashMap<>();
        storage.put("제목1", "내용내용내용1");
        storage.put("제목2", "내용내용내용2");
        System.out.println(storage.get("제목1"));
        System.out.println(storage.get("제목2"));

       Stack<String> storage2 = new Stack<>(); // 여기 저장되면 제일 마지막에 들어간 값을 꺼낼수있음.
        storage2.push("aaa");
        storage2.push("bbb");
        System.out.println(storage2.peek());
        storage2.pop();
        System.out.println(storage2.peek());*/

        Deque<String> qq1 = new LinkedList<>();
        qq1.push("1");
        qq1.push("2");

        System.out.println(qq1.getFirst());;

        /*Map<String, String> boardStorage = new HashMap<>();
        boardStorage.put("A", "1");
        boardStorage.put("B", "2");
        boardStorage.put("C", "3");
        boardStorage.put("D", "4");
        boardStorage.put("E", "5");
        System.out.println(boardStorage.get("C"));; //3
        boardStorage.remove("E");
        System.out.println(boardStorage.get("E"));
        System.out.println(boardStorage.values());*/






    }

}