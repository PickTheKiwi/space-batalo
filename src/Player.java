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
    private int[][][] grids; // Where the grids will be saved.
    public int yPos;
    public int xPos;

    private final int playerNumber;


    public Player(Boolean isHuman, int playerCount, int sizeOfGrid, int playerno) {
        players = playerCount;
        isBot = !isHuman;
        gridSize = sizeOfGrid;

        playerNumber = playerno;

        grids = new int[gridSize][gridSize][players]; // Create a new grid for each player (and set sizes)

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
            System.out.print("X|"); // Prints the X in the top left corner
            for (int x = 0; x < gridSize; x++) {
                System.out.print(row + " "); // Prints the column numbers above the grid
                row++; // Increments the column number
            }
            System.out.println();
            System.out.print("-+"); // Just to make it look nicer
            for (int x = 0; x < (gridSize * 2) - 1; x++) {
                System.out.print("-"); // Prints divider between row numbers and grid
            }

            for (int y = 0; y < gridSize; y++) {
                System.out.println(); // Prints to next line
                System.out.print(Character + "|"); // Prints the row numbers to the left of the grid
                Character++; // Increments the row number
                for (int x = 0; x < gridSize; x++) {
                    System.out.print(grids[x][y][z] + " "); // Prints the cell in the grid
                }
            }
            System.out.println("\n"); // Creates a gap between the boards
        }
    }

    // check if the board has had all ships removed
    public boolean isGameOver(int playerNumber) {
        int count = 0;
        // loop for each cell (with the x, y, and x loops)
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (grids[x][y][playerNumber] == 0 || grids[x][y][playerNumber] == 9) {
                        count++; // if cell is empty (0) or hit (9), increment count
                }
            }
        }
        // if each cell is either empty or hit. return true, otherwise return false
        return count == (gridSize * gridSize); // Initially an if statement, but I realised it could be simplified
    }

    public boolean checkPlaceable(String positions, String alignment, int shipSize, int shipType) {
        String tempXPos = positions.replaceAll("\\D", ""); // Remove all non-numeric characters from the string
        // Convert number in string to actual number
        xPos = Integer.parseInt(tempXPos);
        String tempYPos = positions.replaceAll("[^a-jA-J]", ""); // Remove all non-alphabetic characters from the string.
        if(!Character.isUpperCase(tempYPos.charAt(0))) { // If character is not uppercase
            yPos = tempYPos.charAt(0) - 97; // Change lowercase letter to number and assign to yPos
        }
        if (Character.isUpperCase(tempYPos.charAt(0))){ // If character is uppercase
            yPos = tempYPos.charAt(0) - 65; // Change uppercase letter to number and assign to yPos
        }
        if(alignment.equals("h")) { // if player horizontal alignment
            for(int x=xPos; x<shipSize+xPos; x++) { // loop for shipSize through xPos
                if(grids[x][yPos][playerNumber]!=0) { // if there is an overlap
                    System.out.println("An overlap has been detected, please try again.\nHere is your current board"); // print error
                    viewBoard(); // View the board
                    return false; // return
                }
            }
        }
        if(alignment.equals("v")) { // Ff player chose vertical alignment
            for(int y=yPos; y<shipSize+yPos; y++) { // loop for shipSize through yPos
                if(grids[xPos][y][playerNumber]!=0) { // if there is an overlap
                    System.out.println("An overlap has been detected, please try again.\nHere is your current board"); // print error
                    viewBoard(); // View the board
                    return false; // return
                }
            }
        }
        return true; // If there are no overlaps then return true
    }

    public void placeShips(String positions, String alignment, int shipSize, int shipType) {
        if(!isBot) {
            String tempXPos = positions.replaceAll("\\D", ""); // Remove all non-numeric characters from the string
            // Convert number in string to actual number
            xPos = Integer.parseInt(tempXPos); // Change character into number
            String tempYPos = positions.replaceAll("[^a-jA-J]", ""); // Remove all non-alphabetic characters from the string.
            if (!Character.isUpperCase(tempYPos.charAt(0))) { // If character is not uppercase
                yPos = tempYPos.charAt(0) - 97; // Change lowercase letter to number and assign to yPos
            }
            if (Character.isUpperCase(tempYPos.charAt(0))) { // If character is uppercase
                yPos = tempYPos.charAt(0) - 65; // Change uppercase letter to number and assign to yPos
            }
            if (alignment.equals("h")) { // if player horizontal alignment
                for (int x = xPos; x < shipSize + xPos; x++) { // loop for shipSize through xPos
                    grids[x][yPos][playerNumber] = shipSize * shipType; // set cell number to ship type
                    // "* shipType" because there are two ships of size three.
                    // I put it like this because the max size ship size is 5, and 2*3 is 6, just out of range of the largest ship on the board
                    // This is limiting in some ways however I'm not writing my code to work in those ways, so I really don't care
                }
            }
            if (alignment.equals("v")) { // If alignment is vertical use the same loop but in a vertical direction
                for (int y = yPos; y < shipSize + yPos; y++) {
                    grids[xPos][y][playerNumber] = shipSize * shipType;
                }
            }
        }
    }
}
