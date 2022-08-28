/**
 * This is the Player class.
 * This class is responsible for the controls of the player(s) ant bot(s).
 * and will be where most of the controlling code is.
 *
 * @author Seb White
 * @version 1
 */
import java.util.Random;
import java.util.Scanner;

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
        System.out.println("\n");
        for (int z = 0; z < players; z++) {
            char Character = '\u0041';
            int column = 0;
            System.out.print("X|"); // Prints the X in the top left corner
            for (int x = 0; x < gridSize; x++) {
                System.out.print(column + " "); // Prints the column numbers above the grid
                column++; // Increments the column number
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
    public boolean isGameOver() {
        int count = 0;
        // loop for each cell (with the x, y, and x loops)
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (grids[x][y][playerNumber] == 0 || grids[x][y][playerNumber] == 9) {
                    count++; // if cell is empty (0) or hit (9), increment count
                } else {
                    return false;
                }
            }
        }
        // if each cell is either empty or hit. return true, otherwise return false
        return count == (gridSize * gridSize); // if count is equal to the amount of cells in the grid, return true, otherwise return false
    }

    public boolean checkPlaceable(String positions, String alignment, int shipSize) {
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

    public void botShipSetup(int shipType, int shipSize) {
        Random rand = new Random(); // Create random object
        boolean placed = false; // Boolean to check if ship has been placed
        while(!placed) {
            yPos = rand.nextInt(10); // easier than Math.random, generates a random number between 0 and 9
            xPos = rand.nextInt(10);
            int alignment = rand.nextInt(2); // Generate a random number between 0 and 1
            // 0 is horizontal, 1 is vertical
            if (alignment == 0) { // if number is 0
                xPos = rand.nextInt(11 - shipSize); // Generate a random number between 0 and 10-shipSize
                // My logic is if the ship size is 3 it would 9-3 then it would be 0-6.
                // So if it generates the max number (6) the ship would only reach 8, instead of 9 (the edge of the board)
            } else { // if number is 1
                yPos = rand.nextInt(11 - shipSize);
            }
            // Check if the ship can be placed
            if(botCheckPlaceable(alignment, shipSize)) {
                botPlace(alignment, shipSize, shipType); // Place the ship
                placed = true; // Set boolean to true
            }
        }
    }

    public boolean botCheckPlaceable(int alignment, int shipSize) {
        if(alignment==0) { // if player horizontal alignment
            for(int x=xPos; x<shipSize+xPos; x++) { // loop for shipSize through xPos
                if(grids[x][yPos][playerNumber]!=0) { // if there is an overlap
                    System.out.println("An overlap has been detected, please try again.\nHere is your current board"); // print error
                    viewBoard(); // View the board
                    return false; // return
                }
            }
        }
        if(alignment==1) { // Ff player chose vertical alignment
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

    public void botPlace(int alignment, int shipSize, int shipType) {
        if(alignment==0) { // if player horizontal alignment
            for(int x=xPos; x<shipSize+xPos; x++) { // loop for shipSize through xPos
                grids[x][yPos][playerNumber] = shipSize * shipType; // set cell number to ship type
            }
        }
        if(alignment==1) { // Ff player chose vertical alignment
            for(int y=yPos; y<shipSize+yPos; y++) { // loop for shipSize through yPos
                grids[xPos][y][playerNumber] = shipSize * shipType;
            }
        }
    }

    public String takeTurn() {
        if(!isBot) {
            Scanner keyboard = new Scanner(System.in); // Create a scanner to get input from the user, wasn't needed until now
            System.out.println("Where would you like to fire? (type 'quit' to end the game)"); // Ask for input for positions
            return keyboard.nextLine(); // Save the input to a string
        } else {
            Random rand = new Random(); // Create random object
            int x = rand.nextInt(10); // Generate a random number between 0 and 9
            int intY = rand.nextInt(10); // Generate a random number between 0 and 9
            char charY = (char)(intY + 97); // Convert number to letter
            return x + "" + charY; // Return the x and y positions as a string
        }
    }

    public int checkHit(String positions) {
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
        if (grids[xPos][yPos][playerNumber] == 0 || grids[xPos][yPos][playerNumber] == 9 ) { // If the cell is empty
            return 0; // return 0, meaning missed
        } else { // If the cell is not empty
            int shipHit = grids[xPos][yPos][playerNumber]; // Save the ship number to shipHit
            grids[xPos][yPos][playerNumber] = 9; // Set the cell to 9 to indicate a hit
            // loop for each cell (with the x, y, and x loops)
            for (int y = 0; y < gridSize; y++) {
                for (int x = 0; x < gridSize; x++) {
                    if (grids[x][y][playerNumber] == shipHit) { // If the cell is the same as the shipHit
                        return 1; // return 1, meaning hit but not sunk
                    }
                }
            }
            return 2; // return 2, meaning sunk
            // if each cell is either empty or hit. return true, otherwise return false
        }
    }
}
