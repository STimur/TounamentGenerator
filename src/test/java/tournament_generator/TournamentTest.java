package tournament_generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class TournamentTest {
    private Tournament tournament;

    @Before
    public void setUp() throws Exception {
        tournament = new Tournament();
    }

    private void assertCertainSizeOfMatchesList(int size, List<Match> matches) {
        Assert.assertEquals(size, matches.size());
    }

    private void assertFinalMatchIsLastInListOfAllMatches() {
        Assert.assertEquals(tournament.getAllMatches().get(Tournament.NUMBER_OF_MATCHES_IN_TOURNAMENT - 1), tournament.getFinalMatch());
    }

    private void assertMatchHasNoNextMatch(Match finalMatch) {
        Assert.assertNull(finalMatch.getNextMatch());
    }

    private void assertMatchesHaveCertainNextMatch(List<Match> matches, Match nextMatch) {
        assertMatchHasNextMatch(matches.get(0), nextMatch);
        assertMatchHasNextMatch(matches.get(1), nextMatch);
    }

    private void assertMatchHasNextMatch(Match match, Match nextMatch) {
        Assert.assertEquals(nextMatch, match.getNextMatch());
    }

    private void assertMatchesHaveCertainNextRoundMatches(List<Match> matches, List<Match> nextMatches) {
        for (int i = 0; i < nextMatches.size(); i++)
            assertMatchesHaveCertainNextMatch(matches.subList(2 * i, 2 * i + 2), nextMatches.get(i));
    }

    @Test
    public void tournamentStructure() throws Exception {
        assertCertainSizeOfMatchesList(Tournament.NUMBER_OF_MATCHES_IN_TOURNAMENT, tournament.getAllMatches());
        assertCertainSizeOfMatchesList(Tournament.NUMBER_OF_FIRST_ROUND_MATCHES, tournament.getFirstRoundMatches());
        assertCertainSizeOfMatchesList(Tournament.NUMBER_OF_QUARTER_FINAL_MATCHES, tournament.getQuarterFinalMatches());
        assertCertainSizeOfMatchesList(Tournament.NUMBER_OF_SEMIFINAL_MATCHES, tournament.getSemiFinalMatches());
        assertFinalMatchIsLastInListOfAllMatches();
    }

    @Test
    public void finalMatchHasNoNextMatch() throws Exception {
        assertMatchHasNoNextMatch(tournament.getFinalMatch());
    }

    @Test
    public void semiFinalMatchesHasFinalAsNextMatch() throws Exception {
        assertMatchesHaveCertainNextMatch(tournament.getSemiFinalMatches(), tournament.getFinalMatch());
    }

    @Test
    public void quarterFinalMatchesHaveProperSemiFinalMatchAsNextMatch() throws Exception {
        assertMatchesHaveCertainNextRoundMatches(tournament.getQuarterFinalMatches(), tournament.getSemiFinalMatches());
    }

    @Test
    public void firstRoundMatchesHaveProperQuarterFinalMatchAsNextMatch() throws Exception {
        assertMatchesHaveCertainNextRoundMatches(tournament.getFirstRoundMatches(), tournament.getQuarterFinalMatches());
    }

    @Test
    public void whenTournamentIsNotFinished_ThenIsTournamentFinishedReturnsFalse() throws Exception {
        Assert.assertFalse(tournament.isTournamentFinished());
    }

    @Test
    public void whenTournamentIsFinished_ThenIsTournamentFinishedReturnsTrue() throws Exception {
        tournament.finish();
        Assert.assertTrue(tournament.isTournamentFinished());
    }
}