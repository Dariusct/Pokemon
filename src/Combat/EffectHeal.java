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
    public boolean applyEffect(CombatMonster user, CombatMonster target, Element actionElement) {
        Element userElement = user.getElement();
        double userAtk = user.getAtk();
        double userSpd = user.getSpd();

        switch (this.targetMonster) {
            case USER:
                return applyEffectToUser(user, actionElement);
            case TARGET:
                switch (valueType) {
                    case BASE:
                        return target.takeBaseDamage(-value, actionElement, userElement, userAtk, userSpd, getHitRate());
                    case ABSOLUTE:
                        return target.takeAbsoluteDamage(-value, getHitRate());
                    case RELATIVE:
                        return target.takeRelativeDamage(-value, getHitRate());
                    default:
                        break;
                }
            default:
                break;
        }
        return false;
    }

    private boolean applyEffectToUser(CombatMonster user, Element actionElement) {
        Element userElement = user.getElement();
        double userAtk = user.getAtk();
        double userSpd = user.getSpd();

        return switch (valueType) {
            case BASE -> user.takeBaseDamage(-value, actionElement, userElement, userAtk, userSpd, getHitRate());
            case ABSOLUTE -> user.takeAbsoluteDamage(-value, getHitRate());
            case RELATIVE -> user.takeRelativeDamage(-value, getHitRate());
            default -> false;
        };
    }

}
