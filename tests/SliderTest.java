import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/10/2016.
 */
public class SliderTest {

    public Game initTestGame(){
        Game testGame = new Game(true);
        testGame.initTest();
        testGame.board[4][4].setOccupied(true);
        testGame.board[4][4].piece = new Slider(Piece.Color.WHITE);
        return testGame;
    }

    @Test
    public void testIsValidMove1() throws Exception {
        Game testGame = initTestGame();

        assertEquals(true, testGame.board[4][4].piece.isValidMove(testGame.board[4][4],
                testGame.board[3][3], testGame.board));

        return;
    }

    @Test
    public void testIsValidMove2() throws Exception {
        Game testGame = initTestGame();
        assertEquals(false, testGame.board[4][4].piece.isValidMove(testGame.board[4][4],
                testGame.board[4][3], testGame.board));
    }

    @Test
    public void testIsValidMove3() throws Exception {
        Game testGame = initTestGame();
        assertEquals(false, testGame.board[4][4].piece.isValidMove(testGame.board[4][4],
                testGame.board[6][6], testGame.board));
    }

}