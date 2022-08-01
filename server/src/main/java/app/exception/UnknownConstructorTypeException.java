package app.exception;

public class UnknownConstructorTypeException extends RuntimeException {

    public UnknownConstructorTypeException() {
        super("Unknown user constructor type");
    }
}
