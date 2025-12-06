package ro.mg.chessserver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import ro.mg.chessserver.model.Player;


public class TestPlayer {

    @Test
    void testPlayersWithTheSameId() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(1, "other@other.net", "otherjohn", "otherpassword", "Other", "Doe");

        assertEquals(player1, player2);
    }

    @Test
    void testPlayersWithTheSameEmail() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(2, "john@doe.net", "otherjohndoe", "otherpassword", "Other", "Doe");

        assertEquals(player1, player2);
    }

    @Test
    void testPlayersWithDifferentIdsAndUsernames() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(2, "other@other.net", "otherjohn", "password", "John", "Doe");

        assertNotEquals(player1, player2);
    }
}
