package tournament_generator;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Timur on 04-May-17.
 */
public class MatchScoreTest {
    @Test
    public void whenValidSetScores_canCreateMatchScore() throws Exception {
        new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )).build();
    }

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenInvalidMatchScore_ThenThrowsInvalidMatchScoreException() throws Exception {
        new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )).build();
    }
}