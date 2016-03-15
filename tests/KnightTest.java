import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/2/2016.
 */
public class KnightTest {

    @Test
    public void testIsValidMove1() throws Exception {

        Game testGame = new Game(true);
        testGame.initTest();

        //basic movement
        testGame.board[4][4].setOccupied(true);
        testGame.board[4][4].piece = new Knight(Piece.Color.WHITE);
        assertEquals(true, testGame.board[4][4].piece.isValidMove(testGame.board[4][4],
                testGame.board[6][5], testGame.board));
    }

    @Test
    public void testIsValidMove2() throws Exception {
        Game testGame = new Game();
        testGame.initTest();
        testGame.board[4][4].setOccupied(true);
        testGame.board[4][4].piece = new Knight(Piece.Color.WHITE);
        assertEquals(false,
                testGame.board[4][4].piece.isValidMove(testGame.board[4][4],
                        testGame.board[5][5], testGame.board));


    }
}