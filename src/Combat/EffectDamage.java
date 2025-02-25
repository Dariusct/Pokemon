package Combat;

public class EffectDamage extends Effect {

    private final TargetMonster targetMonster;
    private final int value;
    private final ValueType valueType;

    public EffectDamage(TargetMonster targetMonster, ValueType valueType, int value, int hitRate) {
        super(hitRate);
        this.targetMonster = targetMonster;
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
