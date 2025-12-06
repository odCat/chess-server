package ro.mg.chessserver.service;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Service;

import ro.mg.chessserver.model.Player;


@Service
public class UserService {

    @Getter
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player deletePlayer(int id) {
        int index = search(id);
        if (index != -1) {
            Player player = players.get(index);
            players.remove(index);
            return player;
        } else
            return null;
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
