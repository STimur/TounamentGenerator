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
    public static void createSeventeenPlayers() throws Exception {
        players = new Player[17];
        for (Integer i = 0; i < players.length; i++) {
            players[i] = new Player(i.toString(), i.toString());
        }
    }

    @Before
    public void setUp() throws Exception {
        admin = new Administrator();
    }

    private void registerSeventeenPlayers() {
        for (Integer i = 0; i < players.length; i++) {
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
    public void givenNullPlayer_regiterPlayerThrowsPlayerRegistrationException() throws Exception {
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
    public void afterSeedTournamentPlayersAreInAnotherOrder() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        List<Player> playersBeforeSeed = admin.getRegisteredPlayers();
        admin.seedTournament();
        List<Player> playersAfterSeed = admin.getPlayersSeed();
        assertNotEquals(playersBeforeSeed, playersAfterSeed);
    }

    @Test
    public void afterEverySeedTournamentPlayersAreInAnotherOrder() throws Exception {
        registerPlayers(Administrator.MAX_PLAYERS_IN_TOURNAMENT);
        List<Player> playersAfterSeed;
        List<Player> playersAfterAnotherSeed;
        for (int i = 0; i < 10; i++) {
            admin.seedTournament();
            playersAfterSeed = admin.getPlayersSeed();
            admin.seedTournament();
            playersAfterAnotherSeed = admin.getPlayersSeed();
            assertNotEquals(playersAfterSeed, playersAfterAnotherSeed);
        }
    }

}
