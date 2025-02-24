package config;

import Combat.CombatMonster;

public class EffectDamage extends Effect {

    private final TargetType targetType;
    private final int value;
    private final ValueType valueType;

    public EffectDamage(int hitRate, int repetitionCount, TargetType targetType, int value, ValueType valueType) {
        super(hitRate, repetitionCount);
        this.targetType = targetType;
        this.value = value;
        this.valueType = valueType;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target) {
        switch (valueType) {
            case BASE:
                break;
            case ABSOLUTE:
                break;
            case RELATIVE:
                break;
            default:
        }
    }
}
