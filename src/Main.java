/**
 * This is the main class.
 * This class is responsible for initializing/running the game
 * and will be where most of the code is.
 *
 * @author Seb White
 * @version 0.1
 */
public class Main {
    final static int humanCount = 1; // Amount of humans in game, I chose to do this in case of more than one human playing
    final static int botCount = 1; // Amount of bots in game, I chose to do this in case of more than one bot playing
    final static int playerCount = humanCount + botCount; // Total players, including both humans and bots

    final static int gridSize = 10; // Size of grid

    public static void main(String[] args) {
        // load the Player class into as many objects as playerCount says
        Player[] players = new Player[playerCount];
        for (int x = 0; x < playerCount; x++) { // For as many players as there are
            players[x] = new Player(true, playerCount, gridSize); // Create a new player
        }
        players[0].viewBoard(); // View the board
    }
}