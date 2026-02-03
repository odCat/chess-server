package ro.mg.chessserver.service;

import java.util.ArrayList;
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

    private final List<Player> players = new ArrayList<>();
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
        int index = search(login.getUsernameOrEmail());
        if (index > -1)
            if (players.get(index).getPassword().equals(login.getPassword()))
                return players.get(index);

        return null;
    }

    public Player update(int id, UpdateRequest update) {
        int index = search(id);
        if (index == -1)
            return null;

        Player oldPlayer = players.get(index);
        Player newPlayer = createNewPlayer(id, oldPlayer, update);
        players.remove(index);
        players.add(newPlayer);

        return newPlayer;
    }

    private Player createNewPlayer(int id, Player oldPlayer, UpdateRequest update) {
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

    public void deletePlayer(int id) {
        int index = search(id);
        if (index != -1)
            players.remove(index);
    }

    private int search(int id) {
        if (players.isEmpty())
            return -1;

        for (int index = 0; index < players.size(); ++index) {
            Player player = players.get(index);
            if (player.getId() == id) {
                return index;
            }
        }

        return -1;
    }

    private int search(String userNameOrEmail) {
        if (players.isEmpty())
            return -1;

        for (int index = 0; index < players.size(); ++index) {
            if (players.get(index).getUsername().equals(userNameOrEmail) ||
                    players.get(index).getEmail().equalsIgnoreCase(userNameOrEmail)) {
                return index;
            }
        }

        return -1;
    }

    private int search(Player player) {
        if (players.isEmpty())
            return -1;

        for (int index = 0; index < players.size(); ++index) {
            if (player.equals(players.get(index))) {
                return index;
            }
        }

        return -1;
    }
}
