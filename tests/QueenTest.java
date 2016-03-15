import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/2/2016.
 */
public class QueenTest {

    public Game initTestGame(){
        Game testGame = new Game(true);
        testGame.initTest();
        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new Queen(Piece.Color.WHITE);
        return testGame;
    }
    @Test
    public void testIsValidMoveDiagonal() throws Exception {
        Game testGame = initTestGame();

        //diagonal movement
        assertEquals(true, testGame.board[0][0].piece.isValidMove(testGame.board[0][0],
                testGame.board[7][7], testGame.board));
    }
    @Test
    public void testIsValidMoveHorizontal() throws Exception {
        Game testGame = initTestGame();
        //horizontal movement
        assertEquals(true, testGame.board[0][0].piece.isValidMove(testGame.board[0][0],
                testGame.board[5][0], testGame.board));
    }
    @Test
    public void testIsValidMoveVertical() throws Exception {
        Game testGame = initTestGame();
        //vertical movement
        assertEquals(true, testGame.board[0][0].piece.isValidMove(testGame.board[0][0],
                testGame.board[0][5], testGame.board));
    }

}