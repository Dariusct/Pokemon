package config;

import Combat.Action;
import Combat.Effect;
import Combat.EffectContinue;
import Combat.EffectDamage;
import Combat.EffectHeal;
import Combat.EffectInflictStatChange;
import Combat.EffectProtectStat;
import Combat.Element;
import Combat.Stat;
import Combat.ProtectTarget;
import Combat.TargetMonster;
import Combat.ValueType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class ConfigParsing {

    private static final String MULTIPLE_SPACES_REGEX = "\\s+";
    private static final String STRING_MONSTER_DECLARATION = "monster";
    private static final String STRING_ACTION_DECLARATION = "action";
    private static final String STRING_LOWERCASE_END_ACTION = "end action";
    private static final String STRING_LOWERCASE_DAMAGE = "damage";
    private static final String STRING_INFLICT_STAT_CHANGE = "inflictStatChange";
    private static final String STRING_PROTECT_STAT = "protectStat";
    private static final String STRING_LOWERCASE_HEAL = "heal";
    private static final String STRING_LOWERCASE_REPEAT = "repeat";
    private static final String STRING_LOWERCASE_CONTINUE = "continue";
    private static final String STRING_LOWERCASE_USER = "user";
    private static final String STRING_LOWERCASE_BASE = "base";
    private static final String STRING_LOWERCASE_REL = "rel";
    private static final String STRING_LOWERCASE_ABS = "abs";
    private static final String STRING_LOWERCASE_STATS = "stats";
    private static final String STRING_LOWERCASE_END_REPEAT = "end repeat";

    private final List<String> nonEmptyConfigFileLines = new ArrayList<>();
    private final Configuration config = new Configuration();


    public void parseNewConfigFile(String configFilePath) throws IOException {
        this.nonEmptyConfigFileLines.clear();
        Path filePath = Paths.get(configFilePath);

        try {
            List<String> configFileLines = Files.readAllLines(filePath);

            for (int i = 0; i < configFileLines.size(); i++) {
                String configFileLine = configFileLines.get(i).trim();
                if (!configFileLine.isEmpty()) {
                    this.nonEmptyConfigFileLines.add(configFileLine);
                }
            }


        } catch (IOException e) {
            System.out.println("Error reading config file");
        }
    }

    public void loadNewConfig() {
        config.clearConfig();

        boolean configurationIsDone = false;
        int currentIndex = 0;

        while(!configurationIsDone) {
            if (this.nonEmptyConfigFileLines.get(currentIndex).split(MULTIPLE_SPACES_REGEX)[0].equals(STRING_ACTION_DECLARATION)) {
                currentIndex = addNewAction(currentIndex);
            } else if (this.nonEmptyConfigFileLines.get(currentIndex).split(MULTIPLE_SPACES_REGEX)[0].equals(STRING_MONSTER_DECLARATION)) {
                addNewMonsterBaseValues(currentIndex);
                currentIndex += 1;
            }

            if(currentIndex >= this.nonEmptyConfigFileLines.size()) {
                configurationIsDone = true;
            }
        }
    }

    public void addNewMonsterBaseValues(int monsterConfigIndex) {

    }

    public int addNewAction(int actionStartIndex) {
        List<Effect> actionEffects = new ArrayList<>();


        String actionName = this.nonEmptyConfigFileLines.get(actionStartIndex).split(MULTIPLE_SPACES_REGEX)[0];
        Element actionElement = Element.valueOf(this.nonEmptyConfigFileLines.get(actionStartIndex).split(MULTIPLE_SPACES_REGEX)[1]);

        int effectIndex = actionStartIndex + 1;

        while(!this.nonEmptyConfigFileLines.get(effectIndex).equals(STRING_LOWERCASE_END_ACTION)) {
            actionEffects.addAll(addNewEffect(effectIndex));
            effectIndex += 1;
        }

        this.config.addAction(new Action(actionName, actionElement, actionEffects));

        return effectIndex + 1;
    }

    public List<Effect> addNewEffect(int effectStartIndex) {
        String firstWord = this.nonEmptyConfigFileLines.get(effectStartIndex).split(MULTIPLE_SPACES_REGEX)[0];
        List<Effect> tempEffects = new ArrayList<>();

        switch(firstWord) {
            case STRING_LOWERCASE_DAMAGE:
                tempEffects.add(addNewEffectDamage(effectStartIndex));
                break;
            case STRING_INFLICT_STAT_CHANGE:
                tempEffects.add(addNewEffectInflictStatChange(effectStartIndex));
                break;
            case STRING_PROTECT_STAT:
                tempEffects.add(addNewEffectProtectStat(effectStartIndex));
                break;
            case STRING_LOWERCASE_HEAL:
                tempEffects.add(addNewEffectHeal(effectStartIndex));
                break;
            case STRING_LOWERCASE_REPEAT:
                return addNewEffectRepeat(effectStartIndex);
            case STRING_LOWERCASE_CONTINUE:
                tempEffects.add(addNewEffectContinue(effectStartIndex));
                break;
            default:
                break;
        }

        return tempEffects;

    }

    public Effect addNewEffectDamage(int effectIndex) {
        String[] effectContents = this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX);
        TargetMonster targetMonster = effectContents[1].equals(STRING_LOWERCASE_USER) ? TargetMonster.USER : TargetMonster.TARGET;
        String valueTypeString = effectContents[2];
        ValueType valueType = ValueType.BASE;

        switch (valueTypeString) {
            case STRING_LOWERCASE_BASE -> valueType = ValueType.BASE;
            case STRING_LOWERCASE_REL -> valueType = ValueType.RELATIVE;
            case STRING_LOWERCASE_ABS -> valueType = ValueType.ABSOLUTE;
            default -> System.out.println("insert valueType error");
        }

        int value = Integer.parseInt(effectContents[3]);
        int hitRate = Integer.parseInt(effectContents[4]);

        return new EffectDamage(targetMonster, valueType, value, hitRate);
    }

    public Effect addNewEffectInflictStatChange(int effectIndex) {
        String[] effectContents = this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX);
        TargetMonster targetMonster = effectContents[1].equals(STRING_LOWERCASE_USER) ? TargetMonster.USER : TargetMonster.TARGET;
        Stat stat = Stat.valueOf(effectContents[2]);
        int value = Integer.parseInt(effectContents[3]);
        int hitRate = Integer.parseInt(effectContents[4]);

        return new EffectInflictStatChange(targetMonster, stat, value, hitRate);
    }

    public Effect addNewEffectProtectStat(int effectIndex) {
        String[] effectContents = this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX);
        ProtectTarget protectTarget = effectContents[1].equals(STRING_LOWERCASE_STATS) ? ProtectTarget.STATS : ProtectTarget.HEALTH;
        int value = Integer.parseInt(effectContents[2]);
        int hitRate = Integer.parseInt(effectContents[3]);

        return new EffectProtectStat(protectTarget, value, hitRate);
    }

    public Effect addNewEffectHeal(int effectIndex) {
        String[] effectContents = this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX);
        TargetMonster targetMonster = effectContents[1].equals(STRING_LOWERCASE_USER) ? TargetMonster.USER : TargetMonster.TARGET;
        String valueTypeString = effectContents[2];
        ValueType valueType = ValueType.BASE;

        if (valueTypeString.equals(STRING_LOWERCASE_BASE)) {
            valueType = ValueType.BASE;
        } else if (valueTypeString.equals(STRING_LOWERCASE_REL)) {
            valueType = ValueType.RELATIVE;
        } else if (valueTypeString.equals(STRING_LOWERCASE_ABS)) {
            valueType = ValueType.ABSOLUTE;
        } else {
            System.out.println("insert error");
        }

        int value = Integer.parseInt(effectContents[3]);
        int hitRate = Integer.parseInt(effectContents[4]);

        return new EffectHeal(targetMonster, valueType, value, hitRate);
    }

    public List<Effect> addNewEffectRepeat(int repeatIndex) {
        List<Effect> repeatEffects = new ArrayList<>();
        int effectsIndex = repeatIndex + 1;
        int repetitionCount = Integer.parseInt(this.nonEmptyConfigFileLines.get(repeatIndex).split(MULTIPLE_SPACES_REGEX)[1]);

        while(!this.nonEmptyConfigFileLines.get(effectsIndex).equals(STRING_LOWERCASE_END_REPEAT)) {
            List<Effect> newEffects = addNewEffect(effectsIndex);
            if (newEffects.size() == 1) {
                repeatEffects.add(newEffects.get(0));
                effectsIndex += 1;
            } else if (newEffects.size() > 1) {
                System.out.println("repeat in repeat not allowed");
            } else {
                System.out.println("Empty repeat list not allowed");
            }

        }
        return repeatEffects;
    }

    public Effect addNewEffectContinue(int effectIndex) {
        int hitRate = Integer.parseInt(this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX)[1]);

        return new EffectContinue(hitRate);
    }



}
