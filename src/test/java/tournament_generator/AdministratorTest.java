package tournament_generator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Timur on 09-May-17.
 */
public class AdministratorTest {

    private Administrator admin;
    private static Player[] players;

    @BeforeClass
    public static void createPlayers() throws Exception {
        final int NUMBER_OF_PLAYERS_TO_CREATE = 20;
        players = new Player[NUMBER_OF_PLAYERS_TO_CREATE];
        for (Integer i = 0; i < NUMBER_OF_PLAYERS_TO_CREATE; i++)
            players[i] = new Player(i.toString(), i.toString());
    }

    @Before
    public void setUp() throws Exception {
        admin = new Administrator();
    }

    private void registerSeventeenPlayers() {
        final int PLAYERS_TO_REGISTER = 17;
        for (Integer i = 0; i < PLAYERS_TO_REGISTER; i++) {
            admin.registerPlayer(players[i]);
        }
    }

    private void assertNumberOfRegisteredPlayersEquals(int expected) {
        assertEquals(expected, admin.getNumberOfRegisteredPlayers());
    }

    private void registerPlayers(int numberOfPlayersToRegister) {
        for (Integer i = 0; i < numberOfPlayersToRegister; i++) {
            admin.registerPlayer(players[i]);
        }
    }

    @Test
    public void whenNoPlayersRegistered_ThenNumberOfRegisteredPlayersEqualsZero() throws Exception {
        assertNumberOfRegisteredPlayersEquals(0);
    }

    @Test(expected = Administrator.PlayerRegistrationException.class)
    public void givenNullPlayer_registerPlayerThrowsPlayerRegistrationException() throws Exception {
        admin.registerPlayer(null);
    }

    @Test
    public void whenNewPlayerRegistered_ThenNumberOfRegisteredPlayersIncrements() throws Exception {
        for (int i = 0; i < Administrator.MAX_PLAYERS_IN_TOURNAMENT; i++) {
            admin.registerPlayer(players[i]);
            assertNumberOfRegisteredPlayersEquals(i+1);
        }
    }

    @Test(expected = Administrator.NoSpaceRegistrationException.class)
    public void WhenSeventeenthPlayerAttemptsToRegister_ThenThrowsPlayerRegistrationException() throws Exception {
        registerSeventeenPlayers();
    }

    @Test(expected = Administrator.SeedTournamentException.class)
    public void WhenThereAreNoSixteenPlayersRegistered_SeedTournament_ThrowsTournamentSeedException() throws Exception {
        admin.seedTournament();
    }

    @Test
    public void afterSeedTournamentGetSeededPlayersContainsSixteenPlayers() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        admin.seedTournament();
        assertTrue(admin.getPlayersSeed().size() == 16);
    }

    @Test
    public void afterSeedTournamentPlayersAreNotInOrderOfRegistration() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        List<Player> playersBeforeSeed = admin.getRegisteredPlayers();
        admin.seedTournament();
        List<Player> playersAfterSeed = admin.getPlayersSeed();
        assertNotEquals(playersBeforeSeed, playersAfterSeed);
    }

    @Test
    public void afterEverySeedTournamentPlayersSeedIsDifferent() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        final int NUMBER_OF_SEEDS = 10;
        for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
            admin.seedTournament();
            List<Player> playersAfterSeed = admin.getPlayersSeed();
            admin.seedTournament();
            List<Player> playersAfterAnotherSeed = admin.getPlayersSeed();
            assertNotEquals(playersAfterSeed, playersAfterAnotherSeed);
        }
    }

    @Test
    public void whenTournamentIsNotFinished_ThenIsTournamentFinishedReturnsFalse() throws Exception {
        assertFalse(admin.isTournamentFinished());
    }

    @Test(expected = Administrator.PlayersNotSeededToStartTournamentException.class)
    public void givenPlayersAreNotSeeded_WhenStartTournament_ThenThrowsPlayersNotSeededToStartTournamentException() throws Exception {
        admin.startTournament();
    }

    @Test
    public void existsEightMatchesInFirstRound() throws Exception {
        assertEquals(Administrator.NUMBER_OF_FIRST_ROUND_MATCHES, admin.getFirstRoundMatches().size());
    }

    @Test
    public void givenPlayersAreNotSeeded_ThenPlayersAreNotAssignedToFirstRoundMatches() throws Exception {
        assertNull(admin.getFirstRoundMatches().get(0).getFirstPlayer());
        assertNull(admin.getFirstRoundMatches().get(0).getSecondPlayer());
    }

    @Test
    @Ignore
    public void givenPlayersAreSeeded_ThenPlayersAreAssignedToFirstRoundMatches() throws Exception {
        assertNotNull(admin.getFirstRoundMatches().get(0).getFirstPlayer());
        assertNotNull(admin.getFirstRoundMatches().get(0).getSecondPlayer());
    }

    @Test
    @Ignore
    public void whenTournamentIsFinished_ThenIsTournamentFinishedReturnsTrue() throws Exception {
        assertTrue(admin.isTournamentFinished());
    }
}
