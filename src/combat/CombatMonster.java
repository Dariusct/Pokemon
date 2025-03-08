package combat;

import util.RandomNumberGenerator;

public class CombatMonster {

    private static RandomNumberGenerator randomNumberGenerator;

    private final MonsterBaseValues monsterBaseValues;
    private double maxHealthChange;
    private double atkChange;
    private double defChange;
    private double spdChange;
    private double aglChange;
    private double prcChange;
    private boolean isStatProtectActive;
    private boolean isHealthProtectActive;
    private boolean isWet;
    private boolean isBurning;
    private boolean isInQuicksand;
    private boolean isSleeping;
    private boolean isStatusConditionActive;
    private int numOfProtectRounds;

    public CombatMonster(MonsterBaseValues monsterBaseValues) {
        this.monsterBaseValues = monsterBaseValues;
        this.maxHealthChange = 0;
        this.atkChange = 0;
        this.defChange = 0;
        this.spdChange = 0;
        this.aglChange = 0;
        this.prcChange = 0;
        this.isStatProtectActive = false;
        this.isHealthProtectActive = false;
        this.isWet = false;
        this.isBurning = false;
        this.isInQuicksand = false;
        this.isSleeping = false;
        this.isStatusConditionActive = false;
        this.numOfProtectRounds = 0;

    }


    public Element getElement() {
        return this.monsterBaseValues.getElement();
    }

    public double getEffectiveAtk() {
        double statFactor = (double) (2 + this.atkChange) / 2;
        double baseAtk = this.monsterBaseValues.getAtk();
        double burnFactor = 1;
        if (isBurning) {
            burnFactor = 0.75;
        }
        return statFactor * baseAtk * burnFactor;
    }

    public double getEffectiveDef() {
        double statFactor = (double) (2 + this.defChange) / 2;
        double baseDef = this.monsterBaseValues.getDef();
        double wetFactor = 1;
        if (isWet) {
            wetFactor = 0.75;
        }
        return statFactor * baseDef * wetFactor;
    }

    public double getEffectiveSpd() {
        double statFactor = (double) (2 + this.spdChange) / 2;
        double baseDef = this.monsterBaseValues.getDef();
        double quicksandFactor = 1;
        if (isInQuicksand) {
            quicksandFactor = 0.75;
        }
        return statFactor * baseDef * quicksandFactor;
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
        double criticalHitChance = Math.pow(10, -this.getEffectiveSpd() / userSpd) * 100;
        boolean criticalHit = randomNumberGenerator.decideHitInterval100(criticalHitChance);

        return criticalHit ? 2 : 1;

    }

    public boolean applyAction(String actionName, CombatMonster target) {
        Action actionTOApply = this.monsterBaseValues.getAction(actionName);
        return actionTOApply.applyAction(this, target);
    }

    public boolean takeBaseDamage(int baseValue, Element actionElement, Element userElement, double userAtk, double userSpd, int actionHitRate) {

        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            if (!checkHealthProtect() || baseValue <= 0) {
                double actionVsTargetElementFactor = calcActionVsTargetElementFactor(actionElement, this.getElement());
                double conditionFactor = userAtk / (this.getEffectiveDef());
                double criticalHitFactor = calcCriticalHitFactor(userSpd);
                double actionVsUserElementFactor = actionElement == userElement ? 1.5 : 1;
                double damageTaken = Math.ceil(actionVsTargetElementFactor * conditionFactor * criticalHitFactor * actionVsUserElementFactor);

                this.maxHealthChange -= damageTaken;
            }
            return true;
        } else {
            return false;
        }

    }

    public boolean takeAbsoluteDamage(int value, int actionHitRate) {
        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            if (!checkHealthProtect() || value <= 0) {
                this.maxHealthChange -= value;
            }
            return true;
        } else {
            return false;
        }

    }

    public boolean takeRelativeDamage(int value, int actionHitRate) {
        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            if (!checkHealthProtect() || value <= 0) {
                this.maxHealthChange -= Math.ceil((value * 0.01) * this.monsterBaseValues.getMaxHealth());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean applyStatusCondition(int actionHitRate, StatusCondition statusCondition) {
        if (!isStatusConditionActive && randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            switch (statusCondition) {
                case WET: this.isWet = true;
                    break;
                case BURN: this.isBurning = true;
                    break;
                case QUICKSAND: this.isInQuicksand = true;
                    break;
                case SLEEP: this.isSleeping = true;
                    break;
                default: break;
            }
            this.isStatusConditionActive = true;
            return true;
        } else {
            return false;
        }
    }

    public void burn() {
        this.takeRelativeDamage(10, 100);
    }

    public boolean getIsSleeping() {
        return this.isSleeping;
    }


    public void checkStatusCondition() {
        if (isStatusConditionActive) {
            if (randomNumberGenerator.decideHitInterval100((double) (1 / 3) * 100)) {
                this.isStatusConditionActive = false;
                this.isWet = false;
                this.isBurning = false;
                this.isInQuicksand = false;
                this.isSleeping = false;
            }
        }
    }

    public boolean inflictStatChange(Stat stat, int value, int actionHitRate) {
        int amountToChange = value;
        if (value < -5) {
            amountToChange = -5;
        } else if (value > 5) {
            amountToChange = 5;
        }

        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            if (!checkStatProtect()) {
                switch (stat) {
                    case ATK:
                        this.atkChange += amountToChange;
                        break;
                    case DEF:
                        this.defChange += amountToChange;
                        break;
                    case SPD:
                        this.spdChange += amountToChange;
                        break;
                    case PRC:
                        this.prcChange += amountToChange;
                        break;
                    case AGL:
                        this.aglChange += amountToChange;
                        break;
                    default:
                        break;
                }
            }
            return true;

        } else {
            return false;
        }
    }

    public boolean protectHealth(int numOfRounds, int actionHitRate) {
        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            this.isHealthProtectActive = true;
            this.numOfProtectRounds = numOfRounds;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkHealthProtect() {
        if (this.isHealthProtectActive && this.numOfProtectRounds > 0) {
            this.numOfProtectRounds--;
            if (this.numOfProtectRounds <= 0) {
                this.isHealthProtectActive = false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean protectStats(int numOfRounds, int actionHitRate) {
        if (randomNumberGenerator.decideHitInterval100(actionHitRate)) {
            this.isStatProtectActive = true;
            this.numOfProtectRounds = numOfRounds;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkStatProtect() {
        if (this.isStatProtectActive && this.numOfProtectRounds > 0) {
            this.numOfProtectRounds--;
            if (this.numOfProtectRounds <= 0) {
                this.isStatProtectActive = false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean continueAction(int actionHitRate) {
        return randomNumberGenerator.decideHitInterval100(actionHitRate);
    }





}
