package combat;

public class EffectInflictStatChange extends Effect {

    private final TargetMonster targetMonster;
    private final Stat stat;
    private final int value;

    public EffectInflictStatChange(TargetMonster targetMonster, Stat stat, int value, int hitRate) {
        super(hitRate);
        this.targetMonster = targetMonster;
        this.stat = stat;
        this.value = value;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element acionElement) {
        return switch (this.targetMonster) {
            case USER -> user.inflictStatChange(stat, value, getHitRate());
            case TARGET -> target.inflictStatChange(stat, value, getHitRate());
            default -> false;
        };
    }

}
