package app.utils.constants.user;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    ANONYMOUS("ANONYMOUS");

    private String value;

    UserRole(String value) {
        this.value = value;
    }
}
