import java.util.Scanner;

public class UserInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;

        do { System.out.print("명령어 > ");
            userInput = sc.nextLine();

            if ( !userInput.equals("종료")){
                System.out.println("존재하지 않는 명령어 입니다.");
            }

        } while( !userInput.equals("종료") );

        System.out.println("프로그램이 종료됩니다.");
    }

}
