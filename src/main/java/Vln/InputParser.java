package Vln;

public class InputParser {
    public static String[] parseInput(String input) throws IllegalArgumentException {
        String[] parts = input.split(",\\s*");

        if (parts.length < 4) {
            throw new IllegalArgumentException("Недостаточно данных! Введите все 4 параметра.");
        }
        return parts;
    }

    public static int parseDistance(String distanceStr) throws IllegalArgumentException {
        try {
            return Integer.parseInt(distanceStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка: расстояние должно быть числом!");
        }
    }
}

