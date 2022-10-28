package domain.exceptions;

public class MalformedCoordinatesInstruction extends MalformedInstructionException {
    public MalformedCoordinatesInstruction(String message) {
        super(message);
    }

    public MalformedCoordinatesInstruction(String message, Throwable e) {
        super(message, e);
    }
}
