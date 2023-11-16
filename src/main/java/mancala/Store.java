package mancala;

public class Store {
    private int stones;
    private Player owner;
    public Store(int sAmount, Player sOwner){
        stones = sAmount;
        owner = sOwner;
    }
    public Store(){
        stones = 0;
        owner = null;
    }
    void addStones(int amount){
        stones += amount;
    }
    int emptyStore(){
        int count = stones;
        stones = 0;
        return count;
    }
    Player getOwner(){
        return owner;
    }  
    int getTotalStones(){
        return stones;
    }
    void setOwner(Player player){
        owner = player;
    }
    @Override
    public String toString(){
        return "stones in " + owner + "'s store: " + stones;
    }
}
