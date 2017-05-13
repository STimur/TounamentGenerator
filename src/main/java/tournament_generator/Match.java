package tournament_generator;

/**
 * Created by Timur on 03-May-17.
 */
public class Match {
    private boolean isFinished;
    private MatchScore matchScore;
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean hasFirstPlayerWon;
    private int number;
    private Match nextMatch;

    public Match() {
        isFinished = false;
    }

    public MatchScore getMatchScore() {
        if (!isFinished)
            throw new NoScoreForUnfinishedMatch();
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        isFinished = true;
        if (matchScore.hasFirstWon())
            hasFirstPlayerWon = true;
        this.matchScore = matchScore;

        if (hasNextMatch())
            promoteWinnerToNextMatch();
    }

    private void promoteWinnerToNextMatch() {
        if (number % 2 == 1)
            nextMatch.setFirstPlayer(getWinner());
        else
            nextMatch.setSecondPlayer(getWinner());
    }

    public Player getWinner() {
        if (!isFinished)
            throw new NoWinnerForUnfinishedMatch();
        if (hasFirstPlayerWon)
            return firstPlayer;
        return secondPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public boolean isFinished() {
        if (matchScore == null)
            return false;
        return true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean hasNextMatch() {
        return nextMatch != null;
    }

    public void setNextMatch(Match nextMatch) {
        this.nextMatch = nextMatch;
    }

    public Match getNextMatch() {
        return nextMatch;
    }

    public class NoScoreForUnfinishedMatch extends RuntimeException {}

    public class NoWinnerForUnfinishedMatch extends RuntimeException {}
}
