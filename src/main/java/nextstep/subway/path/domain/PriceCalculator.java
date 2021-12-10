package nextstep.subway.path.domain;

public class PriceCalculator {

    private static final int BASE_PRICE = 1250;

    private PriceCalculator() {
        throw new UnsupportedOperationException();
    }

    public static int process(AgeType ageType, ShortestPath shortestPath) {
        int price = calculateDistancePrice(shortestPath.getDistance()) + shortestPath.getAdditionalPrice();

        return ageType.getDiscountPrice(price);
    }

    private static int calculateDistancePrice(int distance) {
        return BASE_PRICE + PathSector.getOverPrice(distance);
    }
}
