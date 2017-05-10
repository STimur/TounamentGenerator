package tournament_generator;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Timur on 02-May-17.
 */
public class SetScoreTest {

    private void assertValidSetScore(int first, int second) {
        new SetScore.SetScoreBuilder(first, second).build();
    }

    private void assertInvalidSetScore(int first, int second) {
        new SetScore.SetScoreBuilder(first, second).build();
    }

    private SetScore createSetScoreWhereFirstPlayerWon() {
        return new SetScore.SetScoreBuilder(11, 0).build();
    }

    private SetScore createSetScoreWhereSecondPlayerWon() {
        return new SetScore.SetScoreBuilder(0, 11).build();
    }

    @Test
    public void whenValidScore_canCreateSetScore() throws Exception {
        assertValidSetScore(11, 0);
        assertValidSetScore(0, 11);
        assertValidSetScore(13, 11);
        assertValidSetScore(11, 13);
        assertValidSetScore(10, 12);
        assertValidSetScore(12, 10);
    }

    @Test
    public void whenInvalidSetScore_ThenThrowsInvalidSetScoreException() throws Exception {
        final int NUMBER_OF_TEST_CASES = 6;
        final int EXPECTED_NUMBER_OF_CATCHED_EXCEPTIONS = 6;
        int i = 0;
        int firstScores[] = {0, 11, 10, 11, 11, -1};
        int secondScores[] = {0, 10, 11, 11, -1, 11};
        for (; i < NUMBER_OF_TEST_CASES; i++) {
            try {
                assertInvalidSetScore(firstScores[i], secondScores[i]);
            } catch (SetScore.SetScoreBuilder.InvalidSetScore e) {
                i++;
            }
        }
        assertEquals(EXPECTED_NUMBER_OF_CATCHED_EXCEPTIONS, i);
    }

    @Test
    public void givenFirstPlayerWon_HasFirstWonReturnsTrue() throws Exception {
        SetScore setScore = createSetScoreWhereFirstPlayerWon();
        assertTrue(setScore.hasFirstWon());
    }

    @Test
    public void givenSecondPlayerWon_HasFirstWonReturnsFalse() throws Exception {
        SetScore setScore = createSetScoreWhereSecondPlayerWon();
        assertFalse(setScore.hasFirstWon());
    }
}
