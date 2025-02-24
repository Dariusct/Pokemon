package config;

import Combat.CombatMonster;

public class EffectStatusConditionWet extends Effect {

    public EffectStatusConditionWet(int hitRate, int repetitionCount) {
        super(hitRate, repetitionCount);
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target) {

    }
}

