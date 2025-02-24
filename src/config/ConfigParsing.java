package config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class ConfigParsing {

    private static final String MULTIPLE_SPACES_REGEX = "\\s+";
    private static final String STRING_LOWERCASE_ACTION = "action";
    private static final String STRING_LOWERCASE_END_ACTION = "end action";

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

    public int addNewAction(int currentIndex) {
        List<Effect> tempEffects = new ArrayList<>();


        String actionName = this.nonEmptyConfigFileLines.get(currentIndex).split(MULTIPLE_SPACES_REGEX)[0];
        Element actionElement = Element.valueOf(this.nonEmptyConfigFileLines.get(currentIndex).split(MULTIPLE_SPACES_REGEX)[1]);

        int effectIndex = currentIndex + 1;

        while(!this.nonEmptyConfigFileLines.get(effectIndex).equals(STRING_LOWERCASE_END_ACTION)) {
            tempEffects.add(addNewEffect(effectIndex));
            effectIndex += 1;
        }

        return effectIndex;


    }

    public Effect addNewEffect(int currentIndex) {


    }

}
