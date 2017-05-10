package tournament_generator;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Timur on 04-May-17.
 */
public class MatchScoreTest {
    private MatchScore createMatchScoreWhereFirstPlayerWon() {
        return new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )).build();
    }

    private MatchScore createMatchScoreWhereSecondPlayerWon() {
        return new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(0, 11).build()
        )).build();
    }

    private void createInvalidMatchScore() {
        new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )).build();
    }

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenInvalidMatchScore_ThenThrowsInvalidMatchScoreException() throws Exception {
        createInvalidMatchScore();
    }

    @Test
    public void givenFirstPlayerWon_HasFirstWonReturnsTrue() throws Exception {
        MatchScore matchScore = createMatchScoreWhereFirstPlayerWon();
        assertTrue(matchScore.hasFirstWon());
    }

    @Test
    public void givenSecondPlayerWon_HasFirstWonReturnsFalse() throws Exception {
        MatchScore matchScore = createMatchScoreWhereSecondPlayerWon();
        assertFalse(matchScore.hasFirstWon());
    }
}