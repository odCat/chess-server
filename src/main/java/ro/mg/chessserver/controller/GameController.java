package ro.mg.chessserver.controller;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ro.mg.chessserver.dto.game.Diagram;
import ro.mg.chessserver.dto.game.Open;
import ro.mg.chessserver.dto.game.Join;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.service.GameService;


@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ResponseEntity<Game> create(@Valid @RequestBody Open request) {
        Game game = gameService.create(request);

        if (game == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @PostMapping("/join")
    public ResponseEntity<Game> join(@Valid @RequestBody Join join) {
        Game game = gameService.update(join);

        if (game == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        gameService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/inprogress")
    public List<Diagram> getInProgressGames() {
        return gameService.getInProgressGames();
    }

    @GetMapping("/open")
    public List<Diagram> getOpenGames() {
        return gameService.getOpenGames();
    }

    @GetMapping("/id/{id}")
    public Game getGame(@PathVariable long id) {
        return gameService.getGame(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return errors;
    }
}
