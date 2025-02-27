package Combat;

import util.RandomNumberGenerator;

public class CombatMonster {

    private static RandomNumberGenerator randomNumberGenerator;

    private MonsterBaseValues monsterBaseValues;
    private int monsterId;
    private double maxHealthChange;
    private double atkChange;
    private double defChange;
    private double spdChange;
    private double aglChange;
    private double prcChange;


    public Element getElement() {
        return this.monsterBaseValues.getElement();
    }

    public double getAtk() {
        return this.monsterBaseValues.getAtk();
    }

    public double getDef() {
        return this.monsterBaseValues.getDef();
    }

    public double getSpd() {
        return this.monsterBaseValues.getSpd();
    }

    public boolean hasFainted() {
        return this.monsterBaseValues.getMaxHealth() - this.maxHealthChange <= 0;
    }


    private double calcActionVsTargetElementFactor(Element actionElement, Element targetElement) {
        if (actionElement == Element.WATER && targetElement == Element.FIRE
            || actionElement == Element.FIRE && targetElement == Element.EARTH
            || actionElement == Element.EARTH && targetElement == Element.WATER) {
            return 2;
        } else if (actionElement == Element.FIRE && targetElement == Element.WATER
            || actionElement == Element.EARTH && targetElement == Element.FIRE
            || actionElement == Element.WATER && targetElement == Element.EARTH) {
            return 0.5;
        } else {
            return 1;
        }
    }

    private double calcCriticalHitFactor(double userSpd) {
        double criticalHitChance = Math.pow(10, -this.getSpd() / userSpd) * 100;
        boolean criticalHit = randomNumberGenerator.decideHitInterval100(criticalHitChance);

        return criticalHit ? 2 : 1;

    }

    public boolean takeBaseDamage(int baseValue, Element actionElement, Element userElement, double userAtk, double userSpd, int actionHitRate) {

        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            double actionVsTargetElementFactor = calcActionVsTargetElementFactor(actionElement, this.getElement());
            double conditionFactor = userAtk / this.getDef();
            double criticalHitFactor = calcCriticalHitFactor(userSpd);
            double actionVsUserElementFactor = actionElement == userElement ? 1.5 : 1;
            double damageTaken = Math.ceil(actionVsTargetElementFactor * conditionFactor * criticalHitFactor * actionVsUserElementFactor);

            this.maxHealthChange -= damageTaken;
            return true;
        } else {
            return false;
        }

    }

    public boolean takeAbsoluteDamage(int value, int actionHitRate) {
        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            this.maxHealthChange -= value;
            return true;
        } else {
            return false;
        }

    }

    public boolean takeRelativeDamage(int value, int actionHitRate) {
        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            this.maxHealthChange -= Math.ceil((value * 0.01) * this.monsterBaseValues.getMaxHealth());
            return true;
        } else {
            return false;
        }
    }

}
