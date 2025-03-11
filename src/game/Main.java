package game;

import commandhandler.Command;
import commandhandler.CommandCompetition;
import commandhandler.CommandLoad;
import commandhandler.CommandQuit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private GameState gameState;
    private final Map<String, Command> commands = new HashMap<>();

    public void startMain(String[] args) throws IOException {
        this.initializeCommands();
        Command commandLoad = new CommandLoad(this.gameState);
        commandLoad.execute(args);

        this.runScanner();
    }

    private void runScanner() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        Command newCommand = commands.get(tokens[0]);
        newCommand.execute(tokens);
    }


    private void initializeCommands() {
        commands.put("quit", new CommandQuit());
        commands.put("load", new CommandLoad(this.gameState));
        commands.put("competition", new CommandCompetition(this.gameState));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.startMain(args);
    }
}
