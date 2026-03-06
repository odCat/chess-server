package ro.mg.chessserver.exception;

public class GameCannotBeDeletedException extends RuntimeException {

    public GameCannotBeDeletedException(String message) {
        super(message);
    }
}
