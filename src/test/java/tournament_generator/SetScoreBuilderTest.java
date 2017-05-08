package tournament_generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Timur on 04-May-17.
 */
public class SetScoreBuilderTest {
    @Test
    public void whenInvalidParameters_ConstructorThrowsInvalidSetScoreException() throws Exception {
        final int NUMBER_OF_TEST_CASES = 6;
        int numberOfCatchedExceptions = 0;
        int firstScores[] = {0, 11, 10, 11, 11, -1};
        int secondScores[] = {0, 10, 11, 11, -1, 11};
        while (numberOfCatchedExceptions != NUMBER_OF_TEST_CASES) {
            try {
                new SetScore.SetScoreBuilder(firstScores[numberOfCatchedExceptions], secondScores[numberOfCatchedExceptions]);
            } catch (SetScore.SetScoreBuilder.InvalidSetScore e) {
                numberOfCatchedExceptions++;
            }
        }
        assertEquals(NUMBER_OF_TEST_CASES, numberOfCatchedExceptions);
    }

    @Test
    public void whenValidSetScore_ConstructorCreateNewSetScoreBuilderObject() throws Exception {
        new SetScore.SetScoreBuilder(11, 0);
        new SetScore.SetScoreBuilder(11, 0);
        new SetScore.SetScoreBuilder(0, 11);
        new SetScore.SetScoreBuilder(13, 11);
        new SetScore.SetScoreBuilder(11, 13);
        new SetScore.SetScoreBuilder(10, 12);
        new SetScore.SetScoreBuilder(12, 10);
    }

    @Test
    public void whenInvalidSetScore_ThenBuildThrowsInvalidSetScoreException() throws Exception {
        final int NUMBER_OF_TEST_CASES = 6;
        int i = 0;
        int firstScores[] = {0, 11, 10, 11, 11, -1};
        int secondScores[] = {0, 10, 11, 11, -1, 11};
        while (i != NUMBER_OF_TEST_CASES) {
            try {
                new SetScore.SetScoreBuilder(firstScores[i], secondScores[i]).build();
            } catch (SetScore.SetScoreBuilder.InvalidSetScore e) {
                i++;
            }
        }
        assertEquals(NUMBER_OF_TEST_CASES, i);
    }

    @Test
    public void whenValidSetScore_BuildReturnNewSetScoreObject() throws Exception {
        assertTrue(new SetScore.SetScoreBuilder(11, 0).build() instanceof SetScore);
        assertTrue(new SetScore.SetScoreBuilder(0, 11).build() instanceof SetScore);
        assertTrue(new SetScore.SetScoreBuilder(13, 11).build() instanceof SetScore);
        assertTrue(new SetScore.SetScoreBuilder(11, 13).build() instanceof SetScore);
        assertTrue(new SetScore.SetScoreBuilder(10, 12).build() instanceof SetScore);
        assertTrue(new SetScore.SetScoreBuilder(12, 10).build() instanceof SetScore);
    }
}
