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

    public class NoScoreForUnfinishedMatch extends RuntimeException {}

    public class NoWinnerForUnfinishedMatch extends RuntimeException {}
}
