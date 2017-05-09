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
    public void playersWithSameNamesAndSurnamesAreEqual() throws Exception {
        assertTrue(new Player("1", "1").equals(new Player("1", "1")));
    }

    @Test
    public void playersWithDifferentNamesAndSurnamesAreNotEqual() throws Exception {
        assertFalse(new Player("1", "1").equals(new Player("1", "2")));
    }
}