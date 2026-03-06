package ro.mg.chessserver.exception;


public class GameAreadyExisting extends RuntimeException {

    public GameAreadyExisting(String message) {
        super(message);
    }
}
