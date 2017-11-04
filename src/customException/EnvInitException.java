package customException;

//初始化环境时候的异常
public class EnvInitException extends Exception {
    public EnvInitException(String msg){
        super(msg);
    }
}
