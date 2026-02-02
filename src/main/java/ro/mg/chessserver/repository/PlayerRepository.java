package ro.mg.chessserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.model.Room;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findAll();
}
