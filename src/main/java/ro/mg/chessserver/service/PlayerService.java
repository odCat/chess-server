package ro.mg.chessserver.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.repository.PlayerRepository;
import ro.mg.chessserver.request.LoginRequest;
import ro.mg.chessserver.request.UpdateRequest;


@Service
public class PlayerService {

    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    public PlayerService(@Autowired PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public boolean addPlayer(Player player) {
        try {
            playerRepository.save(player);
            return true;
        } catch (JpaSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public Player login(LoginRequest login) {
        Player player = playerRepository.findByUsernameOrEmail(login.getUsernameOrEmail(), login.getUsernameOrEmail());
        if (player.getPassword().equals(login.getPassword()))
                return player;

        return null;
    }

    public Player update(long id, UpdateRequest update) {
        Player player = playerRepository.findById(id);
        if (player == null)
            return null;
        player = createNewPlayer(id, player, update);
        playerRepository.save(player);

        return player;
    }

    private Player createNewPlayer(long id, Player oldPlayer, UpdateRequest update) {
        Player newPlayer = new Player();

        newPlayer.setId(id);

        if (update.getEmail() != null)
            newPlayer.setEmail(update.getEmail());
        else
            newPlayer.setEmail(oldPlayer.getEmail());

        if (update.getUsername() != null)
            newPlayer.setUsername(update.getUsername());
        else
            newPlayer.setUsername(oldPlayer.getUsername());

        if (update.getPassword() != null)
            newPlayer.setPassword(update.getPassword());
        else
            newPlayer.setPassword(oldPlayer.getPassword());

        if (update.getFullName() != null)
            newPlayer.setFullName(update.getFullName());
        else
            newPlayer.setFullName(oldPlayer.getFullName());

        return newPlayer;
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }
}
