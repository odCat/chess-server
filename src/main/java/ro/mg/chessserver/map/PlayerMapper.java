package ro.mg.chessserver.map;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.request.UpdateRequest;

public class PlayerMapper {

    public static Player playerFromUpdateRequest(UpdateRequest update) {
        Player player = new Player();

        if (update.getEmail() != null)
            player.setEmail(update.getEmail());

        if (update.getUsername() != null)
            player.setUsername(update.getUsername());

        if (update.getPassword() != null)
            player.setPassword(update.getPassword());

        if (update.getFirstName() != null)
            player.setFirstName(update.getFirstName());

        if (update.getLastName() != null)
            player.setLastName(update.getLastName());

        return player;
    }
}
