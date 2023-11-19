package ai;
public class Board {
    private int[] pits = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};

    public Board(){

    }
    public void displayBoard() {
        System.out.println("Player B (top)\n");
        for (int i = 12; i >= 7; i--) {
            System.out.print("[" + pits[i] + "] ");
        }
        System.out.println("\n[" + pits[13] + "]\t\t    [" + pits[6] + "]");
        for (int i = 0; i <= 5; i++) {
            System.out.print("[" + pits[i] + "] ");
        }
        System.out.println("\nPlayer A (bottom)\n");
    }
    public boolean isValidMove(int selectedPit, Player currentPlayer) {
        return (currentPlayer.getId() == 0 && selectedPit >= 0 && selectedPit <= 5 && pits[selectedPit] > 0) ||
                (currentPlayer.getId() == 1 && selectedPit >= 7 && selectedPit <= 12 && pits[selectedPit] > 0);
    }
    public void makeMove(int selectedPit, Player currentPlayer) {
        int stones = pits[selectedPit];
        pits[selectedPit] = 0;
        int currentPit = selectedPit;

        while (stones > 0) {
            currentPit = (currentPit + 1) % 14;
            if (currentPlayer.getId() == 0 && currentPit == 13) {
                currentPit = 0; // Skip Player B's Mancala
            } else if (currentPlayer.getId() == 1 && currentPit == 6) {
                currentPit = 7; // Skip Player A's Mancala
            }

            pits[currentPit]++;
            stones--;
        }
        // Handle capturing stones
        if (currentPlayer.getId() == 0 && currentPit >= 0 && currentPit <= 5 && 
                            pits[currentPit] == 1 && pits[12 - currentPit] > 0) {
            pits[6] += pits[currentPit] + pits[12 - currentPit];
            pits[currentPit] = pits[12 - currentPit] = 0;
        } else if (currentPlayer.getId() == 1 && currentPit >= 7 && currentPit <= 12 && 
                                                    pits[currentPit] == 1 && pits[12 - currentPit] > 0) {
            pits[13] += pits[currentPit] + pits[12 - currentPit];
            pits[currentPit] = pits[12 - currentPit] = 0;
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < 6; i++) {
            if (pits[i] != 0) return false;
        }
        for (int i = 7; i < 13; i++) {
            if (pits[i] != 0) return false;
        }
        return true;
    }

    public void determineWinner() {
        if (pits[6] > pits[13]) {
            System.out.println("Player A wins!");
        } else if (pits[6] < pits[13]) {
            System.out.println("Player B wins!");
        } else {
            System.out.println("It's a tie!");
        }

    }
}