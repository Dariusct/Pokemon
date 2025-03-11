package game;

import combat.Competition;
import config.Configuration;

public class GameState {
    private Configuration config;
    private Competition competition;

    public GameState(Configuration config) {
        this.config = config;
        this.competition = null;
    }

    public Competition getCompetition() {
        return this.competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public boolean hasCompetition() {
        return this.competition != null;
    }

    public Configuration getConfig() {
        return this.config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public boolean hasConfiguration() {
        return this.config != null;
    }



}
