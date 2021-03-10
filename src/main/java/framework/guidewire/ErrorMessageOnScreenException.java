package framework.guidewire;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

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
