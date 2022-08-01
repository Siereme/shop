package app.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException() {
        super("Entity is not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
