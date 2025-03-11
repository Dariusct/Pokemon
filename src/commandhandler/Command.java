package commandhandler;

import java.io.IOException;

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
    public abstract boolean execute(String[] args) throws IOException;
}
