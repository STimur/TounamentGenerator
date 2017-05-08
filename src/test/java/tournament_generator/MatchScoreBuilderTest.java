package tournament_generator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Timur on 04-May-17.
 */
public class MatchScoreBuilderTest {

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenEmptyListOfSetScores_ThenThrowsInvalidMatchScoreException() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<SetScore>());

    }

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenLessThanThreeSetScoresInList_ThenThrowsInvalidMatchScoreException() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )));
    }

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenMoreThanFiveSetScoresInList_ThenThrowsInvalidMatchScoreException() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )));
    }

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenThereIsNoPlayerWhoWinThreeSets_ThenThrowsInvalidMatchScoreException() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )));
    }

    @Test(expected = MatchScore.MatchScoreBuilder.InvalidParameters.class)
    public void whenThereIsMoreThanThreeWonSetsByOnePlayer_ThenThrowsInvalidMatchScoreException() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )));
    }

    @Test
    public void whenValidMatchScore_ConstructorCreateNewMatchScoreBuilderObject() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<SetScore>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        ))).build();
    }

    @Test
    public void canBuild_WinFirstLoseSecondWinLastTwoSetsByFirstPlayer() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )));
    }

    @Test
    public void canBuild_WinFirstLoseSecondWinThirdLoseFourthWinLastSetByFirstPlayer() throws Exception {
        new MatchScore.MatchScoreBuilder(new ArrayList<>(Arrays.asList(
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(11, 0).build(),
                new SetScore.SetScoreBuilder(0, 11).build(),
                new SetScore.SetScoreBuilder(11, 0).build()
        )));
    }


}