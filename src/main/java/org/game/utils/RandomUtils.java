package org.game.utils;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RandomUtils {

    public static int getInt(int min, int max) {
        if (min >= max) return min;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean checkChance(double chance) {
        return ThreadLocalRandom.current().nextDouble() < chance;
    }

    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(getInt(0, list.size() - 1));
    }

}
