package tournament_generator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Timur on 09-May-17.
 */
public class Administrator {
    public static final int MAX_PLAYERS_IN_TOURNAMENT = 16;
    private List<Player> registeredPlayers = new ArrayList();
    private List<Player> playersSeed = new ArrayList<>();

    public int getNumberOfRegisteredPlayers() {
        return registeredPlayers.size();
    }

    public void registerPlayer(Player player) {
        if (registeredPlayers.size() == 16)
            throw new NoSpaceRegistrationException();
        if (player == null)
            throw new PlayerRegistrationException();
        registeredPlayers.add(player);
    }

    public void seedTournament() {
        if (registeredPlayers.size() != MAX_PLAYERS_IN_TOURNAMENT)
            throw new SeedTournamentException();
        playersSeed = new ArrayList<>(registeredPlayers);
        Collections.shuffle(playersSeed);
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public List<Player> getPlayersSeed() {
        return playersSeed;
    }

    public class NoSpaceRegistrationException extends RuntimeException {}

    public class PlayerRegistrationException extends RuntimeException {}

    public class SeedTournamentException extends RuntimeException {}
}
