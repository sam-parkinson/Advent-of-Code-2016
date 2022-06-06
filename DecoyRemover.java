import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class DecoyRemover {
    private class Room {
        private String name, fullName, decodedName, checksum;
        private int code;
        private boolean isReal;

        private HashMap<Character, Integer> charCount;

        private Room (String input) {

            String[] split = input.split("\\W");

            this.checksum = split[split.length - 1];
            this.code = Integer.parseInt(split[split.length - 2]);

            this.name = "";
            this.fullName = "";
            this.decodedName = "";
            for (int i = 0; i < split.length - 2; i++) {
                this.name += split[i];
                this.fullName += split[i];
                this.fullName += " ";
            }

            this.fullName.trim();
            
            // hash name to charCount
            this.charCount = new HashMap<Character, Integer>();
            for (int i= 0; i < name.length(); i++) {
                charCount.putIfAbsent(name.charAt(i), 0);
                charCount.put(name.charAt(i), charCount.get(name.charAt(i)) + 1);
            }

            this.isReal = isValidRoom();
            if (isReal) {
                decipherName();
            }
        }

        // is room valid? returns int, 0 if false, code if true
        private boolean isValidRoom() {
            Character[] sortedKeys = charCount.keySet().stream().toArray(Character[]::new);

            // Custom comparator places characters first by number of occurrences in room name
            // then by alphabetic order
            Arrays.sort(sortedKeys, new Comparator<Character>() {
                public int compare(Character a, Character b) {
                    int countComp = charCount.get(b).compareTo(charCount.get(a));
                    
                    return countComp != 0 ? countComp : a.compareTo(b);
                }
            });
            
            for (int i = 0; i < checksum.length(); i++) {
                if ((Character) checksum.charAt(i) != sortedKeys[i]) {
                    return false;
                }
            }

            return true;
        }

        private void decipherName() {
            for (int i = 0; i < fullName.length(); i++) {
                char character = fullName.charAt(i);
                if (character == ' ') {
                    decodedName += ' ';
                } else {
                    // run caesar cipher
                    int origChar = character - 'a';
                    char newChar = (char) ('a' + (origChar + code) % 26);
                    decodedName += newChar;
                }
            }
        }
    }

    private int idSum, correctRoomCode;
    private Room[] roomList;

    public DecoyRemover(String address) {
        this.idSum = 0;
        this.correctRoomCode = -1;

        parseInput(address);
        sumValidRooms();
        findCorrectRoom();
    }

    public int getIDSum() {
        return this.idSum;
    }

    public int getCorrectRoomCode() {
        return this.correctRoomCode;
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
            if (roomList[i].isReal)
                idSum += roomList[i].code;
        }
    }

    // string name is "northpole object storage"
    // there are a few ways to find it, solution here is slightly hackish
    // but given knowledge of the desired string, should be easy to methodically
    // identify correct string
    private void findCorrectRoom() {
        int i = 0;
        while (correctRoomCode == -1 && i < roomList.length) {
            if (roomList[i].isReal) {

                String[] nameArr = roomList[i].decodedName.split(" ");

                if (nameArr[0].equals("northpole"))
                    this.correctRoomCode = roomList[i].code;
                /* for (int j = 0; j < nameArr.length; j++) {
                    if ("northpole".equals(nameArr[j])) {
                        this.correctRoomCode = roomList[i].code;
                    }
                } */
            }   
            i++;
        }
    }
}

// && nameArr[j + 1].equals("pole")