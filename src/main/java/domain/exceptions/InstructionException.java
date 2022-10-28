package domain.exceptions;

public class InstructionException extends RuntimeException {
    public InstructionException(String message) {
        super(message);
    }

    public InstructionException(String message, Throwable e) {
        super(message, e);
    }
}
