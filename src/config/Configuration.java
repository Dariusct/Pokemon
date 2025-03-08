package config;

import combat.Action;
import combat.MonsterBaseValues;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private final Map<String, MonsterBaseValues> monsterBaseValues = new HashMap<>();
    private final Map<String, Action> actions = new HashMap<String, Action>();

    public void clearConfig() {
        monsterBaseValues.clear();
        actions.clear();
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public void addMonsterBaseValues(MonsterBaseValues monsterBaseValues) {
        this.monsterBaseValues.put(monsterBaseValues.getName(), monsterBaseValues);
    }
}
