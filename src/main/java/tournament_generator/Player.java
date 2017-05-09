package tournament_generator;

/**
 * Created by Timur on 09-May-17.
 */
public class Player {
    private String name;
    private String surname;

    public Player(String name, String surname) {
        if (name.length() == 0)
            throw new EmptyNameException();
        if (surname.length() == 0)
            throw new EmptySurnameException();
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public class EmptyNameException extends RuntimeException {}

    public class EmptySurnameException extends RuntimeException {}
}
