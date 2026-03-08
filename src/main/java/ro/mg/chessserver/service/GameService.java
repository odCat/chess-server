package ro.mg.chessserver.service;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.mg.chessserver.dto.game.Join;
import ro.mg.chessserver.dto.game.Move;
import ro.mg.chessserver.dto.game.Open;
import ro.mg.chessserver.exception.GameAlreadyExists;
import ro.mg.chessserver.exception.GameCannotBeDeletedException;
import ro.mg.chessserver.exception.GameCannotBeUpdatedException;
import ro.mg.chessserver.exception.GameMoveException;
import ro.mg.chessserver.exception.GameNotFoundException;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.repository.GameRepository;
import ro.mg.chessserver.dto.game.Diagram;
import ro.mg.chessserver.repository.PlayerRepository;


@Service
public class GameService {

    private static final Logger log = LoggerFactory.getLogger(GameService.class);
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public GameService(@Autowired GameRepository gameRepository,
                       @Autowired PlayerRepository playerRepository,
                       @Autowired SimpMessagingTemplate messagingTemplate)
    {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Game create(Open request) {
        Game openGame = gameRepository.findByStatus("OPEN").stream()
                .filter((game) -> game.getWhite().equals(request.getName()) || game.getBlack().equals(request.getName()))
                .findFirst()
                .orElse(null);

        if (openGame != null)
            return openGame;

        Game inprogressGame = gameRepository.findByStatus("INPROGRESS").stream()
                .filter((game) -> game.getWhite().equals(request.getName()) || game.getBlack().equals(request.getName()))
                .findFirst()
                .orElse(null);

        if (inprogressGame != null)
            throw new GameAlreadyExists("You have a game in progress");

        Game game = new Game(request);

        game = gameRepository.save(game);

        return game;
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

    public Move move(long userId, long gameId, Move move) {
        Game game = gameRepository.findGameById(gameId);

        if (game == null)
            throw new GameNotFoundException("Could not find the game with id: " + gameId);
        if (game.getStatus().equals("FINISHED"))
            throw new GameCannotBeUpdatedException("The game is already finished.");

        Player player = playerRepository.findById(userId);

        log.info("Move: {}", move);
        log.info("Player: {}", player);

        if (!game.getWhite().equals(player.getUsername()) && !game.getBlack().equals(player.getUsername())) {
            throw new GameCannotBeUpdatedException("Only a participant can move");
        }
        if (move.getColor().equals("w"))
            if (!game.getWhite().equals(player.getUsername()))
                throw new GameMoveException("You cannot move for another player");
        if (move.getColor().equals("b"))
            if (!game.getBlack().equals(player.getUsername()))
                throw new GameMoveException("You cannot move for another player");

        checkMoveAndBroadcast(game, move);

        return move;
    }

    private void checkMoveAndBroadcast(Game game, Move move) {
        log.info("PGN: {}", game.getPgn());
        Board board = new Board();
        board.loadFromFen(game.getFen());
        log.info("FEN: {}", game.getFen());

        com.github.bhlangonijr.chesslib.move.Move moveObj =
            new com.github.bhlangonijr.chesslib.move.Move(move.getFrom() + move.getTo(), Side.WHITE);

        if (board.doMove(moveObj, true)) {
            messagingTemplate.convertAndSend("/topic/game/" + game.getId(), move);
            // TODO: Check the result of the client is correct
            if (board.isMated() || board.isDraw())
                game.setStatus("FINISHED");
            game.setFen(board.getFen());
            game.setPgn(move.getPgn());
            gameRepository.save(game);
            log.info("New FEN: {}", board.getFen());
            log.info("New PGN: {}", game.getPgn());
        } else {
            throw new GameMoveException("Invalid move");
        }
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
