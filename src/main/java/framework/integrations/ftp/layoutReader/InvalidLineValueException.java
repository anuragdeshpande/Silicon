package framework.integrations.ftp.layoutReader;

public class InvalidLineValueException extends RuntimeException {
    public InvalidLineValueException(){
        super();
    }

    public InvalidLineValueException(String message){
        super(message);
    }
}
