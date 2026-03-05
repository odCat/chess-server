package ro.mg.chessserver.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mg.chessserver.dto.game.Join;
import ro.mg.chessserver.dto.game.Open;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.repository.GameRepository;
import ro.mg.chessserver.dto.game.Diagram;


@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(@Autowired GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create(Open openRequest) {
        Game existing = gameRepository.findByStatus("OPEN").stream()
                .filter((game) -> game.getWhite().equals(openRequest.getName()) || game.getBlack().equals(openRequest.getName()))
                .findFirst()
                .orElse(null);

        if (existing != null)
            return existing;

        Game game = new Game(openRequest);

        return gameRepository.save(game);
    }

    public Game update(Join joinRequest) {

        if (gameRepository.findByStatus("INPROGRESS").stream()
                .filter((game) -> game.getWhite().equals(joinRequest.getName()) || game.getBlack().equals(joinRequest.getName()))
                .findFirst()
                .orElse(null) != null)
            return null;

        Game game = gameRepository.findGameById(joinRequest.getId());

        if (joinRequest.getColor().equals("white"))
            game.setWhite(joinRequest.getName());

        if (joinRequest.getColor().equals("black"))
            game.setBlack(joinRequest.getName());

        game.setStatus("INPROGRESS");

        return gameRepository.save(game);
    }

    public void delete(long id) {
        gameRepository.deleteById(id);
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
