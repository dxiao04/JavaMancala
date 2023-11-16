package mancala;
import java.util.ArrayList;
public class Board {
    // 0-5 for p1, 6-11 for p2
    private ArrayList<Pit> pitArr = new ArrayList<Pit>();
    private ArrayList<Store> storeArr = new ArrayList<Store>();
    public Board(){
        setUpPits();
        setUpStores();
        initializeBoard();
    }
    public int captureStones(int stoppingPoint) throws PitNotFoundException{
        if (!isValidPit(stoppingPoint)){
            throw new PitNotFoundException();
        }else{
            int pitInd = stoppingPoint - 1;
            int otherPit = 11 - pitInd;
            int otherStones = pitArr.get(otherPit).getStoneCount();
            if (pitInd < 6){
                storeArr.get(0).addStones(1 + otherStones);
            }else{
                storeArr.get(1).addStones(1 + otherStones);
            }
            pitArr.get(pitInd).removeStones();
            pitArr.get(otherPit).removeStones();
            return otherStones;
        }
    }
    public int distributeStones(int startingPoint) throws PitNotFoundException{ 
        int player = 0;
        int stoneNum = 0;
        if(!isValidPit(startingPoint)){
            throw new PitNotFoundException();
        }else{
            int currPit = startingPoint - 1;
            stoneNum = getNumStones(startingPoint);
            int tempStones = stoneNum;
            if (currPit < 6){
                player = 1;
            }else{
                player = 2;
            }
            while (tempStones > 0){
                currPit = (currPit + 1) % 14;
                if (currPit == 13){
                    if (player == 1){
                        currPit = 0;
                    }else{
                        storeArr.get(1).addStones(1);
                        tempStones--;
                        continue;
                    }
                }else if (currPit == 6){
                    if (player == 2){
                        currPit = 7;
                    }else{
                        storeArr.get(0).addStones(1);
                        tempStones--;
                        continue;
                    }
                }
                if (currPit >= 0 && currPit < 6){
                    pitArr.get(currPit).addStone();
                    tempStones--;
                    continue;
                }else if (currPit <= 7 && currPit < 13){
                    pitArr.get(currPit - 1).addStone();
                    tempStones--;
                    continue;
                }
            }
        }
        return stoneNum;
    }
    int getNumStones(int pitNum) throws PitNotFoundException{
        if (!isValidPit(pitNum)){
            throw new PitNotFoundException();
        }else{
            int count = pitArr.get(pitNum - 1).getStoneCount();
            return count;
        }
    }
    ArrayList<Pit> getPits(){
        return pitArr;
    }
    ArrayList<Store> getStores(){
        return storeArr;
    }
    public void initializeBoard(){
        for (Pit p : pitArr){
            p.removeStones();
            p.addStone(4);
        }
    }
    boolean isSideEmpty(int pitNum) throws PitNotFoundException{
        if (!isValidPit(pitNum)){
            throw new PitNotFoundException();
        }else{
            int startingPit;
            if (pitNum <= 6){
                startingPit = 0;
            }else{
                startingPit = 6;
            }
            for (int i = startingPit; i < startingPit + 6; i++){
                if (pitArr.get(i).getStoneCount() > 0){
                    return false;
                }
            }
            return true;
        }
    }
    public int moveStones(int startPit, Player player) throws InvalidMoveException{
        // check validity
        
        return 0;
    }
    void registerPlayers(Player one, Player two){
        if (storeArr.size() > 0){
            one.setStore(storeArr.get(0));
            storeArr.get(0).setOwner(one);
            two.setStore(storeArr.get(1));
            storeArr.get(1).setOwner(two);
        }
    }
    void resetBoard(){ // REDESTRIBUTE STONES?
        for (Store s : storeArr){
            s.emptyStore();
        }
        initializeBoard();
    }
    void setUpPits(){
        pitArr.clear();
        for (int i = 0; i < 12; i++){
            pitArr.add(new Pit());
        }
    }
    void setUpStores(){
        storeArr.clear();
        storeArr.add(new Store());
        storeArr.add(new Store());
    }
    boolean isValidPit(int pitNum){
        if (pitNum < 1 || pitNum > 12){
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("player 2 (top)\n");
        for (int i = 11; i >= 6; i--) {
            strBuilder.append("[" + pitArr.get(i).getStoneCount() + "] ");
        }
        strBuilder.append("\n[" + storeArr.get(1).getTotalStones() + "]");
        strBuilder.append("\t\t    [" + storeArr.get(0).getTotalStones() + "]\n");
        for (int i = 0; i <= 5; i++) {
            strBuilder.append("[" + pitArr.get(i).getStoneCount() + "] ");
        }
        strBuilder.append("\nplayer 1 (bottom)\n");
        return strBuilder.toString();
    }
}
