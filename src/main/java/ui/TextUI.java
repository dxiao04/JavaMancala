package ui;
import java.util.Scanner;

import mancala.MancalaGame;
import mancala.Player;
import mancala.InvalidMoveException;
import mancala.GameNotOverException;
public class TextUI {
    public static void main(String[] args){
        MancalaGame mancalaGame = new MancalaGame();
        Scanner s = new Scanner(System.in);
        int currentP = 0; // 0 is 1 and 1 is 2
        int pitNum = -1;
        boolean isValidMove = false;

        System.out.println("enter player one's name");
        Player p1 = new Player(s.nextLine());
        System.out.println("enter player two's name");
        Player p2 = new Player(s.nextLine());
        mancalaGame.setPlayers(p1, p2);

        while(!mancalaGame.isGameOver()){
            mancalaGame.setCurrentPlayer(mancalaGame.getPlayers().get(currentP));
            System.out.println(mancalaGame.toString());
            System.out.println("current player is " + mancalaGame.getCurrentPlayer().getName());

            do{
                System.out.println("pick a pit to move from");
                pitNum = s.nextInt();
                isValidMove = false;
                try{
                    mancalaGame.move(pitNum);
                    isValidMove = true;
                }catch (InvalidMoveException iME){
                    System.out.println("invalid move. try again");
                }
            }
            while (!isValidMove);
            if (!mancalaGame.getBoard().isExtraTurn()){
                currentP = (currentP + 1) % 2;
            }
        }
        System.out.println(mancalaGame.toString());
        try{
            String winName = mancalaGame.getWinner().getName();
            if (winName != null){
                System.out.println("the winner is " + winName);
            }else{
                System.out.println("there is no winner because it was a tie");
            }
        }catch (GameNotOverException gNOE){
        }
    }
}
