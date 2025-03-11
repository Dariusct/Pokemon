package commandhandler;

import config.ConfigParsing;
import game.GameState;

import java.io.IOException;


/**
 * The type Command load.
 * @author uvzab
 */
public class CommandLoad extends Command {

    private final GameState gameState;

    /**
     * Instantiates a new Command load.
     *
     * @param gameState the game state
     */
    public CommandLoad(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * The type Command load.
     * @param args should contain the load path in args[0]
     * @throws IOException the exception for the load path when parsing
     * @return returns true because the program should not be stopped
     */

    public boolean execute(String[] args) throws IOException {
        ConfigParsing configParsing = new ConfigParsing();
        configParsing.parseNewConfigFile(args[0]);
        configParsing.loadNewConfig();
        gameState.setConfig(configParsing.getConfig());
        return true;
    }

}
