package util;

import java.util.Random;

public class RandomNumberGenerator {

    private final Random random;
    private final boolean debugMode;

    public RandomNumberGenerator(Long seed) {
        this.random = new Random(seed);
        this.debugMode = false;
    }

    public RandomNumberGenerator(boolean debugMode) {
        this.random = new Random();
        this.debugMode = debugMode;
    }

    public RandomNumberGenerator() {
        this.random = new Random();
        this.debugMode = false;
    }



    public boolean decideHitInterval100(double probability) {
        return this.random.nextDouble(0, 101) < probability;
    }
}
