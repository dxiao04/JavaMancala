package mancala;

public class Pit {
    private int stones = 0;

    public Pit(int numStones){
        stones = numStones;
    }
    public Pit(){
        stones = 0;
    }
    void addStone(){
        stones += 1;
    }
    void addStone(int stoneNum){
        stones += stoneNum;
    }
    int getStoneCount(){
        return stones;
    }
    int removeStones(){
        int count = stones;
        stones = 0;
        return count;
    }
    @Override
    public String toString(){
        return "stones in this pit: " + stones;
    }
}
