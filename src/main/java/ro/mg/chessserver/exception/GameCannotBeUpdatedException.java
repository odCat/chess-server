package ro.mg.chessserver.exception;


public class GameCannotBeUpdatedException extends RuntimeException {

    public GameCannotBeUpdatedException(String message) {
        super(message);
    }
}
