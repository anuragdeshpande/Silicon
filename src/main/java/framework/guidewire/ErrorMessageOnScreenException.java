package framework.guidewire;

public class ErrorMessageOnScreenException extends RuntimeException {

    public ErrorMessageOnScreenException(){
        super();
    }

    public ErrorMessageOnScreenException(Throwable e){
        super(e);
    }

    public ErrorMessageOnScreenException(String message){
        super(message);
    }
}
