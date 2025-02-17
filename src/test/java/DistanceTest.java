import Vln.CostCount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceTest {

    @DisplayName("проверка значений из классов эквивалентности и границ")
    @ParameterizedTest
    @Tags({@Tag("smoke"), @Tag("distanceTest")})
    @ValueSource(ints = {1, 2, 3, 10, 15, 30, 31, 45})
    void addPriceForDistanceTest(int distance) {
        CostCount costCount = new CostCount(distance, "оооо", "ааа", "иии");
        int expectedPriceForDistance;
        int actualPriceForDistance = costCount.addPriceForDistance(distance);

        if (distance <= 2) {
            expectedPriceForDistance = 50;
        } else if (distance <= 10) {
            expectedPriceForDistance = 100;
        } else if (distance <= 30) {
            expectedPriceForDistance = 200;
        } else {
            expectedPriceForDistance = 300;
        }
        assertEquals(expectedPriceForDistance, actualPriceForDistance, "Значения должны совпадать");
    }

    @DisplayName("невозможность расчитать стоимость при нулевом и отрицательном расстоянии")
    @ParameterizedTest
    @Tags({@Tag("smoke"), @Tag("distanceTest"), @Tag("negative"), @Tag("exception")})
    @ValueSource(ints = {0, -1, -5})
    void addPriceForDistance_InvalidDistance_ThrowsException(int distance) {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CostCount costCount = new CostCount(distance, "оооо", "ааа", "иии");
            costCount.addPriceForDistance(distance);
        });

        assertEquals("Расстояние должно быть больше 0.", exception.getMessage());
    }
}















