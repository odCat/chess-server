package ro.mg.chessserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.mg.chessserver.model.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findAll();
    Game findGameById(long id);
}
