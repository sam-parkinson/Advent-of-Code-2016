import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BathroomCode {
    private int baseX, baseY, hardX, hardY;               
    private String[] instructions;      // directions
    private String baseCode, hardCode;

    public BathroomCode(String address) {
        this.baseX = 1;
        this.baseY = 1;
        this.hardX = 0;
        this.hardY = 2;

        this.baseCode = "";
        this.hardCode = "";

        parseInstructions(address);
        makeCode();
    }

    public String getBaseCode() {
        return this.baseCode;
    }

    public String getHardCode() {
        return this.hardCode;
    }

    private void parseInstructions(String address) {
        ArrayList<String> strArr = new ArrayList<String>();       

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                strArr.add(stdin.nextLine());
            }

            stdin.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        instructions = new String[strArr.size()];
        instructions = strArr.toArray(instructions);
    }

    private void makeCode() {
        for (int i = 0; i < instructions.length; i++) {
            findDigit(instructions[i]);

            baseCode += addBasicDigit();
            hardCode += addHardDigit();
        }
    }

    private void findDigit(String str) {
        char[] charArr = str.toCharArray(); 
        for (int i = 0; i < charArr.length; i++) {
            switch (charArr[i]) {
                case 'U':
                    goUp();
                    break;
                case 'R':
                    goRight();
                    break;
                case 'D':
                    goDown();
                    break;
                case 'L':
                    goLeft();
                    break;
            }
        }
    }

    private void goUp() {
        if (baseY < 2)
            baseY++;

        if ((hardY < 4 && hardX == 2) || (hardY < 3 && (hardX == 1 || hardX == 3))) 
            hardY++;            
    }

    private void goRight() {
        if (baseX < 2)
            baseX++;
        
        if ((hardX < 4 && hardY == 2) || (hardX < 3 && (hardY == 1 || hardY == 3))) 
            hardX++;
    }

    private void goDown() {
        if (baseY > 0)
            baseY--;

        if ((hardY > 0 && hardX == 2) || (hardY > 1 && (hardX == 1 || hardX == 3))) 
            hardY--;
    }
    private void goLeft() {
        if (baseX > 0)
            baseX--;

        if ((hardX > 0 && hardY == 2) || (hardX > 1 && (hardY == 1 || hardY == 3))) 
            hardX--;
    }

    private String addBasicDigit() {
        if (baseY == 2) {
            return Integer.toString(baseX + 1);
        } else if (baseY == 1) {
            return Integer.toString(baseX + 4);
        } else
            return Integer.toString(baseX + 7);
    }

    private String addHardDigit() {
        if (hardY == 4) {
            return "1";
        } else if (hardY == 3) {
            return Integer.toString(hardX + 1);
        } else if (hardY == 2) {
            return Integer.toString(hardX + 5);
        } else if (hardY == 1) {
            return Character.toString((char) 64 + hardX);
        } else
            return "D";

    }
}

/*
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 
 * [0, 2] [1, 2] [2, 2]
 * [0, 1] [1, 1] [2, 1]
 * [0, 0] [1, 0] [2, 0]
 * 
 * 
 * 
 *     1
 *   2 3 4
 * 5 6 7 8 9
 *   A B C
 *     D
 *               [2, 4]
 *        [1, 3] [2, 3] [3, 3]
 * [0, 2] [1, 2] [2, 2] [3, 2] [4, 2]
 *        [1, 1] [2, 1] [3, 1]
 *               [2, 0]
 */