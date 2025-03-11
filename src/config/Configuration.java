package config;

import combat.Action;
import combat.MonsterBaseValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Configuration.
 * @author uvzab
 */
public class Configuration {

    private final List<MonsterBaseValues> monsterBaseValues = new ArrayList<MonsterBaseValues>();
    private final Map<String, Action> actions = new HashMap<String, Action>();

    /**
     * Clear config.
     */
    public void clearConfig() {
        monsterBaseValues.clear();
        actions.clear();
    }

    /**
     * Add action.
     *
     * @param action the action
     */
    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    /**
     * Add monster base values.
     *
     * @param monsterBaseValues the monster base values
     */
    public void addMonsterBaseValues(MonsterBaseValues monsterBaseValues) {
        this.monsterBaseValues.add(monsterBaseValues);
    }

    /**
     * Gets monster base values.
     *
     * @return the monster base values
     */
    public List<MonsterBaseValues> getMonsterBaseValues() {
        return monsterBaseValues;
    }
}
