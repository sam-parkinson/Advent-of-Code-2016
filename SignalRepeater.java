import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SignalRepeater {
    private ArrayList<HashMap<Character, Integer>> attemptCounter;
    private String[] messageAttempts;
    private String maxMessage, minMessage;

    public SignalRepeater(String address) {
        this.maxMessage = "";
        this.minMessage = "";

        parseInput(address);
        attemptCounter = new ArrayList<HashMap<Character, Integer>>(messageAttempts[0].length());
        decipherMessage();
    }

    public String getMaxMessage() {
        return this.maxMessage;
    }

    public String getMinMessage() {
        return this.minMessage;
    }

    private void parseInput(String address) {
        ArrayList<String> strArr = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                strArr.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        messageAttempts = new String[strArr.size()];
        messageAttempts = strArr.toArray(messageAttempts);
    }

    private void decipherMessage() {
        int length = messageAttempts[0].length();

        for (int i = 0; i < length; i++) {
            attemptCounter.add(new HashMap<Character, Integer>());

            HashMap<Character, Integer> current = attemptCounter.get(i);
            for (int j = 0; j < messageAttempts.length; j++) {
                char letter = messageAttempts[j].charAt(i);
                current.putIfAbsent(letter, 0);
                current.put(letter, current.get(letter) + 1);
            }
        }

        for (int i = 0; i < attemptCounter.size(); i++) {
            HashMap<Character, Integer> current = attemptCounter.get(i);

            HashMap.Entry<Character, Integer> maxEntry = null;
            HashMap.Entry<Character, Integer> minEntry = null;

            for (HashMap.Entry<Character, Integer> entry : current.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                    maxEntry = entry;
                
                if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0)
                    minEntry = entry;
            }

            maxMessage += maxEntry.getKey();
            minMessage += minEntry.getKey();
        }

    }
    // leverage hash maps
    // array of hash maps counting each letter at position in message
    // find max at each position
}
