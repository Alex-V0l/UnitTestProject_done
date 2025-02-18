import Vln.CostCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class TotalPriceTest {
    @DisplayName("позитивная проверка попарного тестирования")
    @ParameterizedTest
    @Tags({@Tag("smoke"), @Tag("distanceTest")})
    @CsvSource({
            "1.0, 'большие', 'хрупкий', 'очень высокая', 880.0",
            "1.0, 'маленькие', 'нехрупкий', 'высокая', 400.0",
            "1.0, 'большие', 'хрупкий', 'повышенная', 660.0",
            "1.0, 'маленькие', 'нехрупкий', 'обычная', 400.0",
            "5.0, 'маленькие', 'хрупкий', 'обычная', 500.0",
            "5.0, 'большие', 'нехрупкий', 'очень высокая', 480.0",
            "5.0, 'маленькие', 'хрупкий', 'высокая', 700.0",
            "5.0, 'большие', 'нехрупкий', 'повышенная', 400.0",
            "20.0, 'большие', 'хрупкий', 'повышенная', 840.0",
            "20.0, 'маленькие', 'нехрупкий', 'обычная', 400.0",
            "20.0, 'большие', 'хрупкий', 'очень высокая', 1120.0",
            "20.0, 'маленькие', 'нехрупкий', 'высокая', 420.0",
            "40.0, 'большие', 'нехрупкий', 'повышенная', 600.0",
            "40.0, 'большие', 'нехрупкий', 'очень высокая',800.0",
    })

    void PairWiseTotalPriceTest(double distance, String dimensions, String fragility, String busyness, double expectedTotalPrice) {
        assertDoesNotThrow(() -> {
            CostCount costCount = new CostCount(distance, dimensions, fragility, busyness);
            double actualTotalPrice = costCount.countTotalPrice();

            assertEquals(expectedTotalPrice, actualTotalPrice, "Значения должны совпадать");
        });
    }

    @DisplayName("тест на минимальную сумму доставки")
    @Tags({@Tag("smoke"), @Tag("counTotalPriceTest"), @Tag("minimumDeliveryPrice")})
    @Test
    void minimumDeliveryPrice(){
        assertDoesNotThrow(() -> {
            CostCount costCount = new CostCount(1.0, "маленькие", "нехрупкий", "обычная");
            double actualDeliveryPrice = costCount.countTotalPrice();
            double expecxtedDeliveryPrice = 400;

            assertEquals(expecxtedDeliveryPrice, actualDeliveryPrice, "Значения должны быть одинаковыми");
        });
    }

    @DisplayName("проверка исключения при полном расчете")
    @ParameterizedTest
    @Tags({@Tag("smoke"), @Tag("distanceTest")})
    @CsvSource({
            "40.0, 'маленькие', 'хрупкий', 'высокая', 980.0",
            "40.0, 'маленькие', 'хрупкий', 'обычная', 700.0",
    })

    void TotalPriceNegativeTest(double distance, String dimensions, String fragility, String busyness) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
        CostCount costCount = new CostCount(distance, dimensions, fragility, busyness);
        costCount.countTotalPrice();
    });

        assertEquals("Хрупкий груз не может быть перевезен на расстояние более 30 км", exception.getMessage());
    }
}
