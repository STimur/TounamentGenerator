package tournament_generator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Timur on 09-May-17.
 */
public class AdministratorTest {

    private Administrator admin;

    @Before
    public void setUp() throws Exception {
        admin = new Administrator();
    }

    @Test
    public void whenNoPlayersRegistered_ThenNumberOfRegisteredPlayersEqualsZero() throws Exception {
        assertEquals(0, new Administrator().getNumberOfRegisteredPlayers());
    }

    @Test
    public void whenNewPlayerRegistered_ThenNumberOfRegisteredPlayersIncrements() throws Exception {
        admin.registerPlayer(new Player());
        assertEquals(1, admin.getNumberOfRegisteredPlayers());
        admin.registerPlayer(new Player());
        assertEquals(2, admin.getNumberOfRegisteredPlayers());
    }

    @Test(expected = Administrator.PlayerRegistrationException.class)
    public void WhenSeventeenthPlayerAttemptsToRegister_ThenThrowsPlayerRegistrationException() throws Exception {
        registerSeventeenPlayers();
    }

    private void registerSeventeenPlayers() {
        Player players[] = new Player[17];
        for (Player p : players)
            admin.registerPlayer(p);
    }
}
