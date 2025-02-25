package config;

import Combat.Action;
import Combat.MonsterBaseValues;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private final List<MonsterBaseValues> monsterBaseValues = new ArrayList<MonsterBaseValues>();
    private final List<Action> actions = new ArrayList<>();

    public void clearConfig() {
        monsterBaseValues.clear();
        actions.clear();
    }

    public void addAction(Action action) {
        actions.add(action);
    }
}
