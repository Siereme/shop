package app.exception;

import java.util.HashMap;
import java.util.Map;

public class UserAlreadyExistsException extends RuntimeException{
    Map<String, String> messages = new HashMap<>();

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(Map<String, String> messages) {
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessage(String name, String value) {
        this.messages.put(name, value);
    }
}
