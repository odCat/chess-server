package ro.mg.chessserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.request.DeleteRequest;
import ro.mg.chessserver.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String register(@RequestBody Player player) {
        userService.addPlayer(player);

        return player.toString();
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Player> getAllPlayers() {
        return userService.getPlayers();
    }

    @GetMapping
    public String login() {
        return "user";
    }

    @PutMapping
    public String update() {
        return "user";
    }

    @DeleteMapping
    public Player delete(@RequestParam int id) {
        return userService.deletePlayer(id);
    }
}
