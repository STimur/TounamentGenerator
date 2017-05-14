package tournament_generator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

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

    private void assertNumberOfRegisteredPlayersEquals(int expected) {
        assertEquals(expected, admin.getNumberOfRegisteredPlayers());
    }

    private void registerPlayers(int numberOfPlayersToRegister) {
        for (Integer i = 0; i < numberOfPlayersToRegister; i++) {
            admin.registerPlayer(players[i]);
        }
    }

    private void assertPlayersAreAssignedToFirstRoundMatches() {
        for (Match m : admin.getFirstRoundMatches())
            assertPlayersAreAssignedToMatch(m);
    }

    private void assertPlayersAreAssignedToMatch(Match m) {
        assertNotNull(m.getFirstPlayer());
        assertNotNull(m.getSecondPlayer());
    }

    private void assertEachSeedTournamentChangesPlayersSeed() {
        final int NUMBER_OF_SEEDS = 10;
        List<Player> playersAfterSeed, playersAfterAnotherSeed;
        for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
            admin.seedTournament();
            playersAfterSeed = admin.getPlayersSeed();
            admin.seedTournament();
            playersAfterAnotherSeed = admin.getPlayersSeed();
            assertNotEquals(playersAfterSeed, playersAfterAnotherSeed);
        }
    }

    private void assertPlayersAreNotAssignedToFirstRoundMatches() {
        for (Match m : admin.getFirstRoundMatches())
            assertPlayersAreNotAssignedToMatch(m);
    }

    private void assertPlayersAreNotAssignedToMatch(Match m) {
        assertNull(m.getFirstPlayer());
        assertNull(m.getSecondPlayer());
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
            assertNumberOfRegisteredPlayersEquals(i + 1);
        }
    }

    @Test(expected = Administrator.NoSpaceRegistrationException.class)
    public void WhenSeventeenthPlayerAttemptsToRegister_ThenThrowsPlayerRegistrationException() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT + 1);
    }

    @Test(expected = Administrator.SeedTournamentException.class)
    public void givenThereAreNoSixteenPlayersRegistered_WhenSeedTournament_ThenThrowsTournamentSeedException() throws Exception {
        admin.seedTournament();
    }

    @Test
    public void givenSixteenPlayersAreRegistered_WhenSeedTournament_ThenPlayersSeedContainsSixteenPlayers() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        admin.seedTournament();
        assertTrue(admin.getPlayersSeed().size() == admin.getNumberOfRegisteredPlayers());
    }

    @Test
    public void afterSeedTournamentPlayersAreNotInOrderOfRegistration() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        admin.seedTournament();
        assertNotEquals(admin.getRegisteredPlayers(), admin.getPlayersSeed());
    }

    @Test
    public void afterEverySeedTournamentPlayersSeedIsDifferent() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        assertEachSeedTournamentChangesPlayersSeed();
    }

    @Test(expected = Administrator.PlayersNotSeededToStartTournamentException.class)
    public void givenPlayersAreNotSeeded_WhenStartTournament_ThenThrowsPlayersNotSeededToStartTournamentException() throws Exception {
        admin.startTournament();
    }

    @Test
    public void givenPlayersAreNotSeeded_ThenPlayersAreNotAssignedToFirstRoundMatches() throws Exception {
        assertPlayersAreNotAssignedToFirstRoundMatches();
    }

    @Test
    public void givenPlayersAreSeeded_ThenPlayersAreAssignedToFirstRoundMatches() throws Exception {
        registerPlayers(16);
        admin.seedTournament();
        assertPlayersAreAssignedToFirstRoundMatches();
    }

}
