package ro.mg.chessserver.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
public class UserController {

    @PostMapping("/user")
    public String register() {
        return "user";
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
