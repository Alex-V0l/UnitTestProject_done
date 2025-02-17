package Vln;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите расстояние до пункта назначения в километрах, габариты (большие/маленькие), " +
                "является ли груз хрупким (хрупкий/нехрупкий), текущая загруженность службы доставки (очень высокая/высокая/" +
                "повышенная/обычная)");

        String input = scanner.nextLine();
        String[] parts = input.split(",\\s*");

        try {
            if (parts.length < 4) {
                throw new IllegalArgumentException("Недостаточно данных! Введите все 4 параметра.");
            }

            int distance = Integer.parseInt(parts[0]);
            String dimensions = parts[1];
            String fragility = parts[2];
            String busyness = parts[3];

            if (distance <= 0) {
                throw new IllegalArgumentException("Расстояние должно быть больше 0.");
            }

            CostCount cost = new CostCount(distance, dimensions, fragility, busyness);

            int totalPrice = cost.countTotalPrice();
            System.out.println("Общая стоимость доставки составляет " + totalPrice);

        } catch (NumberFormatException e) {
            System.err.println("Ошибка: расстояние должно быть числом!");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}