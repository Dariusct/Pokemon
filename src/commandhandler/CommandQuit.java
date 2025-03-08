package commandhandler;

import combat.Competition;

/**
 * The Command quit.
 * @author uvzab
 */
public class CommandQuit extends Command {

    private final Competition competition;

    /**
     * Instantiates a new quit command.
     *
     * @param competition is the competition that is running the game loop.
     */
    public CommandQuit(Competition competition) {
        this.competition = competition;
    }

    /**
     * Execute.
     * Ends the game loop and thus stops the program from running.
     *
     * @param args the args
     */
    public void execute(String[] args) {
        competition.setIsRunning(false);
    }
}
