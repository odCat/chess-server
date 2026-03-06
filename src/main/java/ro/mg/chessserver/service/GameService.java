package ro.mg.chessserver.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mg.chessserver.dto.game.Join;
import ro.mg.chessserver.dto.game.Open;
import ro.mg.chessserver.exception.GameAlreadyExists;
import ro.mg.chessserver.exception.GameCannotBeDeletedException;
import ro.mg.chessserver.exception.GameNotFoundException;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.repository.GameRepository;
import ro.mg.chessserver.dto.game.Diagram;
import ro.mg.chessserver.repository.PlayerRepository;


@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(@Autowired GameRepository gameRepository, @Autowired PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game create(Open openRequest) {
        Game openGame = gameRepository.findByStatus("OPEN").stream()
                .filter((game) -> game.getWhite().equals(openRequest.getName()) || game.getBlack().equals(openRequest.getName()))
                .findFirst()
                .orElse(null);

        if (openGame != null)
            return openGame;

        Game inprogressGame = gameRepository.findByStatus("INPROGRESS").stream()
                .filter((game) -> game.getWhite().equals(openRequest.getName()) || game.getBlack().equals(openRequest.getName()))
                .findFirst()
                .orElse(null);

        if (inprogressGame != null)
            throw new GameAlreadyExists("You have a game in progress");

        Game game = new Game(openRequest);

        return gameRepository.save(game);
    }

    public Game join(Join joinRequest) {

        if (gameRepository.findByStatus("INPROGRESS").stream()
                .filter((game) -> game.getWhite().equals(joinRequest.getName()) || game.getBlack().equals(joinRequest.getName()))
                .findFirst()
                .orElse(null) != null)
            throw new GameAlreadyExists("You already have a game in progress.");

        if (gameRepository.findByStatus("OPEN").stream()
                .filter((game) -> game.getWhite().equals(joinRequest.getName()) || game.getBlack().equals(joinRequest.getName()))
                .findFirst()
                .orElse(null) != null)
            throw new GameAlreadyExists("You have an open game.");

        Game game = gameRepository.findGameById(joinRequest.getId());

        if (joinRequest.getColor().equals("white"))
            game.setWhite(joinRequest.getName());

        if (joinRequest.getColor().equals("black"))
            game.setBlack(joinRequest.getName());

        game.setStatus("INPROGRESS");

        return gameRepository.save(game);
    }

    public void delete(long playerId, long gameId) {
        Game game = gameRepository.findGameById(gameId);
        if (game == null) {
            throw new GameNotFoundException("Could not find the game with id: " + gameId);
        }

        Player player = playerRepository.findById(playerId);
        if (!game.getWhite().equals(player.getUsername()) && !game.getBlack().equals(player.getUsername())) {
            throw new GameCannotBeDeletedException("Only the owner can delete this game.");
        }

        gameRepository.deleteById(gameId);
    }

    public List<Diagram> getInProgressGames() {
        List<Diagram> diagramList = new ArrayList<>();
        for (Game game : gameRepository.findByStatus("INPROGRESS")) {
            diagramList.add(new Diagram(game));
        }

        return diagramList;
    }

    public List<Diagram> getOpenGames() {
        List<Diagram> diagramList = new ArrayList<>();
        for (Game game : gameRepository.findByStatus("OPEN")) {
            diagramList.add(new Diagram(game));
        }

        return diagramList;
    }
    public Game getGame(long id) {
        return gameRepository.findGameById(id);
    }
}
