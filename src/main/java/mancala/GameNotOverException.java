package mancala;

public class GameNotOverException extends Exception{
    public GameNotOverException(){
        super("game not over");
    }
}
