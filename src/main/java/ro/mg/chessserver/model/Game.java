package ro.mg.chessserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import ro.mg.chessserver.dto.Open;


@Entity
@Table(name = "games")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String white;
    @Column(name = "white_id")
    private long whiteId;
    private String black;
    @Column(name = "black_id")
    private long blackId;
    private String status;
    private String pgn;
    private String fen;
    private String result;

    public Game(Open createRequest) {
        if (createRequest.getColor().equals("white")) {
            this.white = createRequest.getName();
            this.black = "";
        }

        if (createRequest.getColor().equals("black")) {
            this.black = createRequest.getName();
            this.white = "";
        }

        this.fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    }
}
