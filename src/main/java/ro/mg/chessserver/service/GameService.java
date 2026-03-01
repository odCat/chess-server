package ro.mg.chessserver.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.repository.GameRepository;
import ro.mg.chessserver.dto.Diagram;


@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(@Autowired GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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
