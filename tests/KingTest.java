import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/6/16.
 */
public class KingTest {

    public Game init(){
        Game testGame = new Game(true);
        testGame.initTest();

        testGame.board[3][3].setOccupied(true);
        testGame.board[3][3].piece = new King(Piece.Color.BLACK);
        return testGame;
    }

    @Test
    public void testIsValidMove1() throws Exception {
        Game testGame = init();
        assertEquals(true, testGame.board[3][3].piece.isValidMove(testGame.board[3][3],
                testGame.board[4][4], testGame.board));
    }

    @Test
    public void testIsValidMove2() throws Exception {
        Game testGame = init();
        assertEquals(false, testGame.board[3][3].piece.isValidMove(testGame.board[3][3],
                testGame.board[5][5], testGame.board));
    }

    @Test
    public void testCopy() throws Exception {
        Piece k = new King(Piece.Color.WHITE);
        Piece copyOfK = k.copy();

        assertEquals(copyOfK.color, Piece.Color.WHITE);
    }
}