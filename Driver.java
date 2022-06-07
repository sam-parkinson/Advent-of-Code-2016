public class Driver {
    public static void main(String[] args) {
        GridTracker gridTracker = new GridTracker("inputs/day1.txt");
        BathroomCode bathroomCode = new BathroomCode("inputs/day2.txt");
        ShapeAnalyzer shapeAnalyzer = new ShapeAnalyzer("inputs/day3.txt");
        DecoyRemover decoyRemover = new DecoyRemover("inputs/day4.txt");
        // DoorCode doorCode = new DoorCode("inputs/day5.txt");
        SignalRepeater signalRepeater = new SignalRepeater("inputs/day6.txt");

        System.out.println("Problem 1.1: " + gridTracker.getEndDistance());
        System.out.println("Problem 1.2: " + gridTracker.getTrueDistance());
        System.out.println();

        System.out.println("Problem 2.1: " + bathroomCode.getBaseCode());
        System.out.println("Problem 2.2: " + bathroomCode.getHardCode());
        System.out.println();

        System.out.println("Problem 3.1: " + shapeAnalyzer.getHorizTriangle());
        System.out.println("Problem 3.2: " + shapeAnalyzer.getVertTriangle());
        System.out.println();

        System.out.println("Problem 4.1: " + decoyRemover.getIDSum());
        System.out.println("Problem 4.2: " + decoyRemover.getCorrectRoomCode());
        System.out.println();

        /* 
        System.out.println("Problem 5.1: " + doorCode.getPasswordOne());
        System.out.println("Problem 5.2: " + doorCode.getPasswordTwo());
        System.out.println(); 
        */

        System.out.println("Problem 6.1: " + signalRepeater.getMaxMessage());
        System.out.println("Problem 6.2: " + signalRepeater.getMinMessage());
        System.out.println();
    }
}
