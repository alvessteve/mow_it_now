package domain.exceptions;

public class MalformedInstructionException extends InstructionException {

    public MalformedInstructionException(String message) {
        super(message);
    }

    public MalformedInstructionException(String message, Throwable e) {
        super(message, e);
    }
}
