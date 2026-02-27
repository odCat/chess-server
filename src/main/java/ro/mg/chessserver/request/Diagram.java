package ro.mg.chessserver.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.mg.chessserver.model.Game;


@AllArgsConstructor
@Getter
@Setter
@ToString
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Diagram {

    private final long id;
    private final String white;
    private final String black;
    private final String fen;

    public Diagram(Game game) {
        this.id = game.getId();
        this.white = game.getWhite();
        this.black = game.getBlack();
        this.fen = game.getFen();
    }
}
