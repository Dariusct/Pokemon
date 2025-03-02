package Combat;

public class EffectProtect extends Effect {

    private final ProtectTarget protectTarget;
    private final int numOfRounds;

    public EffectProtect(ProtectTarget protectTarget, int numOfRounds, int hitRate) {
        super(hitRate);
        this.protectTarget = protectTarget;
        this.numOfRounds = numOfRounds;
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element actionElement) {
        return switch (protectTarget) {
            case STATS -> user.protectStats(numOfRounds, getHitRate());
            case HEALTH -> user.protectHealth(numOfRounds, getHitRate());
            default -> false;
        };
    }
}
