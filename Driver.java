public class Driver {
    public static void main(String[] args) {
        GridTracker gridTracker = new GridTracker("inputs/day1.txt");
        BathroomCode bathroomCode = new BathroomCode("inputs/day2.txt");
        ShapeAnalyzer shapeAnalyzer = new ShapeAnalyzer("inputs/day3.txt");

        System.out.println("Problem 1.1: " + gridTracker.getEndDistance());
        System.out.println("Problem 1.2: " + gridTracker.getTrueDistance());
        System.out.println();

        System.out.println("Problem 2.1: " + bathroomCode.getBaseCode());
        System.out.println("Problem 2.2: " + bathroomCode.getHardCode());
        System.out.println();

        System.out.println("Problem 3.1: " + shapeAnalyzer.getHorizTriangle());
        System.out.println("Problem 3.2: " + shapeAnalyzer.getVertTriangle());
    }
}