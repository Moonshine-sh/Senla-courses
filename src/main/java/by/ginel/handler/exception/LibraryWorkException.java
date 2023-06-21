package by.ginel.handler.exception;

public class LibraryWorkException extends RuntimeException{
    public LibraryWorkException(String message) {
        super(message);
    }
    public LibraryWorkException(LibraryWorkException sourceException) {
        super(sourceException);
    }
}
