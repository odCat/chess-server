package ro.mg.chessserver.map;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.dto.Update;

public class PlayerMapper {

    public static Player playerFromUpdateRequest(Update update) {
        Player player = new Player();

        if (update.getEmail() != null)
            player.setEmail(update.getEmail());

        if (update.getUsername() != null)
            player.setUsername(update.getUsername());

        if (update.getPassword() != null)
            player.setPassword(update.getPassword());

        if (update.getFullName() != null)
            player.setFullName(update.getFullName());

        return player;
    }
}
