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
    public static final int NUMBER_OF_MATCHES_IN_TOURNAMENT = MAX_PLAYERS_IN_TOURNAMENT - 1;
    public static final int NUMBER_OF_FIRST_ROUND_MATCHES = MAX_PLAYERS_IN_TOURNAMENT / 2;
    public static final int NUMBER_OF_QUARTER_FINAL_MATCHES = 4;
    public static final int NUMBER_OF_SEMIFINAL_MATCHES = 2;


    private List<Player> registeredPlayers = new ArrayList();
    private List<Player> playersSeed = new ArrayList<>();
    private Match finalMatch = new Match();
    private List<Match> firstRoundMatches = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    public Administrator() {
        createTournamentMatches();
        createFirstRoundMatches();
    }

    private void createTournamentMatches() {
        for (int i = 0; i < NUMBER_OF_MATCHES_IN_TOURNAMENT; i++)
            matches.add(new Match());
    }

    private void createFirstRoundMatches() {
        for (int i = 0; i < NUMBER_OF_FIRST_ROUND_MATCHES; i++)
            firstRoundMatches.add(new Match());
    }

    public int getNumberOfRegisteredPlayers() {
        return registeredPlayers.size();
    }

    public void registerPlayer(Player player) {
        if (registeredPlayers.size() == MAX_PLAYERS_IN_TOURNAMENT)
            throw new NoSpaceRegistrationException();
        if (player == null)
            throw new PlayerRegistrationException();
        registeredPlayers.add(player);
    }

    public void seedTournament() {
        if (registeredPlayers.size() != MAX_PLAYERS_IN_TOURNAMENT)
            throw new SeedTournamentException();
        Collections.shuffle(playersSeed = new ArrayList<>(registeredPlayers));
        assignPlayersToFirstRoundMatches();
    }

    private void assignPlayersToFirstRoundMatches() {
        for (int i = 0; i < NUMBER_OF_FIRST_ROUND_MATCHES; i++) {
            firstRoundMatches.get(i).setFirstPlayer(playersSeed.get(2*i));
            firstRoundMatches.get(i).setSecondPlayer(playersSeed.get(2*i+1));
        }
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

    public List<Match> getAllMatches() {
        return matches;
    }

    public Match getFinalMatch() {
        return matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 1);
    }

    public List<Match> getSemiFinalMatches() {
        return matches.subList(NUMBER_OF_MATCHES_IN_TOURNAMENT - 3, NUMBER_OF_MATCHES_IN_TOURNAMENT - 1);
    }

    public List<Match> getQuarterFinalMatches() {
        return matches.subList(NUMBER_OF_MATCHES_IN_TOURNAMENT - 7, NUMBER_OF_MATCHES_IN_TOURNAMENT - 3);
    }

    public class NoSpaceRegistrationException extends RuntimeException {}

    public class PlayerRegistrationException extends RuntimeException {}

    public class SeedTournamentException extends RuntimeException {}

    public class PlayersNotSeededToStartTournamentException extends RuntimeException {}
}
