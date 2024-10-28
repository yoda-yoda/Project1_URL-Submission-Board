public class Session<T> extends Request<T> {

    boolean userLogin;  // 만약 user로그인에 성공했다면 session.userLogin == true인 상태로 프로그램(do문) 계속 실행됨.
    boolean masterLogin;

    String loginingAccount; // 현재 로그인중인 그 계정명. 만약 로그인에 성공했다면 해당 계정을 담아 활용하기위해 만듬.

    public Session(T arg){ //세션 생성자
        super(arg);
    }

}
