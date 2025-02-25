package Combat;

public class EffectHeal extends Effect {

    private final TargetMonster targetMonster;
    private final ValueType valueType;
    private final int value;

    public EffectHeal(TargetMonster targetMonster, ValueType valueType, int value, int hitRate) {
        super(hitRate);
        this.targetMonster = targetMonster;
        this.valueType = valueType;
        this.value = value;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target) {

    }
}
