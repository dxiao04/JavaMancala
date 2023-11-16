package mancala;

public class GameNotOverException extends Exception{
    public GameNotOverException(){
        System.out.println("game not over");
    }
}
