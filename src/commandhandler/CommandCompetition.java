package commandhandler;

import combat.Competition;
import game.GameState;

/**
 * The type Command competition.
 * @author uvzab
 */
public class CommandCompetition extends Command {

    private final GameState gameState;

    /**
     * Instantiates a new Command competition.
     *
     * @param gameState the game state
     */
    public CommandCompetition(GameState gameState) {
        this.gameState = gameState;
    }


    /**
     * Sets a new Competition to start it.
     * @param args the args needed for certain commands.
     * @return true because the program should keep running.
     */
    public boolean execute(String [] args) {
        if (gameState.hasConfiguration()) {
            Competition competition = new Competition(this.gameState.getConfig().getMonsterBaseValues());
            this.gameState.setCompetition(competition);
        } else {
            throw error .....
        }
        return true;
    }
}
