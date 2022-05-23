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
    private int x;
    private int y;

    public static void main(String[] args) {
        // Create 2 arrays of size 10
        int[][] playerGrid = new int[10][10]; // I do it like this because the IDE complains otherwise
        int[][] botGrid = new int[10][10];

        for (int y=0;y<10;y++) { // Loop through the y-axis
            for (int x=0;x<10;x++) { // Loop through the x-axis
                playerGrid[x][y] = 0; // Sets the cell in the grid to 0
            }
        }
        System.out.println(); // Creating gap between grids.
        for (int y=0;y<10;y++) { // Loop through the y-axis
            for (int x=0;x<10;x++) { // Loop through the x-axis
                botGrid[x][y] = 0; // Sets the cell in the grid to 0
            }
        }
    }
}