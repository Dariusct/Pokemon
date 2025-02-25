package config;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class ConfigParsing {

    private static final int BASE_REPEAT_COUNT = 1;
    private static final String MULTIPLE_SPACES_REGEX = "\\s+";
    private static final String STRING_LOWERCASE_ACTION = "action";
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
            if(this.nonEmptyConfigFileLines.get(currentIndex).split(MULTIPLE_SPACES_REGEX)[0].equals(STRING_LOWERCASE_ACTION)) {
                currentIndex = addNewAction(currentIndex);
            }

            if(currentIndex >= this.nonEmptyConfigFileLines.size()) {
                configurationIsDone = true;
            }
        }
    }

    public int addNewAction(int actionStartIndex) {
        List<Effect> tempEffects = new ArrayList<>();


        String actionName = this.nonEmptyConfigFileLines.get(actionStartIndex).split(MULTIPLE_SPACES_REGEX)[0];
        Element actionElement = Element.valueOf(this.nonEmptyConfigFileLines.get(actionStartIndex).split(MULTIPLE_SPACES_REGEX)[1]);

        int effectIndex = actionStartIndex + 1;

        while(!this.nonEmptyConfigFileLines.get(effectIndex).equals(STRING_LOWERCASE_END_ACTION)) {
            tempEffects.add(addNewEffect(effectIndex));
            effectIndex += 1;
        }

        return effectIndex;
    }

    public Effect addNewEffect(int effectStartIndex) {
        String firstWord = this.nonEmptyConfigFileLines.get(effectStartIndex).split(MULTIPLE_SPACES_REGEX)[0];

        switch(firstWord) {
            case STRING_LOWERCASE_DAMAGE:
                return addNewEffectDamage(effectStartIndex);
                break;
            case STRING_INFLICT_STAT_CHANGE:
                return addNewEffectInflictStatChange(effectStartIndex);
                break;
            case STRING_PROTECT_STAT:
                return addNewEffectProtectStat(effectStartIndex);
                break;
            case STRING_LOWERCASE_HEAL:
                return addNewEffectHeal(effectStartIndex);
                break;
            case STRING_LOWERCASE_REPEAT:
                return addNewEffectRepeat(effectStartIndex);
                break;
            case STRING_LOWERCASE_CONTINUE:
                return addNewEffectCOntinue(effectStartIndex);
                break;
            default:
                break;
        }

    }

    public Effect addNewEffectDamage(int effectIndex) {
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

        return new EffectDamage(BASE_REPEAT_COUNT, targetMonster, valueType, value, hitRate);
    }

    public Effect addNewEffectInflictStatChange(int effectIndex) {
        String[] effectContents = this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX);
        TargetMonster targetMonster = effectContents[1].equals(STRING_LOWERCASE_USER) ? TargetMonster.USER : TargetMonster.TARGET;
        Stat stat = Stat.valueOf(effectContents[2]);
        int value = Integer.parseInt(effectContents[3]);
        int hitRate = Integer.parseInt(effectContents[4]);

        return new EffectInflictStatChange(BASE_REPEAT_COUNT, targetMonster, stat, value, hitRate);
    }

    public Effect addNewEffectProtectStat(int effectIndex) {
        String[] effectContents = this.nonEmptyConfigFileLines.get(effectIndex).split(MULTIPLE_SPACES_REGEX);
        ProtectTarget protectTarget = effectContents[1].equals(STRING_LOWERCASE_STATS) ? ProtectTarget.STATS : ProtectTarget.HEALTH;
        int value = Integer.parseInt(effectContents[2]);
        int hitRate = Integer.parseInt(effectContents[3]);

        return new EffectProtectStat(BASE_REPEAT_COUNT, protectTarget, value, hitRate);
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

        return new EffectHeal(BASE_REPEAT_COUNT, targetMonster, valueType, value, hitRate);
    }

    public Effect addNewEffectRepeat(int effectIndex) {

    }

    public Effect addNewEffectContinue(int effectIndex) {

    }



}
