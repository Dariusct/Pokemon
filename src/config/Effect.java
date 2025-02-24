package config;

import Combat.CombatMonster;

public abstract class Effect {

    private final int hitRate;
    private final int repetitionCount;

    public Effect(int hitRate, int repetitionCount) {
        this.hitRate = hitRate;
        this.repetitionCount = repetitionCount;
    }
    abstract boolean applyEffect(CombatMonster user, CombatMonster target);
}
