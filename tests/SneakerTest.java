import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/10/2016.
 */
public class SneakerTest {

    public Game init(){
        Game testGame = new Game(true);
        testGame.initTest();

        testGame.board[3][3].setOccupied(true);
        testGame.board[3][3].piece = new Sneaker(Piece.Color.BLACK);
        return testGame;
    }

    @Test
    public void testIsValidMove1() throws Exception {
        Game testGame = init();
        assertEquals(true, testGame.board[3][3].piece.isValidMove(testGame.board[3][3],
                testGame.board[4][3], testGame.board));

    }

    @Test
    public void testIsValidMove2() throws Exception {
        Game testGame = init();
        assertEquals(false, testGame.board[3][3].piece.isValidMove(testGame.board[3][3],
                testGame.board[5][3], testGame.board));

    }

    @Test
    public void testIsValidMove3() throws Exception {
        Game testGame = init();
        assertEquals(true, testGame.board[3][3].piece.isValidMove(testGame.board[3][3],
                testGame.board[3][2], testGame.board));
    }


    @Test
    public void testCopy() throws Exception {
        Piece s = new Sneaker(Piece.Color.WHITE);
        Piece copyOfS = s.copy();

        assertEquals(copyOfS.color, Piece.Color.WHITE);
    }
}