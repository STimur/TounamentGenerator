package tournament_generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur on 09-May-17.
 */
public class Administrator {
    private List<Player> registeredPlayers = new ArrayList();

    public int getNumberOfRegisteredPlayers() {
        return registeredPlayers.size();
    }

    public void registerPlayer(Player player) {
        if (registeredPlayers.size() == 16)
            throw new PlayerRegistrationException();
        registeredPlayers.add(player);
    }

    public class PlayerRegistrationException extends RuntimeException {
    }
}
