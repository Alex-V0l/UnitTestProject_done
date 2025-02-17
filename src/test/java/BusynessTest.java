import Vln.CostCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BusynessTest {
    @DisplayName("проверка добавления цены за занятость")
    @Tags({@Tag("smoke"), @Tag("busynessTest")})
    @ParameterizedTest
    @ValueSource(strings = {"очень высокая", "высокая", "повышенная", "обычная"})
    void addMultiplierForBusynessTest(String busyness){
        CostCount costCount = new CostCount(10, "большие", "хрупкий", busyness);
        assertDoesNotThrow ( () ->{
            double actualMultiplierForBusyness = costCount.addMultiplierForBusyness(busyness);
            double expectedMultiplierForBusyness = 0;
            if ("очень высокая".equals(busyness)) {
                expectedMultiplierForBusyness = 1.6;
            } else if ("высокая".equals(busyness)) {
                expectedMultiplierForBusyness = 1.4;
            } else if ("повышенная".equals(busyness)) {
                expectedMultiplierForBusyness = 1.2;
            } else if ("обычная".equals(busyness)) {
                expectedMultiplierForBusyness = 1.0;
            }

            assertEquals(expectedMultiplierForBusyness, actualMultiplierForBusyness,
                    "Коэффициент загруженности должен совпадать");
        });
    }

    @DisplayName("некорректное наименование степени загруженности")
    @Tags({@Tag("smoke"), @Tag("busynessTest"), @Tag("negative"), @Tag("exception")})
    @ParameterizedTest
    @ValueSource(strings = {"очень высокий", "высокое", "повышена", "простая"})
    void addPriceForBusyness_InvalidBusyness_ThrowsException(String busyness){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            CostCount costCount = new CostCount(10, "большие", "хрупкий", busyness);
            costCount.addMultiplierForBusyness(busyness);
        });

        assertEquals("Некорректное значение загруженности. Введите \"очень высокая\", \"высокая\", \"повышенная\" или \"обычная\".", exception.getMessage());
    }


}
