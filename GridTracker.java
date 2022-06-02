import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class GridTracker {
    private String[] instructions;
    private int x, y;       // coordinates
    private int direction;  // 0 = up, 1 = right, 2 = down, 3 = left
    private int trueX, trueY; // coordinates for first location visited twice
    private HashSet<String> visited;
    
    public GridTracker(String address) {
        // start at 0, 0; facing north
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        
        this.instructions = parseInstructions(address);
        
        this.visited = new HashSet<String>();

        followInstructions();
    }

    public int getEndDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public int getTrueDistance() {
        return Math.abs(trueX) + Math.abs(trueY);
    }

    private String[] parseInstructions(String address) {
        String baseInst = "";

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            baseInst = stdin.nextLine();

            stdin.close();
        } catch(Exception e) {
            System.out.println(e);
        }      

        return baseInst.split(", ");
    }

    private void followInstructions() {

        // add start to visited locations, we have not found true location yet
        visited.add(x + ", " + y);
        boolean found = false;

        for (int i = 0; i < instructions.length; i++) {
            // update direction
            String dir = instructions[i];

            if (dir.charAt(0) == 'L')
                // two wrongs don't make a right but three rights make a left
                direction += 3;
            else
                direction += 1;

            direction %= 4;
            int dist = Integer.parseInt(dir.substring(1));

            // use two different functions
            // one, if true destination has not been found yet, iterates
            // the other, if true destination has been found, adds

            if (!found) {
                found = moveDeliberately(dist);
            } else {
                moveQuickly(dist);
            }

            
            
            // add new location to hashmap of previously visited locations
            // as long as boolean is false
            // if we see previously visited location
            // set boolean to true
            // update twoX, twoY
        }
    }

    private boolean moveDeliberately(int dist) {
        boolean success = true;

        for (int i = 0; i < dist; i++) {
            // step once in current direction
            switch(direction) {
                case 0:     // up
                    y++;
                    break;
                case 1:     // right
                    x++;
                    break;
                case 2:     // down
                    y--;
                    break;
                case 3:     // left
                default:    // so the compiler doesn't throw a fit
                    x--;    
                    break;
            }
            
            if (success)
            {
                success = visited.add(x + ", " + y);
                if (!success) {
                    trueX = x;
                    trueY = y;
                }
            }    
        }

        return !success;
    }

    private void moveQuickly(int dist) {
        switch(direction) {
            case 0:     // up
                y += dist;
                break;
            case 1:     // right
                x += dist;
                break;
            case 2:     // down
                y -= dist;
                break;
            case 3:     // left
            default:    // so the compiler doesn't throw a fit
                x -= dist;    
                break;
        }
    }
}