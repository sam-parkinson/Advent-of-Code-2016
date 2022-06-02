public class Driver {
    public static void main(String[] args) {
        GridTracker gridTracker = new GridTracker("inputs/day1.txt");

        System.out.println("Problem 1.1: " + gridTracker.getEndDistance());
        System.out.println("Problem 1.2: " + gridTracker.getTrueDistance());
    }
}