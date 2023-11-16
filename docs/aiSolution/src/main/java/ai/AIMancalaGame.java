package ai;
import java.util.Scanner;

public class AIMancalaGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] board = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0}; // Mancala board

        int currentPlayer = 0; // Player A: 0, Player B: 1
        boolean gameOn = true;

        while (gameOn) {
            displayBoard(board);
            System.out.println("Player " + (currentPlayer == 0 ? "A" : "B") + ", choose a pit (0-5):");
            int selectedPit = scanner.nextInt();
            if (isValidMove(board, selectedPit, currentPlayer)) {
                makeMove(board, selectedPit, currentPlayer);
                gameOn = !isGameOver(board);
                currentPlayer = (currentPlayer + 1) % 2; // Switch players
            } else {
                System.out.println("Invalid move. Please choose a pit with stones.");
            }
        }

        displayBoard(board);
        determineWinner(board);
        scanner.close();
    }

    // Display the current board
    private static void displayBoard(int[] board) {
        System.out.println("Player B (top)\n");
        for (int i = 12; i >= 7; i--) {
            System.out.print("[" + board[i] + "] ");
        }
        System.out.println("\n[" + board[13] + "]\t\t    [" + board[6] + "]");
        for (int i = 0; i <= 5; i++) {
            System.out.print("[" + board[i] + "] ");
        }
        System.out.println("\nPlayer A (bottom)\n");
    }

    // Check if the selected move is valid
    private static boolean isValidMove(int[] board, int selectedPit, int currentPlayer) {
        return (currentPlayer == 0 && selectedPit >= 0 && selectedPit <= 5 && board[selectedPit] > 0) ||
                (currentPlayer == 1 && selectedPit >= 7 && selectedPit <= 12 && board[selectedPit] > 0);
    }

    // Make the move and distribute stones
    private static void makeMove(int[] board, int selectedPit, int currentPlayer) {
        int stones = board[selectedPit];
        board[selectedPit] = 0;
        int currentPit = selectedPit;

        while (stones > 0) {
            currentPit = (currentPit + 1) % 14;
            if (currentPlayer == 0 && currentPit == 13) {
                currentPit = 0; // Skip Player B's Mancala
            } else if (currentPlayer == 1 && currentPit == 6) {
                currentPit = 7; // Skip Player A's Mancala
            }

            board[currentPit]++;
            stones--;
        }

        // Handle capturing stones
        if (currentPlayer == 0 && currentPit >= 0 && currentPit <= 5 && board[currentPit] == 1 && board[12 - currentPit] > 0) {
            board[6] += board[currentPit] + board[12 - currentPit];
            board[currentPit] = board[12 - currentPit] = 0;
        } else if (currentPlayer == 1 && currentPit >= 7 && currentPit <= 12 && board[currentPit] == 1 && board[12 - currentPit] > 0) {
            board[13] += board[currentPit] + board[12 - currentPit];
            board[currentPit] = board[12 - currentPit] = 0;
        }
    }

    // Check if the game is over
    private static boolean isGameOver(int[] board) {
        for (int i = 0; i < 6; i++) {
            if (board[i] != 0) return false;
        }
        for (int i = 7; i < 13; i++) {
            if (board[i] != 0) return false;
        }
        return true;
    }

    // Determine the winner and display the result
    private static void determineWinner(int[] board) {
        if (board[6] > board[13]) {
            System.out.println("Player A wins!");
        } else if (board[6] < board[13]) {
            System.out.println("Player B wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
