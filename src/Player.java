/**
 * This is the Player class.
 * This class is responsible for the controls of the player(s) ant bot(s).
 * and will be where most of the controlling code is.
 *
 * @author Seb White
 * @version 1
 */
public class Player {
    public int players;
    public boolean isBot;
    private static int[][][] grids;

    public Player(Boolean isHuman, int playerCount) {
        players = playerCount;
        isBot = !isHuman;

        grids = new int[10][10][players];

        for (int z = 0; z < players; z++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    grids[x][y][z] = 0; // Sets the cell in the grid to 0
                }
            }
        }
    }


    public void viewBoard() {
        // print the grid
        for (int z = 0; z < players; z++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    System.out.print(grids[x][y][z] + " "); // Prints the cell in the grid
                }
                System.out.println(); // Prints to next line
            }
            System.out.println(); // Creates a gap between the boards
        }
    }
}
