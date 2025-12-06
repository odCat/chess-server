package ro.mg.chessserver.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Player> register(@Validated @RequestBody Player player) {
        if (userService.addPlayer(player))
            return ResponseEntity.status(HttpStatus.CREATED).body(player);
        else
            return
                   ResponseEntity.status(HttpStatus.OK).body(null);
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
    public ResponseEntity<Void> delete(@RequestParam int id) {
        userService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }
}
