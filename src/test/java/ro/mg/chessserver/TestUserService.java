package ro.mg.chessserver;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.service.UserService;


@Tag("Unit")
public class TestUserService {

    UserService userService = new UserService();

    @Test
    void testAddPlayers() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(2, "jane@doe.net", "janedoe", "password", "Jane", "Doe");

        boolean actual = userService.addPlayer(player1);
        boolean expected = true;
        assertEquals(expected, actual);

        int actualSize = userService.getPlayers().size();
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);

        actual = userService.addPlayer(player2);
        assertEquals(expected, actual);

        actualSize = userService.getPlayers().size();
        expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void testAddExistingPlayer() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");

        userService.addPlayer(player1);
        boolean actualAdded = userService.addPlayer(player1);
        boolean expectedAdded = false;

        assertEquals(expectedAdded, actualAdded);

        int actualSize = userService.getPlayers().size();
        int expected = 1;
        assertEquals(expected, actualSize);
    }

    @Test
    void testRemovePlayer() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(2, "jane@doe.net", "janedoe", "password", "Jane", "Doe");

        userService.addPlayer(player1);
        userService.addPlayer(player2);
        userService.deletePlayer(player1.getId());

        int actual = userService.getPlayers().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void testRemoveNonExistingPlayer() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(2, "jane@doe.net", "janedoe", "password", "Jane", "Doe");

        userService.addPlayer(player1);
        userService.deletePlayer(player2.getId());

        int actual = userService.getPlayers().size();
        int expected = 1;
        assertEquals(expected, actual);
    }
}
