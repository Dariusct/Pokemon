package combat;

public class EffectStatusCondition extends Effect {

    private final TargetMonster targetMonster;
    private final StatusCondition statusCondition;

    public EffectStatusCondition(TargetMonster targetMonster, StatusCondition statusCondition, int hitRate) {
        super(hitRate);
        this.targetMonster = targetMonster;
        this.statusCondition = statusCondition;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element actionElement) {
        return switch (this.targetMonster) {
            case TARGET -> target.applyStatusCondition(getHitRate(), statusCondition);
            case USER -> user.applyStatusCondition(getHitRate(), statusCondition);
        };
    }

}

