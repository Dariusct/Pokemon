package combat;

public class Effect {

    private final int hitRate;

    public Effect(int hitRate) {
        this.hitRate = hitRate;
    }
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element actionElement) {
        System.out.println("Wrong parameters given to Effect");
        return false;
    }

    public int getHitRate() {
        return hitRate;
    }
}
