package ai;
import java.util.Scanner;

public class AIMancalaGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board();
        Player[] players = {new Player("A", 0), new Player("B", 1)};
        int currentPlayerIndex = 0;
    
        do{
            board.displayBoard();
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("Player " + currentPlayer.getName() + ", choose a pit (0-5):");
            int selectedPit = scanner.nextInt();

            if (board.isValidMove(selectedPit, currentPlayer)) {
                board.makeMove(selectedPit, currentPlayer);
                currentPlayerIndex = (currentPlayerIndex + 1) % 2; // Switch players
            } else {
                System.out.println("Invalid move. Please choose a pit with stones.");
            }
        }while(!board.isGameOver());

        board.displayBoard();
        board.determineWinner();
        scanner.close();
    }
}
