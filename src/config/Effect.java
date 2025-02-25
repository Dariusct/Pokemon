package config;

import Combat.CombatMonster;

public abstract class Effect {

    private final int hitRate;

    public Effect(int hitRate, int repetitionCount) {
        this.hitRate = hitRate;
    }
    abstract boolean applyEffect(CombatMonster user, CombatMonster target);
}
