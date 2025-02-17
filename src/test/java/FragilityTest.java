import Vln.CostCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class FragilityTest {
    @DisplayName("проверка добавления цены за хрупкость")
    @Tags({@Tag("smoke"), @Tag("fragilityTest")})
    @ParameterizedTest
    @ValueSource(strings = {"хрупкий", "нехрупкий"})
    void addPriceForFragilityTest(String fragility) {
        CostCount costCount = new CostCount(10, "большие", fragility, "повышенная");

        assertDoesNotThrow(() -> {
            int actualPriceForFragility = costCount.addPriceForFragility(fragility);
            int expectedPriceForFragility = "хрупкий".equals(fragility) ? 300 : 0;

            assertEquals(expectedPriceForFragility, actualPriceForFragility, "Суммы должны совпадать");
        });
    }

    @DisplayName("отказ доставки на расстояние более 30 км при хрупком грузе")
    @Tags({@Tag("smoke"), @Tag("fragilityTest"), @Tag("exception")})
    @Test
    void refusalOfDeliveryTest_Invalid_Distance_and_Fragility(){
    CostCount costCount = new CostCount(31, "большие", "хрупкий", "обычная");

    Exception exception = assertThrows(IllegalArgumentException.class, ()->
            costCount.addPriceForFragility("хрупкий"));

    assertEquals("Хрупкий груз не может быть перевезен на расстояние более 30 км", exception.getMessage());
    }

    @DisplayName("исключение при некорректном наименовании хрупкости")
    @Tags({@Tag("smoke"), @Tag("fragilityTest"), @Tag("negative"), @Tag("exception")})
    @ParameterizedTest
    @ValueSource(strings = {"хрупкая", "нехрупкие"})
    void addPriceForFragility_InvalidFragility_ThrowsException(String fragility){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            CostCount costCount = new CostCount(10, "большие", fragility, "обычная");
            costCount.addPriceForFragility(fragility);
        });

        assertEquals("Некорректное наименование хрупкости. Введите \"хрупкий\" или \"нехрупкий\"", exception.getMessage());
    }



}



