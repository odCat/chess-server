package ro.mg.chessserver.request;

import ro.mg.chessserver.model.Game;


@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Diagram {

    private final String white;
    private final String black;
    private final String fen;

    public Diagram(Game game) {
        this.white = game.getWhite();
        this.black = game.getBlack();
        this.fen = game.getFen();
    }
}
