package Combat;

public class Effect {

    private final int hitRate;

    public Effect(int hitRate) {
        this.hitRate = hitRate;
    }
    public boolean applyEffect(CombatMonster user, CombatMonster target) {
        return true;
    }
}
