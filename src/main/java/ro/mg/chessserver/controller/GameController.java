package ro.mg.chessserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.service.GameService;


@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @ResponseBody
    public List<Game> getOpenGames() {
        return gameService.getAll();
    }
}
