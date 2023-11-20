package mancala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeBoard();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        board.registerPlayers(player1, player2);

    }
    private int getPitValue(int pit){
        return board.getPits().get(pit-1).getStoneCount();
    }

    private int getStoreValue(int store){
        return board.getStores().get(store-1).getTotalStones();
    }

    @Test
    public void testSetUpPitsAndGetPits() {

        for (Pit pit : board.getPits()) {
            assertEquals(4, pit.getStoneCount());
        }
    }



        @Test
    public void testMoveStonesValidMove() throws InvalidMoveException,PitNotFoundException {
        // Assuming you have a valid move (e.g., startPit = 1)
        int startPit = 3;

        // Perform the move
        int stonesAddedToStore = board.moveStones(startPit, player1);
        assertEquals(5,getPitValue(4));

        assertEquals(1,player1.getStoreCount());

    }

    @Test
    public void testMoveStonesInvalidMove() {
        // Assuming you have an invalid move (e.g., startPit = 0, which is out of bounds)

        // The method should throw an InvalidMoveException for an invalid move
        assertThrows(InvalidMoveException.class, () -> board.moveStones(14, player1));
    }

    @Test
    public void testCaptureStonesValid() throws PitNotFoundException ,Exception{
        // Manually set up the board for capturing opponent's stones
        // For example, arrange the pits and stones to simulate capturing
        // Player1's last stone lands in an empty pit on their side, capturing opponent's stones

        // Set up the board state
        board.getPits().get(3).removeStones();
        board.getPits().get(4).removeStones();
        board.getPits().get(3).addStone();

        // Perform the move that captures opponent's stones
        int captured = board.captureStones(5);

        // Assertions
        // You can assert the expected captured stones and other game conditions
        // For example, assert that player1's store has increased by the expected amount
        assertEquals(5, board.getStores().get(0).getTotalStones());
        assertEquals(5, captured);
        assertEquals(0, board.getPits().get(4).getStoneCount());
        assertEquals(0, board.getPits().get(7).getStoneCount());

    }
    
    @Test
    public void testCaptureStonesInvalidPit() throws PitNotFoundException, Exception{
        board.getPits().get(3).removeStones();
        board.getPits().get(4).removeStones();
        board.getPits().get(3).addStone();
        assertThrows(PitNotFoundException.class, ()->board.captureStones(13));
    }

    @Test
    public void testDistributeStonesValid() throws PitNotFoundException, Exception{
        int distributed = board.distributeStones(1);
        assertEquals(4, distributed);
        for (int i = 1; i < 5; i ++){
            assertEquals(5, board.getPits().get(i).getStoneCount());
        }
    }

    @Test
    public void testDistributeStonesValidStores() throws PitNotFoundException, Exception{
        int distributed = board.distributeStones(3);
        assertEquals(4, distributed);
        assertEquals(1, board.getStores().get(0).getTotalStones());
        for (int i = 3; i < 6; i ++){
            assertEquals(5, board.getPits().get(i).getStoneCount());
        }
    }

    @Test
    public void testDistributeStonesInvalid() throws PitNotFoundException, Exception{
        assertThrows(PitNotFoundException.class, ()->board.distributeStones(0));
    }

    @Test
    public void testDistributeStonesZero() throws PitNotFoundException, Exception{
        board.getPits().get(0).removeStones();
        int distributed = board.distributeStones(1);
        assertEquals(0, distributed);
        assertEquals(4, board.getPits().get(1).getStoneCount());
    }

    @Test
    public void testDistributeStonesValidCapture() throws PitNotFoundException, Exception{
        board.getPits().get(3).removeStones();
        board.getPits().get(4).removeStones();
        board.getPits().get(3).addStone();

        int distributed = board.distributeStones(4);
        assertEquals(5, distributed);
    }

    @Test
    public void testDistributeStonesSkipOtherStore() throws PitNotFoundException, Exception{
        board.getPits().get(5).removeStones();
        board.getPits().get(5).addStone(8);

        int distributed = board.distributeStones(6);
        assertEquals(8, distributed);
        assertEquals(1, board.getStores().get(0).getTotalStones());
        for (int i = 6; i < 12; i ++){
            assertEquals(5, board.getPits().get(i).getStoneCount());
        }
        assertEquals(0, board.getStores().get(1).getTotalStones());
        assertEquals(5, board.getPits().get(0).getStoneCount());
    }

    @Test
    public void testGetNumStonesValid() throws PitNotFoundException, Exception{
        board.getPits().get(0).removeStones();
        board.getPits().get(0).addStone(2);
        assertEquals(2, board.getNumStones(1));
    }
    @Test
    public void testGetNumStonesInvalid() throws PitNotFoundException, Exception{
        assertThrows(PitNotFoundException.class, ()->board.getNumStones(0));
    }

    @Test
    public void testIsSideEmptyValidTrue() throws PitNotFoundException, Exception{
        for (int i = 0; i < 6; i ++){
            board.getPits().get(i).removeStones();
        }
        boolean result = board.isSideEmpty(3);
        assertTrue(result);
    }

    @Test
    public void testIsSideEmptyValidFalse() throws PitNotFoundException, Exception{
        boolean result = board.isSideEmpty(3);
        assertFalse(result);
    }

    @Test
    public void testIsSideEmptyInvalid() throws PitNotFoundException, Exception{
        assertThrows(PitNotFoundException.class, ()->board.isSideEmpty(13));
    }

    @Test
    public void testResetBoard(){
        board.resetBoard();
        assertEquals(player1, board.getStores().get(0).getOwner());
        assertEquals(player2, board.getStores().get(1).getOwner());
        for (Pit p : board.getPits()){
            assertEquals(4, p.getStoneCount());
        }
        for (Store s : board.getStores()){
            assertEquals(0, s.getTotalStones());
        }
    }

    @Test
    public void testRegisterPlayers(){
        Player newP1 = new Player("new player 1");
        Player newP2 = new Player("new player 2");
        board.registerPlayers(newP1, newP2);
        assertEquals(newP1, board.getStores().get(0).getOwner());
        assertEquals(newP2, board.getStores().get(1).getOwner());
        assertEquals(board.getStores().get(0), newP1.getStore());
        assertEquals(board.getStores().get(1), newP2.getStore());
    }
}
