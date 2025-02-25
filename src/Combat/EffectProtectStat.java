package Combat;

public class EffectProtectStat extends Effect {

    private final ProtectTarget protectTarget;
    private final int numOfRounds;

    public EffectProtectStat(ProtectTarget protectTarget, int numOfRounds, int hitRate) {
        super(hitRate);
        this.protectTarget = protectTarget;
        this.numOfRounds = numOfRounds;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target) {

    }
}
