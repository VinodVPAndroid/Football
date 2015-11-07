package demo.com.footbal.modal;

import com.google.gson.annotations.SerializedName;

/**
 * Serialize class for GSON.
 */
public class Game {
    // Member variables
    @SerializedName("date")
    private String mDate;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("matchday")
    private String mMatchDay;
    @SerializedName("homeTeamName")
    private String mHomeTeamName;
    @SerializedName("awayTeamName")
    private String mAwayTeamName;
    @SerializedName("result")
    private Result mResult;

    /**
     * This method is used to retrieve the Match date.
     *
     * @return Match date.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * This method is used to set the Match date.
     *
     * @param mDate Match date.
     */
    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * This method is used to retrieve the match status.
     *
     * @return Match Status.
     */
    public String getStatus() {
        return mStatus;
    }

    /**
     * This method is used to set the Status of match
     *
     * @param mStatus
     */
    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    /**
     * This method is used to retrieve the match day.
     *
     * @return Match day.
     */
    public String getMatchDay() {
        return mMatchDay;
    }

    /**
     * This method is used to set the match day.
     *
     * @param mMatchDay Match day.
     */
    public void setMatchDay(String mMatchDay) {
        this.mMatchDay = mMatchDay;
    }

    /**
     * This method is used to get the Home team Name.
     *
     * @return Home team name
     */
    public String getHomeTeamName() {
        return mHomeTeamName;
    }

    /**
     * This method is used to set the Home team name.
     *
     * @param mHomeTeamName Home Team name.
     */
    public void setHomeTeamName(String mHomeTeamName) {
        this.mHomeTeamName = mHomeTeamName;
    }

    /**
     * This method is used to get the Away team name.
     *
     * @return Away team name
     */
    public String getAwayTeamName() {
        return mAwayTeamName;
    }

    /**
     * This method is used to set the Away team name.
     *
     * @param mAwayTeamName Away team name.
     */
    public void setAwayTeamName(String mAwayTeamName) {
        this.mAwayTeamName = mAwayTeamName;
    }

    /**
     * This method is used to get the Result object.
     *
     * @return Result object.
     */
    public Result getResult() {
        return mResult;
    }

    /**
     * This method is used to set the Result of the match.
     *
     * @param mResult Result match.
     */
    public void setResult(Result mResult) {
        this.mResult = mResult;
    }

    /**
     * This class maintained result in json string.
     */
    public class Result {
        @SerializedName("goalsHomeTeam")
        private int mGoalHomeTeam;

        @SerializedName("goalsAwayTeam")
        private int mGoalAwayTeam;

        /**
         * This method is used to get the Goal scored by away team.
         *
         * @return Goal scored by away team.
         */
        public int getGoalAwayTeam() {
            return mGoalAwayTeam;
        }

        /**
         * This method is used to set the Goal scored by away team.
         *
         * @param mGoalAwayTeam Goal scored by away team.
         */
        public void setGoalAwayTeam(int mGoalAwayTeam) {
            this.mGoalAwayTeam = mGoalAwayTeam;
        }

        /**
         * This method is used to get the Goal scored by away team.
         *
         * @return Goal scored by home team.
         */
        public int getGoalHomeTeam() {
            return mGoalHomeTeam;
        }

        /**
         * This method is used to set the Goal scored by
         * hometeam.
         *
         * @param mGoalHomeTeam Goal scored by away team.
         */
        public void setGoalHomeTeam(int mGoalHomeTeam) {
            this.mGoalHomeTeam = mGoalHomeTeam;
        }
    }
}
