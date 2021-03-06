package tournament_generator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Timur on 09-May-17.
 */
public class PlayerTest {
    private Player player;

    private void assertPlayerHasNameAndSurname() {
        assertTrue(player.getName().length() > 1);
        assertTrue(player.getSurname().length() > 1);
    }

    private void assertPlayerEquals(Player otherPlayer) {
        assertTrue(player.equals(otherPlayer));
    }

    private void assertPlayerNotEquals(Player otherPlayer) {
        assertFalse(player.equals(otherPlayer));
    }

    @Test
    public void playerMustHaveNameAndSurname() throws Exception {
        player = new Player("Jack", "Sparrow");
        assertPlayerHasNameAndSurname();
    }

    @Test(expected = Player.EmptyNameException.class)
    public void whenEmptyName_ThenThrowsEmptyNameException() throws Exception {
        player = new Player("", "Sparrow");
    }

    @Test(expected = Player.EmptySurnameException.class)
    public void whenEmptyName_ThenThrowsEmptySurnameException() throws Exception {
        player = new Player("Jack", "");
    }

    @Test
    public void playersWithSameNamesAndSurnamesAreNotEqual() throws Exception {
        player = new Player("1", "1");
        assertPlayerNotEquals(new Player("1", "1"));
    }
}