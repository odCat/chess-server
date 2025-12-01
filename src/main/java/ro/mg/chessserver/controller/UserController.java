package ro.mg.chessserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.service.UserService;


@RestController("/")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String register(@RequestBody Player player) {
        userService.addPlayer(player);

        return player.toString();
    }

    @GetMapping("/user")
    public String login() {
        return "user";
    }

    @PutMapping("/user")
    public String update() {
        return "user";
    }

    @DeleteMapping("/user")
    public String delete() {
        return "user";
    }
}
