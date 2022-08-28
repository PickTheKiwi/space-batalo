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

    public static String position; // Where the position will be saved temporarily
    public static String alignment; // Where the alignment will be saved temporarily

    public static Player[] players;
    public static void main(String[] args) {


        if (args.length != 0) {
            System.out.println("\nArguments are not needed to run the game.\nPlease run the game without any arguments to play.\n");
            System.exit(2); // We don't need arguments for the game to run
        }

        // load the Player class into as many objects as PLAYERCOUNT is
        players = new Player[PLAYERCOUNT];
        for (int x = 0; x < HUMANCOUNT; x++) { // For as many players as there are
            players[x] = new Player(true, PLAYERCOUNT, GRIDSIZE, x); // Create a new player

        }
        for (int x = HUMANCOUNT; x < PLAYERCOUNT; x++) { // For as many players as there are
            players[x] = new Player(false, PLAYERCOUNT, GRIDSIZE, x); // Create a new player
        }
        for (int x = 0; x < PLAYERCOUNT; x++) { // For as many players as there are
            players[x].viewBoard(); // View the board before game start - This is will be removed in the future
        }

        for (int c=0;c<HUMANCOUNT;c++) {
            for (int x = 2; x < 6; x++) { // Ask for input, check, then place the ships
                position = askPosInput(); // Ask for input for positions
                while (!checkPosInput(position)){ // This will only loop if the previous input is invalid
                    position = askPosInput();
                }
                alignment = askAlignInput(); // Ask for input for alignment
                while (!checkAlignInput(alignment, x, 1)){ // This will only loop if the previous input is invalid
                    alignment = askAlignInput();
                }
                players[c].checkPlaceable(position, alignment, x);
                players[c].placeShips(position, alignment, x, 1);
                players[c].viewBoard();
            }
            // repeat the last loop one for time for the second size 3 ship
            position = askPosInput(); // Ask for input for positions
            while (!checkPosInput(position)){ // This will only loop if the previous input is invalid
                position = askPosInput();
            }
            alignment = askAlignInput(); // Ask for input for alignment
            while (!checkAlignInput(alignment, 3, 1)){ // This will only loop if the previous input is invalid
                alignment = askAlignInput();
            }
            players[c].checkPlaceable(position, alignment, 3);
            players[c].placeShips(position, alignment, 3, 2);
            players[c].viewBoard();
        }

        for (int c=HUMANCOUNT;c<PLAYERCOUNT;c++) {
            for (int x = 2; x < 6; x++) { // Ask for input, check, then place the ships
                players[c].botShipSetup(1, x);
            }
            players[c].botShipSetup(2, 3);
        }

        // Clear the screen
        System.out.print("\033[H\033[2J");

        boolean gameRunning = true; // Boolean to check if the game is running, true by default.

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

    public static String askPosInput() {
        Scanner keyboard=new Scanner(System.in); // Creates a scanner to take input from the user
        System.out.print("Where do you wish to place the ship? (e.g. A2) - "); // Ask the user where they wish to place the ship
        return keyboard.nextLine(); // Take input for string1
    }
    public static String askAlignInput() {
        Scanner keyboard=new Scanner(System.in); // Creates a scanner to take input from the user
        System.out.print("What alignment do you wish for your ship to be placed in?\nH for Horizontal for V for Vertical - "); // Ask the user where they wish to place the ship
        return keyboard.nextLine(); // Take input for string
    }

    public static boolean checkPosInput(String positions) { // Checks if the position is valid
        if (positions.length() != 2) {
            System.out.println("\nYou have entered and invalid input.\nPlease enter a valid position.\nYou must only input two characters"); // If the input is not valid, tell the user to try again
            return false;
        }
        if (Character.isDigit(positions.charAt(0)) && !Character.isDigit(positions.charAt(1))) { // if first character is a digit, and the second character is not
            if((positions.charAt(1) >= 'a' && positions.charAt(1) <= 'j') || (positions.charAt(1) >= 'A' && positions.charAt(1) <= 'J')) {
                return true;
            }
        }
        if (Character.isDigit(positions.charAt(1)) && !Character.isDigit(positions.charAt(0))){
            if((positions.charAt(0) >= 'a' && positions.charAt(0) <= 'j') || (positions.charAt(0) >= 'A' && positions.charAt(0) <= 'J')) {
                return true;
            }
        }

        System.out.println("\nYou have entered and invalid input.\nPlease enter a valid position\nYou must enter one letter (from a-j) and one number (0-9)"); // If the input is not valid, tell the user to try again
        return false;
    }
    public static boolean checkAlignInput(String alignment, int shipSize, int shipType) { // Checks if the position is valid
        if(!alignment.equals("H")&&!alignment.equals("V")&&!alignment.equals("h")&&!alignment.equals("v")) {
            System.out.println("\nYou have entered and invalid input.\nPlease enter a valid position.\nValid input is 'V' or 'H'");
        }
        return (alignment.equals("H")||alignment.equals("V")||alignment.equals("h")||alignment.equals("v"));
    }
}