package ro.mg.chessserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.mg.chessserver.dto.Diagram;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.service.GameService;


@Controller
@RequestMapping("/games")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    private final GameService gameService;

    public GameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/inprogress")
    @ResponseBody
    public List<Diagram> getInProgressGames() {
        return gameService.getInProgressGames();
    }

    @GetMapping("/open")
    @ResponseBody
    public List<Diagram> getOpenGames() {
        return gameService.getOpenGames();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Game getGame(@PathVariable long id) {
        return gameService.getGame(id);
    }
}
