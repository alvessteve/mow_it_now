package domain.exceptions;

public class OutsideGrassMoveException extends RuntimeException {
    public OutsideGrassMoveException(String message) {
        super(message);
    }
}
