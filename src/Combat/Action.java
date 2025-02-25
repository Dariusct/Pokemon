package Combat;

import java.util.List;

public class Action {

    public final String name;
    public final Element element;
    public final List<Effect> effects;


    public Action(String name, Element element, List<Effect> effects) {
        this.name = name;
        this.element = element;
        this.effects = effects;
    }

    public String getName() {
        return this.name;
    }

    public Element getElement() {
        return this.element;
    }

    public boolean applyAction(CombatMonster user, CombatMonster target) {

        boolean isFirst = true;
        for (Effect effect : effects) {
            boolean isSuccessful = effect.applyEffect(user, target);
            if (isFirst && !isSuccessful) {
                return false;
            }
            isFirst = false;
        }
        return true;
    }

}
