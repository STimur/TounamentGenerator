package tournament_generator;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Timur on 03-May-17.
 */
public class MatchTest {
    @Test
    public void haveFiveSets() throws Exception {
        Match match = new Match();
        assertEquals(5, match.getNumberOfSets());
    }

    @Test(expected = Match.NoScoreForUnfinishedMatch.class)
    public void getMatchScore_ThrowsExceptionIfMatchIsNotFinished() throws Exception {
        Match match = new Match();
        match.getMatchScore();
    }

    @Test
    public void whenMatchFinished_canGetMatchScore() throws Exception {
        Match match = new Match();
        match.setMatchScore(new MatchScore.MatchScoreBuilder(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )).build());
        assertEquals("11-0 11-0 11-0", match.getMatchScore().toString());
    }
}
