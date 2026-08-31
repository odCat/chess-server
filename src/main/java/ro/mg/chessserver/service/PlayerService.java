package ro.mg.chessserver.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ro.mg.chessserver.dto.player.Register;
import ro.mg.chessserver.model.Game;
import ro.mg.chessserver.model.Player;
import ro.mg.chessserver.repository.GameRepository;
import ro.mg.chessserver.repository.PlayerRepository;
import ro.mg.chessserver.dto.player.Login;
import ro.mg.chessserver.dto.player.Update;


@Service
public class PlayerService {

    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final JwtService jwtService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository,
                         PasswordEncoder passwordEncoder,
                         GameRepository gameRepository,
                         JwtService jwtService)
    {
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.jwtService = jwtService;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public boolean addPlayer(Register register) {
        Player player = createPlayer(register);
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerRepository.save(player);
        return true;
    }

    private Player createPlayer(Register register) {
        Player player = new Player();
        player.setEmail(register.getEmail());
        player.setUsername(register.getUsername());
        player.setPassword(register.getPassword());
        player.setFullName(register.getFullName());

        register.setCreated(DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now()));
        player.setCreated((register.getCreated()));

        return player;
    }

    public Login authenticate(Login login) {
        Player player = playerRepository.findByUsernameOrEmail(login.getUsernameOrEmail(), login.getUsernameOrEmail());

        if (player != null && passwordEncoder.matches(login.getPassword(), player.getPassword())) {
            login.setId(player.getId());
            login.setUsername(player.getUsername());
            login.setEmail(player.getEmail());
            login.setFullName(player.getFullName());
            login.setPassword(jwtService.createToken(player.getId()));
            return login;
        }

        throw new UsernameNotFoundException("Invalid username or password");
    }

    public Player update(long id, Update update) {
        Player player = playerRepository.findById(id);

        if (player == null)
            return null;
        updatePlayer(player, update);
        playerRepository.save(player);

        player.setPassword(jwtService.createToken(player.getId()));
        return player;
    }

    public List<Game> getHistory(long id) {
        String username = playerRepository.findById(id).getUsername();
        return new ArrayList<>(gameRepository.findGameHistory(username, "FINISHED"));
    }

    private void updatePlayer(Player player, Update update) {

        if (update.getEmail() != null)
            player.setEmail(update.getEmail());

        if (update.getUsername() != null)
            player.setUsername(update.getUsername());

        if (update.getPassword() != null)
            player.setPassword(passwordEncoder.encode(update.getPassword()));

        if (update.getFullName() != null)
            player.setFullName(update.getFullName());
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }
}
