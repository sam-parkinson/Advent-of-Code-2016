import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecoyRemover {
    private class Room {
        private String name;
        private int code;
        private String checksum;

        private HashMap<Character, Integer> charCount;

        private Room (String code) {
            // split code
            // assign parts of code to room

            // make hash map
        }
        // room constructor

        // is room valid? returns int, 0 if false, code if true
    }

    private int idSum;
    private Room[] roomList;

    public DecoyRemover(String address) {
        this.idSum = 0;

        parseInput(address);
    }

    private void parseInput(String address) {
        ArrayList<String> rawRooms = new ArrayList<String>();
        
        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                rawRooms.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // make room list
    }
}