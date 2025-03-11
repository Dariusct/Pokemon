package commandhandler;


/**
 * The Command quit.
 *
 * @author uvzab
 */
public class CommandQuit extends Command {


    /**
     * this method execute terminates the program.
     * @param args the args needed for certain commands.
     * @return returns false because the program should be terminated
     */
    public boolean execute(String[] args) {
        return false;
    }
}
