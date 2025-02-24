package config;

import java.util.HashMap;
import java.util.Map;

public class MonsterBaseValues {

    public final Map<String, Action> actions = new HashMap<String, Action>();

    private final String name;
    private final Element element;
    private final double maxHealth;
    private final double atk;
    private final double def;
    private final double spd;
    private final double agl;
    private final double prc;

    public MonsterBaseValues(String name, Element element, double maxHealth, double atk, double def,
                             double spd) {
        this.name = name;
        this.element = element;
        this.maxHealth = maxHealth;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.agl = 1;
        this.prc = 1;
    }

    public String getName() {
        return this.name;
    }

    public Element getElement() {
        return this.element;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getAtk() {
        return this.atk;
    }

    public double getDef() {
        return this.def;
    }

    public double getSpd() {
        return this.spd;
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

}
