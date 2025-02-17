import Vln.CostCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CountTotalPriceTest {
    @DisplayName("позитивный тест расчета общей стоимости")
    @Tags({@Tag("smoke"), @Tag("counTotalPriceTest")})
    @Test
    void countTotalPriceTest() {
        assertDoesNotThrow(() -> {
            CostCount costCount = new CostCount(20, "большие", "хрупкий", "обычная");
            double actualTotalPrice = costCount.countTotalPrice();
            double expecxtedTotalPrice = 700;

            assertEquals(expecxtedTotalPrice, actualTotalPrice, "Значения должны быть одинаковыми");
        });
    }

    @DisplayName("тест на минимальную сумму доставки")
    @Tags({@Tag("smoke"), @Tag("counTotalPriceTest"), @Tag("minimumDeliveryPrice")})
    @Test
    void minimumDeliveryPrice(){
        assertDoesNotThrow(() -> {
            CostCount costCount = new CostCount(2, "маленькие", "нехрупкий", "обычная");
            double actualDeliveryPrice = costCount.countTotalPrice();
            double expecxtedDeliveryPrice = 400;

            assertEquals(expecxtedDeliveryPrice, actualDeliveryPrice, "Значения должны быть одинаковыми");
        });
    }
}
