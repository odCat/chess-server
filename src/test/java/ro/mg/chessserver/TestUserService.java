package ro.mg.chessserver;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.request.LoginRequest;
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

    @Test
    void testLoginSuccessful() {
        Player expected = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        userService.addPlayer(expected);
        LoginRequest login = new LoginRequest("johndoe", "password");

        Player actual = userService.login(login);
        assertEquals(expected, actual);
    }

    @Test
    void testLoginNonExistingUser() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        Player player2 = new Player(2, "jane@doe.net", "janedoe", "janepassword", "Jane", "Doe");
        userService.addPlayer(player1);
        LoginRequest login = new LoginRequest(player2.getUsername(), player2.getPassword());

        Player actual = userService.login(login);
        assertNull(actual);
    }

    @Test
    void testLoginWithWrongPassword() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        userService.addPlayer(player1);
        LoginRequest login = new LoginRequest(player1.getUsername(), "wrong_password");

        Player actual = userService.login(login);
        assertNull(actual);
    }

    @Test
    void testCannotUpdatePlayerId() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        userService.addPlayer(player1);
        Player player2 = new Player();
        player2.setId(1);

        userService.update(1, player2);

        int actual = userService.getPlayers().getFirst().getId();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void testCanUpdateAllFieldsAtOnce() {
        Player player1 = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        userService.addPlayer(player1);
        Player player2 = new Player(1, "jane@doe.net", "janedoe", "janepassword", "Jane", "Doe");

        userService.update(1, player2);

        String actual = userService.getPlayers().getFirst().getEmail();
        String expected = player2.getEmail();
        assertEquals(expected, actual);

        actual = userService.getPlayers().getFirst().getUsername();
        expected = player2.getUsername();
        assertEquals(expected, actual);

        actual = userService.getPlayers().getFirst().getPassword();
        expected = player2.getPassword();
        assertEquals(expected, actual);

        actual = userService.getPlayers().getFirst().getFirstName();
        expected = player2.getFirstName();
        assertEquals(expected, actual);

        actual = userService.getPlayers().getFirst().getLastName();
        expected = player2.getLastName();
        assertEquals(expected, actual);
    }

    @Test
    void testUpdateNonExistingPlayer() {
        Player expectedPlayer = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        userService.addPlayer(expectedPlayer);
        Player otherPlayer = new Player(3, "jane@doe.net", "janedoe", "janepassword", "Jane", "Doe");
        userService.update(3, otherPlayer);

        int actual = userService.getPlayers().size();
        int expected = 1;
        assertEquals(expected, actual);

        Player actualPlayer = userService.getPlayers().getFirst();
        assertTrue(actualPlayer.fullEquals(expectedPlayer));
    }

    @Test
    void testUpdateWithEmptyPlayer() {
        Player expectedPlayer = new Player(1, "john@doe.net", "johndoe", "password", "John", "Doe");
        userService.addPlayer(expectedPlayer);
        Player player2 = new Player();
        player2.setId(1);
        userService.update(1, player2);

        int actual = userService.getPlayers().size();
        int expected = 1;
        assertEquals(expected, actual);

        Player actualPlayer = userService.getPlayers().getFirst();
        assertTrue(actualPlayer.fullEquals(expectedPlayer));
    }
}
