import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class DoorCode {
    private String name;
    private String passwordOne, passwordTwo;

    public DoorCode(String address) {
        this.passwordOne = "";
        this.passwordTwo = "";
        parseInput(address);
        findpasswords();
    }

    public String getPasswordOne() {
        return this.passwordOne;
    }

    public String getPasswordTwo() {
        return this.passwordTwo;
    }

    private void parseInput(String address) {
        String input = "";

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);
            input = stdin.nextLine();
            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        this.name = input;
    }

    private void findpasswords() {
        int i = 0;
        int filledTwo = 0;
        String[] passwordTwoArr = new String[8];

        while (passwordOne.length() < 8 || filledTwo < 8) {
            String attempt = findFirst7MD5(name + i);
            if (attempt.substring(0, 5).equals("00000")) {
                if (passwordOne.length() < 8) 
                    passwordOne += attempt.charAt(5);

                int index = Character.getNumericValue(attempt.charAt(5));

                if (index < 8 && index >= 0 && passwordTwoArr[index] == null) {
                    passwordTwoArr[index] = attempt.substring(6);
                    filledTwo++;
                }
            }
                
            i++;
        }
        
        for (int j = 0; j < passwordTwoArr.length; j++) {
            passwordTwo += passwordTwoArr[j];
        }
    }

    // https://www.geeksforgeeks.org/md5-hash-in-java/

    private String findFirst7MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext.substring(0, 7);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        return "";
    }
}
