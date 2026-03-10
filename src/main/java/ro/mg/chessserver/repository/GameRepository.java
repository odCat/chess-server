package ro.mg.chessserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mg.chessserver.model.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameById(long id);
    List<Game> findByStatus(String status);

    @Query("SELECT g FROM Game g WHERE (g.white = :username OR g.black = :username) AND g.status = :status")
    List<Game> findGameHistory(String username, String status);
}
