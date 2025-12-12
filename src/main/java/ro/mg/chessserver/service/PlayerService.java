package ro.mg.chessserver.service;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Service;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.request.LoginRequest;


@Service
public class PlayerService {

    @Getter
    private final List<Player> players = new ArrayList<>();

    public boolean addPlayer(Player player) {
        if (search(player) == -1) {
            players.add(player);
            return true;
        } else
            return false;
    }

    public Player login(LoginRequest login) {
        int index = search(login.getUsernameOrEmail());
        if (index > -1)
            if (players.get(index).getPassword().equals(login.getPassword()))
                return players.get(index);

        return null;
    }

    public Player update(int id, Player updatePlayer) {
        int index = search(id);
        if (index == -1)
            return null;

        Player oldPlayer = players.get(index);
        Player newPlayer = createNewPlayer(id, oldPlayer, updatePlayer);
        players.remove(index);
        players.add(newPlayer);

        return newPlayer;
    }

    private Player createNewPlayer(int id, Player oldPlayer, Player updatePlayer) {
        Player newPlayer = new Player();

        newPlayer.setId(id);

        if (updatePlayer.getEmail() != null)
            newPlayer.setEmail(updatePlayer.getEmail());
        else
            newPlayer.setEmail(oldPlayer.getEmail());

        if (updatePlayer.getUsername() != null)
            newPlayer.setUsername(updatePlayer.getUsername());
        else
            newPlayer.setUsername(oldPlayer.getUsername());

        if (updatePlayer.getPassword() != null)
            newPlayer.setPassword(updatePlayer.getPassword());
        else
            newPlayer.setPassword(oldPlayer.getPassword());

        if (updatePlayer.getFirstName() != null)
            newPlayer.setFirstName(updatePlayer.getFirstName());
        else
            newPlayer.setFirstName(oldPlayer.getFirstName());

        if (updatePlayer.getLastName() != null)
            newPlayer.setLastName(updatePlayer.getLastName());
        else
            newPlayer.setLastName(oldPlayer.getLastName());

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
