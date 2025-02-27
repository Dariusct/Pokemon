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
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element actionElement) {
        Element userElement = user.getElement();
        double userAtk = user.getAtk();
        double userSpd = user.getSpd();

        switch (this.targetMonster) {
            case USER:
                return applyEffectToUser(user);
                break;
            case TARGET:
                switch (valueType) {
                    case BASE:
                        return target.takeBaseDamage(value, actionElement, userElement, userAtk, userSpd, getHitRate());
                        break;
                    case ABSOLUTE:
                        return target.takeAbsoluteDamage(value, getHitRate());
                        break;
                    case RELATIVE:
                        return target.takeRelativeDamage(value, getHitRate());
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    private boolean applyEffectToUser(CombatMonster user) {
        switch (valueType) {
            case BASE:
                return true;
                break;
            case ABSOLUTE:
                return true;
                break;
            case RELATIVE:
                return true;
                break;
            default:
        }
    }


}
