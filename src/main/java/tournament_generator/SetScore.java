package tournament_generator;

public class SetScore {
    private int firstScore;
    private int secondScore;

    private SetScore(SetScoreBuilder builder) {
        this.firstScore = builder.getFirstScore();
        this.secondScore = builder.getSecondScore();
    }

    public int getFirstScore() {
        return firstScore;
    }

    public int getSecondScore() {
        return secondScore;
    }

    @Override
    public String toString() {
        return firstScore + "-" + secondScore;
    }

    public boolean hasFirstWon() {
        if (firstScore < secondScore)
            return false;
        return true;
    }

    /**
     * Created by Timur on 04-May-17.
     */
    public static class SetScoreBuilder {

        private final int firstScore;
        private final int secondScore;

        public SetScoreBuilder(int firstScore, int secondScore) {
            if (!isValidSetScore(firstScore, secondScore))
                throw new InvalidSetScore();
            this.firstScore = firstScore;
            this.secondScore = secondScore;
        }

        private boolean isValidSetScore(int firstScore, int secondScore) {
            return isOneOfScoresNegative(firstScore, secondScore) ? false :
                    (isStandardSetScore(firstScore, secondScore) || isBalancedSetScore(firstScore, secondScore));
        }

        private boolean isBalancedSetScore(int firstScore, int secondScore) {
            if (firstScore > 11 || secondScore > 11)
                return Math.abs(firstScore - secondScore) == 2;
            return false;
        }

        private boolean isStandardSetScore(int firstScore, int secondScore) {
            if (firstScore == 11 && secondScore < 10)
                return true;
            if (firstScore < 10 && secondScore == 11)
                return true;
            return false;
        }

        private boolean isOneOfScoresNegative(int firstScore, int secondScore) {
            if (firstScore < 0 || secondScore < 0)
                return true;
            return false;
        }

        public SetScore build() {
            return new SetScore(this);
        }

        public int getFirstScore() {
            return firstScore;
        }

        public int getSecondScore() {
            return secondScore;
        }

        public class InvalidSetScore extends RuntimeException {
        }

    }
}