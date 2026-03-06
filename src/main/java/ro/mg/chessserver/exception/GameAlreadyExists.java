package ro.mg.chessserver.exception;


public class GameAlreadyExists extends RuntimeException {

    public GameAlreadyExists(String message) {
        super(message);
    }
}
