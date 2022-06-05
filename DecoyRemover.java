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

        private Room (String input) {
            this.code = 0;
            // split code

            // get regex working properly
            String[] split0 = input.split("");

            // code in format letters-dashes-dashes-number[checksum]

            // 1. break out checksum into separate value
            // 2. go thru remainder of array
            // 3. while value non-numeric, append to name, then hash
            // 4. when value numeric, parse int to code

        }

        // is room valid? returns int, 0 if false, code if true
        private int isValidRoom() {
            /*
                sort keys of hash map by value then by alphabetical order

                go thru checksum and make sure char at each index matches at corresponding index

                if we make it through, return true, otherwise return false
            */            

            // return 0 if false
            return this.code;
        }
    }

    private int idSum;
    private Room[] roomList;

    public DecoyRemover(String address) {
        this.idSum = 0;

        parseInput(address);
        sumValidRooms();
    }

    public int getIDSum() {
        return this.idSum;
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

        roomList = new Room[rawRooms.size()];

        for (int i = 0; i < roomList.length; i++) {
            roomList[i] = new Room(rawRooms.get(i));
        }
    }

    private void sumValidRooms() {
        for (int i = 0; i < roomList.length; i++) {
            idSum += roomList[i].isValidRoom();
        }
    }
}