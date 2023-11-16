package ui;
import java.util.Scanner;

import mancala.MancalaGame;
import mancala.Player;
public class TextUI {
    private static MancalaGame mancalaGame = new MancalaGame();
    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args){
       setUpPlayers();

    }
    static void setUpPlayers(){
        System.out.println("enter player one's name");
        Player p1 = new Player(s.nextLine());
        System.out.println("enter player two's name");
        Player p2 = new Player(s.nextLine());
        mancalaGame.setPlayers(p1, p2);
    }
}
