/**
 * This is the main class.
 * This class is responsible for initializing/running the game
 * and will be where most of the code is.
 *
 * @author Seb White
 * @version 0.1
 */
import java.util.Scanner;

public class Main {

    final static int HUMANCOUNT = 1; // Amount of humans in game, I chose to do this in case of more than one human playing
    final static int BOTCOUNT = 1; // Amount of bots in game, I chose to do this in case of more than one bot playing
    final static int PLAYERCOUNT = HUMANCOUNT + BOTCOUNT; // Total players, including both humans and bots

    final static int GRIDSIZE = 10; // Size of grid, do not set above 10, you will break the program due to how position input is taken.

    public static int whichPlayer = 0; // Which player is currently playing, starts at 0 because it is the first player to play.

    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in); // Creates a scanner to take input from the user

        if (args.length != 0) {
            System.out.println("\nArguments are not needed to run the game.\nPlease run the game without any arguments to play.\n");
            System.exit(2); // We don't need arguments for the game to run
        }

        // load the Player class into as many objects as PLAYERCOUNT is
        Player[] players = new Player[PLAYERCOUNT];
        for (int x = 0; x < HUMANCOUNT; x++) { // For as many players as there are
            players[x] = new Player(true, PLAYERCOUNT, GRIDSIZE); // Create a new player

        }
        for (int x = HUMANCOUNT; x < PLAYERCOUNT; x++) { // For as many players as there are
            players[x] = new Player(false, PLAYERCOUNT, GRIDSIZE); // Create a new player
        }
        for (int x = 0; x < PLAYERCOUNT; x++) { // For as many players as there are
            players[x].viewBoard(); // View the board before game start - This is will be removed in the future
        }

        boolean inSetup = true; // Boolean to check if the game is in setup mode or not
        String position; // Where the position will be saved temporarily
        String alignment; // Where the alignment will be saved temporarily
        int shipSize = 0; // Where the ship size will be saved temporarily

        for (int x=2;x<5;x++) {
            while (inSetup) { // While the position is not valid
                System.out.print("Where do you wish to place the ship? (e.g. A2) - "); // Ask the user where they wish to place the ship
                position = keyboard.nextLine(); // Take input for string1
                inSetup = !validPosInput(position); // Check if the position is valid
            }



        }

        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.print("Test output"); // Test output, will be removed

        Boolean gameRunning = true; // Boolean to check if the game is running, true by default.

        while (gameRunning) { // Because I want the game to end when player board is empty, not after all player have had their turn and a board is empty
            if(whichPlayer < PLAYERCOUNT) { // If the player is not at/above the player count
                whichPlayer++; // Increments which player is currently playing
            }
            else {
                whichPlayer = 0; // Resets which player is currently playing
            }
        }
        System.out.print("Game over"); // Tell the player the game is over, this wil be updated to be better later.
    }

    public static boolean validPosInput(String positions) { // Checks if the position is valid
        if (positions.length() != 2) {
            System.out.println("\nYou have entered and invalid input.\nPlease enter a valid position\nIssue: You must only input two characters"); // If the input is not valid, tell the user to try again
            return false;
        }
        if (Character.isDigit(positions.charAt(0)) && !Character.isDigit(positions.charAt(1))) { // if first character is a digit, and the second character is not
            if((positions.charAt(1) <= 'a' && positions.charAt(1) >= 'j') || (positions.charAt(1) <= 'A' && positions.charAt(1) >= 'J')) {
                return true;
            }
        }
        if (Character.isDigit(positions.charAt(1)) && !Character.isDigit(positions.charAt(0))){
            if((positions.charAt(0) <= 'a' && positions.charAt(1) >= 'j') || (positions.charAt(1) <= 'A' && positions.charAt(0) >= 'J')) {
                return true;
            }
        }

        System.out.println("\nYou have entered and invalid input.\nPlease enter a valid position\nissue: You must enter one letter (from a-j) and one number (0-9)"); // If the input is not valid, tell the user to try again
        return false;
    }

    public static boolean validPosPlacement(String positions, String alignment, int shipsize) {
        return false;
    }
}