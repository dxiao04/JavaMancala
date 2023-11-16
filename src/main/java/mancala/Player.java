package mancala;

public class Player {
    private String nam;
    private Store store;
    public Player(){
        nam = "";
        store = null;
    }
    public Player(String name){
        nam = name;
        store = null;
    }
    public String getName(){
        return nam;
    }
    public Store getStore(){
        return store;
    }
    public int getStoreCount(){
        return store.getTotalStones();
    }
    void setName(String name){
        nam = name;
    }
    void setStore(Store storeInp){
        store = storeInp;
    }
    @Override
    public String toString(){
        return "player name: " + nam + ". store has: " + store.getTotalStones();
    }
}
