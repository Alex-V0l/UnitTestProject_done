package Vln;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите расстояние до пункта назначения в километрах, габариты (большие/маленькие), " +
                "является ли груз хрупким (хрупкий/нехрупкий), текущая загруженность службы доставки (очень высокая/высокая/" +
                "повышенная/обычная)");

        String input = scanner.nextLine();

        try {
            String[] parts = InputParser.parseInput(input);

            double distance = InputParser.parseDistance(parts[0]);
            String dimensions = parts[1];
            String fragility = parts[2];
            String busyness = parts[3];

            CostCount cost = new CostCount((int) distance, dimensions, fragility, busyness);

            double totalPrice = cost.countTotalPrice();
            System.out.println("Общая стоимость доставки составляет " + totalPrice);

        } catch (NumberFormatException e) {
            System.err.println("Ошибка: расстояние должно быть числом!");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}