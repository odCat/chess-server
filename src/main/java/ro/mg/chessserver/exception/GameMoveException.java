package ro.mg.chessserver.exception;

import com.github.bhlangonijr.chesslib.move.MoveException;

public class GameMoveException extends MoveException {

    public GameMoveException(String message) {
        super(message);
    }
}
