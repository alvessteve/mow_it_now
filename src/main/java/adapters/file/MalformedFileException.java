package adapters.file;

public class MalformedFileException extends RuntimeException {
    public MalformedFileException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
