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
    private int[][][] grids;


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
        char Character = '\u0041';
        System.out.println("\n");
        for (int z = 0; z < players; z++) {
            int row = 0;
            System.out.print("X\u2551"); // Prints the X in the top left corner
            for (int x = 0; x < gridSize; x++) {
                System.out.print(row + " "); // Prints the column numbers above the grid
                row++; // Increments the column number
            }
            System.out.println();
            System.out.print("\u2550\u256C"); // Just to make it look nicer
            for (int x = 0; x < (gridSize * 2) - 1; x++) {
                System.out.print("\u2550"); // Prints divider between row numbers and grid
            }

            for (int y = 0; y < gridSize; y++) {
                System.out.println(); // Prints to next line
                System.out.print(Character + "\u2551"); // Prints the row numbers to the left of the grid
                Character++; // Increments the row number
                for (int x = 0; x < gridSize; x++) {
                    System.out.print(grids[x][y][z] + " "); // Prints the cell in the grid
                }
            }
            System.out.println("\n"); // Creates a gap between the boards
        }
    }

    public boolean positionLengthCheck(String positions) {
        // check to made sure positions entered is valid, if not, print and error and return false
        if (positions.length() != 2) {
            System.out.println("Invalid positions entered");
            System.out.println("Issue: Position entered is too long.\n ");
            return(false);
        }
        else {return(true);}
    }

    public void playerSetup(String positions, String alignment, int shipSize) { // This is where the player will set up their ships on the grid
        // convert positions to two separate variables

            // check to make sure ship position doesn't go off the board
        if (positions.charAt(0) > gridSize || positions.charAt(1) > gridSize) {
            System.out.println("Invalid positions entered");
            return;
        }
    }

    // check if the board has had all ships removed
    public boolean isGameOver(int playerNumber) {
        int count = 0;
        // loop for each cell (with the x, y, and x loops)
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (grids[x][y][playerNumber] == 0 || grids[x][y][playerNumber] == 9) { // checks if cell is empty or hit
                        count++; //
                }
            }
        }
        // if each cell is either empty or hit. return true, otherwise return false
        return count == (gridSize * gridSize); // Initially an if statement, but I realised it could be simplified
    }
}
