package ro.mg.chessserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.mg.chessserver.model.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findById(int id);
    Player findByUsernameOrEmail(String username, String email);
}
