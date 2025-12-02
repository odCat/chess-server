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
        if (players.isEmpty())
            return null;

        for (int index = 0; index < players.size(); ++index) {
            Player player = players.get(index);
            if (player.getId() == id) {
                players.remove(index);
                return player;
            }
        }

        return null;
    }
}
