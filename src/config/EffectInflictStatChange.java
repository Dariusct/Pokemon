package config;

import Combat.CombatMonster;

import java.lang.annotation.Target;

public class EffectInflictStatChange extends Effect {

    private final TargetMonster targetMonster;
    private final Stat stat;
    private final int value;

    public EffectInflictStatChange(int repetitionCount, TargetMonster targetMonster, Stat stat, int value, int hitRate) {
        super(repetitionCount, hitRate);
        this.targetMonster = targetMonster;
        this.stat = stat;
        this.value = value;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target) {

    }
}
