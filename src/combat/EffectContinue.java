package combat;

public class EffectContinue extends Effect {

    public EffectContinue(int hitRate) {
        super(hitRate);
    }

    @Override
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element actionElement) {
        return user.continueAction(getHitRate());
    }
}
