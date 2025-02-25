package config;

import Combat.CombatMonster;

public class EffectProtectStat extends Effect {

    private final ProtectTarget protectTarget;
    private final int numOfRounds;

    public EffectProtectStat(int repeatCount, ProtectTarget protectTarget, int numOfRounds, int hitRate) {
        super(repeatCount, hitRate);
        this.protectTarget = protectTarget;
        this.numOfRounds = numOfRounds;
    }

    public boolean applyEffect(CombatMonster user, CombatMonster target) {

    }
}
