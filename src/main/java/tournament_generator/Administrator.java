package tournament_generator;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Timur on 09-May-17.
 */
public class Administrator {
    public static final int MAX_PLAYERS_IN_TOURNAMENT = 16;
    public static final int NUMBER_OF_FIRST_ROUND_MATCHES = 8;

    private List<Player> registeredPlayers = new ArrayList();
    private List<Player> playersSeed = new ArrayList<>();
    private Match finalMatch = new Match();
    private List<Match> firstRoundMatches = new ArrayList<>();

    public Administrator() {
        createFirstRoundMatches();
    }

    private void createFirstRoundMatches() {
        for (int i = 0; i < NUMBER_OF_FIRST_ROUND_MATCHES; i++)
            firstRoundMatches.add(new Match());
    }

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
        Collections.shuffle(playersSeed = new ArrayList<>(registeredPlayers));
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public List<Player> getPlayersSeed() {
        return playersSeed;
    }

    public boolean isTournamentFinished() {
        if (finalMatch.isFinished())
            return true;
        return false;
    }

    public void startTournament() {
        throw new PlayersNotSeededToStartTournamentException();
    }

    public List<Match> getFirstRoundMatches() {
        return firstRoundMatches;
    }

    public class NoSpaceRegistrationException extends RuntimeException {}

    public class PlayerRegistrationException extends RuntimeException {}

    public class SeedTournamentException extends RuntimeException {}

    public class PlayersNotSeededToStartTournamentException extends RuntimeException {}
}
