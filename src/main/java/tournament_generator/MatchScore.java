package tournament_generator;

import java.util.List;

/**
 * Created by Timur on 04-May-17.
 */
public class MatchScore {

    private final List<SetScore> setScores;

    private MatchScore(MatchScoreBuilder matchScoreBuilder) {
        this.setScores = matchScoreBuilder.setScores;
    }

    public String toString() {
        String str = "";
        for (SetScore score : setScores) {
            if (!str.isEmpty())
                str += " ";
            str += score;
        }
        return str;
    }

    public boolean equals(Object obj) {
        return true;
    }

    /**
     * Created by Timur on 04-May-17.
     */
    public static class MatchScoreBuilder {
        private final List<SetScore> setScores;

        public MatchScoreBuilder(List<SetScore> setScores) {
            if (!isValidMatchScore(setScores))
                throw new InvalidParameters();
            this.setScores = setScores;
        }

        public MatchScore build() {
            return new MatchScore(this);
        }

        private boolean isValidMatchScore(List<SetScore> setScores) {
            if (setScores.size() < 3 || setScores.size() > 5)
                return false;
            if (!isThreeSetsWonByOnePlayer(setScores))
                return false;
            return true;
        }

        private boolean isThreeSetsWonByOnePlayer(List<SetScore> setScores) {
            int firstPlayerWins = 0;
            for (SetScore score : setScores)
                if (score.getFirstScore() - score.getSecondScore() > 0)
                    firstPlayerWins++;
            if (firstPlayerWins == 3 || ((setScores.size() - firstPlayerWins) == 3))
                return true;
            return false;
        }

        public class InvalidMatchScore extends RuntimeException {
        }

        public class InvalidParameters extends RuntimeException {
        }
    }
}
