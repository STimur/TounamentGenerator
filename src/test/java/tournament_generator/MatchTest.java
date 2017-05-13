package tournament_generator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Timur on 03-May-17.
 */
public class MatchTest {
    final private int[] firstPlayerWonScores = new int[]{11, 0, 11, 0, 11, 0};
    final private int[] secondPlayerWonScores = new int[]{0, 11, 0, 11, 0, 11};
    final Player firstPlayer = new Player("1", "1");
    final Player secondPlayer = new Player("1", "1");

    private Match match;

    private MatchScore createMatchScore(int scores[]) {
        return new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(scores[0], scores[1]).build(),
                new SetScore.SetScoreBuilder(scores[2], scores[3]).build(),
                new SetScore.SetScoreBuilder(scores[4], scores[5]).build()
        )).build();
    }

    @Before
    public void setUp() {
        match = new Match();
        match.setFirstPlayer(firstPlayer);
        match.setSecondPlayer(secondPlayer);
    }

    @Test
    public void knowsItsFirstPlayer() throws Exception {
        Player player = new Player("1", "1");
        match.setFirstPlayer(player);
        assertEquals(player, match.getFirstPlayer());
    }

    @Test
    public void knowsItsSecondPlayer() throws Exception {
        Player player = new Player("1", "1");
        match.setSecondPlayer(player);
        assertEquals(player, match.getSecondPlayer());
    }

    @Test
    public void givenMatchScoreIsSet_ThenIsFinishedReturnsTrue() throws Exception {
        MatchScore matchScore = createMatchScore(firstPlayerWonScores);
        match.setMatchScore(matchScore);
        assertTrue(match.isFinished());
    }

    @Test
    public void givenMatchScoreIsNotSet_ThenIsFinishedReturnsFalse() throws Exception {
        assertFalse(match.isFinished());
    }

    @Test(expected = Match.NoScoreForUnfinishedMatch.class)
    public void givenMatchIsNotFinished_WhenGetMatchScore_ThenThrowsNoScoreForUnfinishedMatch() throws Exception {
        match.getMatchScore();
    }

    @Test
    public void givenMatchIsFinished_WhenGetMatchScore_ThenReturnsMatchScore() throws Exception {
        MatchScore matchScore = createMatchScore(firstPlayerWonScores);
        match.setMatchScore(matchScore);
        assertEquals(matchScore, match.getMatchScore());
    }

    @Test(expected = Match.NoWinnerForUnfinishedMatch.class)
    public void givenMatchIsNotFinished_WhenGetWinner_ThenThrowsNoWinnerForUnfinishedMatch() throws Exception {
        match.getWinner();
    }

    @Test
    public void givenMatchScoreIsSet_WhenGetWinner_ThenReturnsPlayerInstance() throws Exception {
        match.setMatchScore(createMatchScore(firstPlayerWonScores));
        assertTrue(match.getWinner() instanceof Player);
    }

    @Test
    public void givenMatchScoreIsSetAndFirstPlayerWon_WhenGetWinner_ThenReturnsTheFirstPlayer() throws Exception {
        match.setMatchScore(createMatchScore(firstPlayerWonScores));
        assertEquals(firstPlayer, match.getWinner());
    }

    @Test
    public void givenMatchScoreIsSetAndSecondPlayerWon_WhenGetWinner_ThenReturnsTheSecondPlayer() throws Exception {
        match.setMatchScore(createMatchScore(secondPlayerWonScores));
        assertEquals(secondPlayer, match.getWinner());
    }

    @Test
    public void hasNumber() throws Exception {
        match.setNumber(1);
        assertEquals(1, match.getNumber());
    }

    @Test
    public void givenNextMatchIsNull_HasNextMatch_ReturnsFalse() throws Exception {
        assertFalse(match.hasNextMatch());
    }

    @Test
    public void givenNextMatchIsNotNull_HasNextMatch_ReturnsFalse() throws Exception {
        match.setNextMatch(new Match());
        assertTrue(match.hasNextMatch());
    }

    @Test
    public void givenMatchHasOddNumberAndHasNextMatch_WhenMatchScoreIsSet_ThenWinnerIsAssignedAsFirstPlayerToNextMatch() throws Exception {
        match.setNumber(1);
        match.setNextMatch(new Match());
        match.setMatchScore(createMatchScore(firstPlayerWonScores));
        assertEquals(match.getNextMatch().getFirstPlayer(), match.getWinner());
    }

    @Test
    public void givenMatchHasEvenNumberAndHasNextMatch_WhenMatchScoreIsSet_ThenWinnerIsAssignedAsSecondPlayerToNextMatch() throws Exception {
        match.setNumber(2);
        match.setNextMatch(new Match());
        match.setMatchScore(createMatchScore(firstPlayerWonScores));
        assertEquals(match.getNextMatch().getSecondPlayer(), match.getWinner());
    }
}
