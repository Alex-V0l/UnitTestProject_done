package Vln;

public class CostCount {

    final int BASECOST = 400;
    private int distance;
    private String dimensions;
    private String fragility;
    private String busyness;

    public CostCount(int distance, String dimensions, String fragility, String busyness) {
        this.distance = distance;
        this.dimensions = dimensions;
        this.fragility = fragility;
        this.busyness = busyness;
    }

    public int addPriceForDistance(Integer distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть больше 0.");
        }
        if (distance <= 2) {
            return 50;
        } else if (distance <= 10) {
            return 100;
        } else if (distance <= 30) {
            return 200;
        } else {
            return 300;
        }
    }

    public int addPriceForDimensions(String dimensions) {
        if ("большие".equals(dimensions)) {
            return 200;
        } else if ("маленькие".equals(dimensions)) {
            return 100;
        } else {
            throw new IllegalArgumentException("Некорректное наименование габаритов. Введите \"большие\" или \"маленькие\"");
        }
    }

    public int addPriceForFragility(String fragility) throws Exception {
        if ("хрупкий".equals(fragility) && distance > 30) {
            throw new IllegalArgumentException("Хрупкий груз не может быть перевезен на расстояние более 30 км");
        }
        if ("хрупкий".equals(fragility)) {
            return 300;
        } else if ("нехрупкий".equals(fragility)) {
            return 0;
        } else {
            throw new IllegalArgumentException("Некорректное наименование хрупкости. Введите \"хрупкий\" или \"нехрупкий\"");
        }
    }

    public double addMultiplierForBusyness(String busyness) throws Exception {
        if ("очень высокая".equals(busyness)) {
            return 1.6;
        } else if ("высокая".equals(busyness)) {
            return 1.4;
        } else if ("повышенная".equals(busyness)) {
            return 1.2;
        } else if ("обычная".equals(busyness)) {
            return 1.0;
        } else {
            throw new IllegalArgumentException("Некорректное значение загруженности. Введите \"очень высокая\", \"высокая\", \"повышенная\" или \"обычная\".");
        }
    }

    public double countTotalPrice() throws Exception {
        double totalPrice = ((addPriceForDistance(distance)
                        + addPriceForDimensions(dimensions)
                        + addPriceForFragility(fragility))
                        * addMultiplierForBusyness(busyness));

        return Math.max(totalPrice, BASECOST);
    }
}







