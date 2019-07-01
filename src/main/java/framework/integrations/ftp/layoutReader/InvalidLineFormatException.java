package framework.integrations.ftp.layoutReader;

public class InvalidLineFormatException extends RuntimeException {
    public InvalidLineFormatException(){
        super();
    }

    public InvalidLineFormatException(String message){
        super(message);
    }
}
