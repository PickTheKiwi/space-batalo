/**
 * This is the Player class.
 * This class is responsible for the controls of the player(s) ant bot(s).
 * and will be where most of the controlling code is.
 *
 * @author Seb White
 * @version 1
 */

public class Player {
    public int players; // Amount of players
    public boolean isBot; // For checks if a bot should play when this is called
    public int gridSize; // Where grid size will be saved
    private static int[][][] grids;


    public Player(Boolean isHuman, int playerCount, int sizeOfGrid) {
        players = playerCount;
        isBot = !isHuman;
        gridSize = sizeOfGrid;

        grids = new int[gridSize][gridSize][players];

        // Set each cell in the grid to 0
        for (int z = 0; z < players; z++) {
            for (int y = 0; y < gridSize; y++) {
                for (int x = 0; x < gridSize; x++) {
                    grids[x][y][z] = 0; // Sets the cell in the grid to 0
                }
            }
        }
    }

    public void viewBoard() {
        // print the grid
        char Character = '\u0030';
        System.out.println("\n");
        for (int z = 0; z < players; z++) {
            int row = 0;
            System.out.print("X⼁"); // Prints the X in the top left corner
            for (int x = 0; x < gridSize; x++) {
                System.out.print(row + " "); // Prints the column numbers above the grid
                row++; // Increments the column number
            }
            System.out.println();
            System.out.print("-\uD83D\uDFA1"); // Just to make it look nicer
            for (int x = 0; x < (gridSize * 2) - 1; x++) {
                System.out.print("-"); // Prints divider between row numbers and grid
            }

            for (int y = 0; y < gridSize; y++) {
                System.out.println(); // Prints to next line
                System.out.print(Character + "⼁"); // Prints the row numbers to the left of the grid
                Character++; // Increments the row number
                for (int x = 0; x < gridSize; x++) {
                    System.out.print(grids[x][y][z] + " "); // Prints the cell in the grid
                }
            }
            System.out.println("\n"); // Creates a gap between the boards
        }
    }

    public void playerSetup(String positions, String alignment, String shipType) { // This is where the player will set up their ships on the grid
        // TODO: Start by checking if the ship can be placed in that position (no colisions or leaving board)
        // TODO: Place ship
        if()
    }

    // check if the board has had all ships removed
    public boolean isGameOver() {
        int count = 0;
        for (int z = 0; z < players; z++) {
            for (int y = 0; y < gridSize; y++) {
                for (int x = 0; x < gridSize; x++) {
                    if (grids[x][y][z] == 0 || grids[x][y][z] == 9) {
                        count++;
                    }
                }
            }
        }
        if (count == (gridSize * gridSize * players)) {
            return true;
        } else {
            return false;
        }
    }
}
