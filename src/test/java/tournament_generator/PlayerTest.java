package tournament_generator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Timur on 09-May-17.
 */
public class PlayerTest {
    @Test
    public void playerMustHaveNameAndSurname() throws Exception {
        Player player = new Player("Jack", "Sparrow");
        assertTrue(player.getName().length() > 1);
        assertTrue(player.getSurname().length() > 1);
    }
}