import Vln.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputParserTest {
    @DisplayName("тест на корректное разделение строки")
    @Tags({@Tag("smoke"), @Tag("parseInputTest")})
    @Test
    void parseInputTest() {
        String input = "10, большие, хрупкий, повышенная";

        String[] expectedSplitResult = {"10", "большие", "хрупкий", "повышенная"};
        String[] actualSplitterResult = input.split(",\\s*");

        assertEquals(Arrays.toString(expectedSplitResult), Arrays.toString(actualSplitterResult), "Содержание массивов должно быть одинаковым");
    }

    @DisplayName("тест на недостаточное количество аргументов")
    @Tags({@Tag("smoke"), @Tag("parseInputTest"), @Tag("exception")})
    @Test
    void testParseInput_insufficientData() {
        String input = "10, большие, хрупкий";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parseInput(input);
        });

        assertEquals("Недостаточно данных! Введите все 4 параметра.", exception.getMessage());
    }

    @DisplayName("корректный парсинг числа")
    @Tags({@Tag("smoke"), @Tag("parseDistanceTest")})
    @Test
    void parseDistanceTest() {
        String input = "10";
        int expectedParasingResult = 10;
        int actualParsingResult = InputParser.parseDistance(input);

        assertEquals(expectedParasingResult, actualParsingResult, "Значения должны быть одинаковыми");
    }

    @DisplayName("исключение на некорректные данные")
    @Tags({@Tag("smoke"), @Tag("parseDistanceTest"), @Tag("exception")})
    @Test
    void parseDistanceExceptionTest() {
        String input = "хрупкий";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parseDistance(input);
        });

        assertEquals("Ошибка: расстояние должно быть числом!", exception.getMessage());
    }
}

