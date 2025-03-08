package combat;

import commandhandler.Command;
import commandhandler.CommandQuit;

import javax.management.modelmbean.ModelMBeanInfoSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Competition {

    private static final char INDEX_SUFFIX = '#';
    private final Map<String, CombatMonster> activeMonsters = new HashMap<>();
    private Map<String, Command> commands = new HashMap<>();
    private boolean isRunning;

    public Competition(List<MonsterBaseValues> fightingMonsters) {

        this.isRunning = false;
        initializeMonsterNames(fightingMonsters);
        initializeCommands();
    }

    private void initializeMonsterNames(List<MonsterBaseValues> fightingMonsters) {
        Map<String, Integer> nameCount = new HashMap<>();
        for (MonsterBaseValues monster : fightingMonsters) {
            String monsterName = monster.getName();
            nameCount.put(monsterName, nameCount.getOrDefault(monsterName, 0) + 1);
        }

        for (MonsterBaseValues monster : fightingMonsters) {
            String monsterName = monster.getName();
            int count = nameCount.getOrDefault(monsterName, 0);
            if (nameCount.get(monsterName) > 1) {
                count -= 1;
                monsterName = monster.getName() + INDEX_SUFFIX + (nameCount.get(monsterName) - count);
            }

            activeMonsters.put(monsterName, new CombatMonster(monster));
        }
    }


    private void initializeCommands() {
        commands.put("quit", new CommandQuit(this));
    }


    public void startCompetition() {
        this.isRunning = true;
        while (isRunning) {
            phase1();
            isRunning = phase2();
        }
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void phase1() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        Command command = commands.get
    }

    public boolean phase2() {
        return true;
    }
}
