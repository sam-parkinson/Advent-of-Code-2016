import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ShapeAnalyzer {
    private int horizTriangle, vertTriangle;
    private int[][] trios;

    public ShapeAnalyzer(String address) {
        this.horizTriangle = 0;
        this.vertTriangle = 0;

        parseInput(address);
        countValidTriangles();
    }

    public int getHorizTriangle() {
        return this.horizTriangle;
    }

    public int getVertTriangle() {
        return this.vertTriangle;
    }

    private void parseInput(String address) {
        ArrayList<String> strArr = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNextLine()) {
                strArr.add(stdin.nextLine().trim());
            }

            stdin.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        trios = new int[strArr.size()][strArr.get(0).split("\s+").length];

        for (int i = 0; i < trios.length; i++) {
            String[] lineArr = strArr.get(i).split("\s+");

            for (int j = 0; j < lineArr.length; j++) {
                trios[i][j] = Integer.parseInt(lineArr[j]);
            }
        }
        
    }

    // there might be a way to do this all in one for loop
    // leveraging something like i % 3 to run the vertical triangle counter
    // but I think the below should work, I don't feel like juggling modulos at present moment
    // and I'm not quite sure which has the better runtime
    private void countValidTriangles() {
        for (int i = 0; i < trios.length; i++) {
            int[] points = new int[3];

            for (int j = 0; j < points.length; j++) {
                points[j] = trios[i][j];
            }

            Arrays.sort(points);

            if (points[0] + points[1] > points[2]) 
                horizTriangle++;
        }

        for (int i = 0; i < trios.length - 2; i += 3) {
            for (int j = 0; j < 3; j++) {
                int[] points = new int[3];

                for (int k = 0; k < 3; k++) {
                    points[k] = trios[k + i][j];
                }

                Arrays.sort(points);
                if (points[0] + points[1] > points[2]) 
                    vertTriangle++;
            }
        }
    }
}