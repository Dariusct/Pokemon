package commandhandler;

/**
 * The type Command.
 * @author uvzab
 */
public abstract class Command {
    /**
     * Execute.
     *
     * @param args the args needed for certain commands.
     */
    abstract void execute(String[] args);
}
