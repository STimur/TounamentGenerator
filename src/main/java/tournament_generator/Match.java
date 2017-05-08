package tournament_generator;

/**
 * Created by Timur on 03-May-17.
 */
public class Match {
    private boolean isFinished;
    private MatchScore matchScore;

    public Match() {
        isFinished = false;
    }

    public int getNumberOfSets() {
        return 5;
    }

    public MatchScore getMatchScore() {
        if (!isFinished)
            throw new NoScoreForUnfinishedMatch();
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        isFinished = true;
        this.matchScore = matchScore;
    }

    public class NoScoreForUnfinishedMatch extends RuntimeException {
    }
}
