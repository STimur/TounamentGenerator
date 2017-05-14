package tournament_generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Timur on 09-May-17.
 */
public class Administrator {
    public static final int MAX_PLAYERS_IN_TOURNAMENT = 16;
    private final Tournament tournament = new Tournament();


    private List<Player> registeredPlayers = new ArrayList();
    private List<Player> playersSeed = new ArrayList<>();

    public Administrator() {
        tournament.createTournament();
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
        for (int i = 0; i < Tournament.NUMBER_OF_FIRST_ROUND_MATCHES; i++) {
            tournament.getFirstRoundMatches().get(i).setFirstPlayer(playersSeed.get(2*i));
            tournament.getFirstRoundMatches().get(i).setSecondPlayer(playersSeed.get(2*i+1));
        }
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public List<Player> getPlayersSeed() {
        return playersSeed;
    }

    public boolean isTournamentFinished() {
        return tournament.isTournamentFinished();
    }

    public void startTournament() {
        throw new PlayersNotSeededToStartTournamentException();
    }

    public List<Match> getFirstRoundMatches() {
        return tournament.getFirstRoundMatches();
    }

    public List<Match> getAllMatches() {
        return tournament.getAllMatches();
    }

    public Match getFinalMatch() {
        return tournament.getFinalMatch();
    }

    public List<Match> getSemiFinalMatches() {
        return tournament.getSemiFinalMatches();
    }

    public List<Match> getQuarterFinalMatches() {
        return tournament.getQuarterFinalMatches();
    }

    public class NoSpaceRegistrationException extends RuntimeException {}

    public class PlayerRegistrationException extends RuntimeException {}

    public class SeedTournamentException extends RuntimeException {}

    public class PlayersNotSeededToStartTournamentException extends RuntimeException {}
}
