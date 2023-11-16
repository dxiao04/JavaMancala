package mancala;
import java.util.ArrayList;
public class MancalaGame {
    private ArrayList<Player> playerArr = new ArrayList<Player>();
    private Board board = new Board();
    private Player currPlayer = new Player();
    public MancalaGame(){
        board.resetBoard();
    }
    public Board getBoard(){
        return board;
    }
    public Player getCurrentPlayer(){
        return currPlayer;
    }
    public int getNumStones(int pitNum) throws PitNotFoundException{
        if (!board.isValidPit(pitNum)){
            throw new PitNotFoundException();
        }else{
            return board.getNumStones(pitNum);
        }
    }
    public ArrayList<Player> getPlayers(){
        return playerArr;
    }
    public int getStoreCount(Player player) throws NoSuchPlayerException{
        return 0;
    }
    public Player getWinner() throws GameNotOverException{
        if (!isGameOver()){
            throw new GameNotOverException();
        }else{
            int p1Total = 0;
            for (int i = 0; i < 6; i++){
                try {
                    p1Total += board.getNumStones(i + 1);
                } catch (PitNotFoundException pNFE) {
                    return null;
                }
            }
            p1Total += playerArr.get(0).getStoreCount();
            int p2Total = 0;
            for (int i = 6; i < 12; i++){
                try {
                    p2Total += board.getNumStones(i + 1);
                }catch (PitNotFoundException pNFE) {
                    return null;
                }
            }
            p2Total += playerArr.get(1).getStoreCount();
            if (p1Total == p2Total){
                return null;
            }else if (p1Total > p2Total){
                return playerArr.get(0);
            }else{
                return playerArr.get(1);
            }
        }
    }
    public boolean isGameOver(){
        try {
            if (board.isSideEmpty(1) || board.isSideEmpty(7)){
                return true;
            }
            return false;
        } catch (PitNotFoundException pNFE) {
            return false;
        }
    }
    public int move(int startPit) throws InvalidMoveException{
        if (!board.isValidPit(startPit)){
            throw new InvalidMoveException();
        }else{
             
        }
        return 0;
    }
    public void setBoard(Board theBoard){
        board = theBoard;
    }
    public void setCurrentPlayer(Player player){
        currPlayer = player;
    }
    public void setPlayers(Player onePlayer, Player twoPlayer){
        board.registerPlayers(onePlayer, twoPlayer);
        playerArr.add(onePlayer);
        playerArr.add(twoPlayer);
    }
    public void startNewGame(){

    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(board.toString() + "\n");
        return "";
    }
}
