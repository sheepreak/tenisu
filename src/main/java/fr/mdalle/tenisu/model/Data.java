
package fr.mdalle.tenisu.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public record Data(int age, int height, List<Integer> last, int points, int rank, int weight) {
    public double bmi() {
        return BigDecimal.valueOf((((double) weight / 1000)) / Math.pow((double) height/100, 2)).setScale(1, RoundingMode.HALF_DOWN).doubleValue();
    }

    public int wins() {
        return (int) last.stream().filter(this::isWin).count();
    }

    private boolean isWin(Integer value) {
        return value == 1;
    }
}
