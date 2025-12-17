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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.request.LoginRequest;
import ro.mg.chessserver.request.UpdateRequest;
import ro.mg.chessserver.service.PlayerService;


@RestController
@RequestMapping("/user")
public class UserController {

    private final PlayerService playerService;

    public UserController(@Autowired PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Player> getAllPlayers() {
        return playerService.getPlayers();
    }

    @PostMapping
    public ResponseEntity<Player> register(@Valid @RequestBody Player player) {
        if (playerService.addPlayer(player))
            return ResponseEntity.status(HttpStatus.CREATED).body(player);
        else
            return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity<Player> login(@Valid @RequestBody LoginRequest login) {
        Player player = playerService.login(login);

        if (player == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @PatchMapping
    public ResponseEntity<Player> update(@RequestParam int id, @RequestBody UpdateRequest update) {
        Player updated = playerService.update(id, update);
        if (updated == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam int id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
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
