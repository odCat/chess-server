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
}
