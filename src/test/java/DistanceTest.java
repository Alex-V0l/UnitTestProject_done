import Vln.CostCount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceTest {

    @DisplayName("проверка значений из классов эквивалентности и границ")
    @ParameterizedTest
    @Tags({@Tag("smoke"), @Tag("distanceTest")})
    @ValueSource(doubles = {1.0, 2.0, 3.0, 10.0, 15.5, 30.0, 31.0, 45.0})
    void addPriceForDistanceTest(double distance) {
        CostCount costCount = new CostCount(distance, "оооо", "ааа", "иии");
        double expectedPriceForDistance;
        double actualPriceForDistance = costCount.addPriceForDistance(distance);

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
    @ValueSource(doubles = {0.0, -1.0, -5.5})
    void addPriceForDistance_InvalidDistance_ThrowsException(double distance) {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CostCount costCount = new CostCount(distance, "оооо", "ааа", "иии");
            costCount.addPriceForDistance(distance);
        });

        assertEquals("Расстояние должно быть больше 0.", exception.getMessage());
    }
}















