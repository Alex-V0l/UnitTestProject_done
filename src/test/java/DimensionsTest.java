import Vln.CostCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DimensionsTest {

    @DisplayName("проверка добавления стоимости в зависимости от габаритов")
    @Tags({@Tag("smoke"), @Tag("dimensionsTest")})
    @ParameterizedTest
    @ValueSource(strings = {"большие", "маленькие"})
    void addPriceForDimensionTest(String dimensions){
    CostCount costCount1 = new CostCount(10, dimensions, "хрупкий", "повышенная");

    int actualPriceForDimensions = costCount1.addPriceForDimensions(dimensions);

    int expectedPriceForDimensions = "большие".equals(dimensions) ? 200 : 100;

    assertEquals(expectedPriceForDimensions, actualPriceForDimensions, "Суммы должны совпадать");
    }

    @DisplayName("исключение при некорректном наименовании габаритов")
    @Tags({@Tag("smoke"), @Tag("dimensionsTest"), @Tag("negative"), @Tag("exception")})
    @ParameterizedTest
    @ValueSource(strings = {"большой", "маленькая"})
    void addPriceForDimensions_InvalidDimension_ThrowsException(String dimensions){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            CostCount costCount = new CostCount(10, dimensions, "хрупкий", "повышенная");
            costCount.addPriceForDimensions(dimensions);
        });

        assertEquals("Некорректное наименование габаритов. Введите \"большие\" или \"маленькие\"", exception.getMessage());
    }



}
