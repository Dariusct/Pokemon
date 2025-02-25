package config;

import Combat.CombatMonster;

public class EffectHeal extends Effect {

    private final TargetMonster targetMonster;
    private final ValueType valueType;
    private final int value;

    public EffectHeal(int repeatCount, TargetMonster targetMonster, ValueType valueType, int value, int hitRate) {
        super(repeatCount, hitRate);
        this.targetMonster = targetMonster;
        this.valueType = valueType;
        this.value = value;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target) {

    }
}
