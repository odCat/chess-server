package ro.mg.chessserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "games")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
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
}
