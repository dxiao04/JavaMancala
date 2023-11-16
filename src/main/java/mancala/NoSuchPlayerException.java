package mancala;

public class NoSuchPlayerException extends Exception{
    public NoSuchPlayerException(){
        super("that player does not exist");
    }
}
