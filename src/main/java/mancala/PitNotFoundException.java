package mancala;

public class PitNotFoundException extends Exception{
    public PitNotFoundException(){
        System.out.println("invalid pit. pit numbers are 1-6 for player one and 7-12 for player two");
    }
}
