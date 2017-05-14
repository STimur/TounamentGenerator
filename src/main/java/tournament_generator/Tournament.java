package tournament_generator;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    public static final int NUMBER_OF_MATCHES_IN_TOURNAMENT = Administrator.MAX_PLAYERS_IN_TOURNAMENT - 1;
    public static final int NUMBER_OF_FIRST_ROUND_MATCHES = Administrator.MAX_PLAYERS_IN_TOURNAMENT / 2;
    public static final int NUMBER_OF_QUARTER_FINAL_MATCHES = 4;
    public static final int NUMBER_OF_SEMIFINAL_MATCHES = 2;
    Match finalMatch = new Match();
    List<Match> firstRoundMatches = new ArrayList<Match>();
    List<Match> matches = new ArrayList<Match>();

    public Tournament() {
    }

    void createTournament() {
        for (int i = 0; i < NUMBER_OF_MATCHES_IN_TOURNAMENT; i++)
            matches.add(new Match());
        createFinalMatch();
        createSemiFinalMatches();
        createQuarterFinalMatches();
        createFirstRoundMatches();
    }

    void createQuarterFinalMatches() {
        matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 4).setNextMatch(getSemiFinalMatches().get(1));
        matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 5).setNextMatch(getSemiFinalMatches().get(1));
        matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 6).setNextMatch(getSemiFinalMatches().get(0));
        matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 7).setNextMatch(getSemiFinalMatches().get(0));
    }

    void createSemiFinalMatches() {
        matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 2).setNextMatch(finalMatch);
        matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 3).setNextMatch(finalMatch);
    }

    void createFinalMatch() {
        finalMatch = matches.get(NUMBER_OF_MATCHES_IN_TOURNAMENT - 1);
    }

    void createFirstRoundMatches() {
        matches.get(0).setNextMatch(getQuarterFinalMatches().get(0));
        matches.get(1).setNextMatch(getQuarterFinalMatches().get(0));
        matches.get(2).setNextMatch(getQuarterFinalMatches().get(1));
        matches.get(3).setNextMatch(getQuarterFinalMatches().get(1));
        matches.get(4).setNextMatch(getQuarterFinalMatches().get(2));
        matches.get(5).setNextMatch(getQuarterFinalMatches().get(2));
        matches.get(6).setNextMatch(getQuarterFinalMatches().get(3));
        matches.get(7).setNextMatch(getQuarterFinalMatches().get(3));

        for (int i = 0; i < NUMBER_OF_FIRST_ROUND_MATCHES; i++)
            firstRoundMatches.add(matches.get(i));
    }

    public boolean isTournamentFinished() {
        if (finalMatch.isFinished())
            return true;
        return false;
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
}