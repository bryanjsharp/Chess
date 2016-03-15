import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/3/2016.
 */
public class PawnTest {

    public Game initPawnTest() {
        Game testGame = new Game(true);
        testGame.initTest();

        testGame.board[0][1].setOccupied(true);
        testGame.board[0][1].piece = new Pawn(Piece.Color.WHITE);

        return testGame;
    }

    @Test
    public void testIsValidMove1() throws Exception {

        Game testGame = initPawnTest();

        //check regular move
        assertEquals(true, testGame.board[0][1].piece.isValidMove(testGame.board[0][1],
                testGame.board[0][2], testGame.board));
    }

    @Test
    public void testIsValidFirstMove() throws Exception {
        Game testGame = initPawnTest();
        //check first move
        assertEquals(true, testGame.board[0][1].piece.isValidMove(testGame.board[0][1], testGame.board[0][3], testGame.board));
    }
    @Test
    public void testIfValidAttack() throws Exception {
        Game testGame = initPawnTest();
        //attack
        testGame.board[1][2].setOccupied(true);
        testGame.board[1][2].piece = new Bishop(Piece.Color.BLACK);
        assertEquals(true, testGame.board[0][1].piece.isValidMove(testGame.board[0][1], testGame.board[1][2], testGame.board));
    }
}