/**
 * This is the main class.
 * This class is responsible for initializing/running the game
 * and will be where most of the code is.
 *
 *
 * @author Seb White
 * @version 1
 */

public class Main {
    final static int players = 2; // Never changes
    public static void main(String[] args) {
        // Create 2 arrays of size 10
        int[][][] grids = new int[10][10][players]; // I do it like this because the IDE complains otherwise
        for (int z=0;z<players;z++) { // Loop through the z-axis
            for (int y = 0; y < 10; y++) { // Loop through the y-axis
                for (int x = 0; x < 10; x++) { // Loop through the x-axis
                    grids[x][y][z] = 0; // Sets the cell in the grid to 0
                }
            }
        }

        // Repeat the last loop but print the grid
        for (int z=0;z<players;z++) { // Loop through the z-axis
            for (int y = 0; y < 10; y++) { // Loop through the y-axis
                for (int x = 0; x < 10; x++) { // Loop through the x-axis
                    System.out.print(grids[x][y][z] + " "); // Prints the cell in the grid
                }
                System.out.println(); // Prints a new line
            }
            System.out.println(); // Prints a new line
        }
    }
}