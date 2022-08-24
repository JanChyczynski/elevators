package UI;

public class InvalidCommandArguments extends Exception {
    public InvalidCommandArguments(String errorMessage) {
        super(errorMessage);
    }
}