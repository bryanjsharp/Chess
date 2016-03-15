import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/2/2016.
 */
public class BishopTest {

    @Test
    public void testIsValidMove1() throws Exception {
        Game testGame = new Game(true);
        testGame.initTest();

        //check movement
        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new Bishop(Piece.Color.WHITE);
        assertEquals(true,
                testGame.board[0][0].piece.isValidMove(testGame.board[0][0],
                        testGame.board[7][7], testGame.board));
    }

    @Test
    public void testIsValidMove2() throws Exception {

        Game testGame = new Game(true);
        testGame.initTest();

        //check movement
        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new Bishop(Piece.Color.WHITE);
        //check invalid move
        assertEquals(false,
                testGame.board[0][0].piece.isValidMove(testGame.board[0][0],
                        testGame.board[7][6], testGame.board));
    }

    @Test
    public void testIsValidMove3()throws Exception {

        Game testGame = new Game(true);
        testGame.initTest();

        //check piece in path
        testGame.board[2][2].setOccupied(true);
        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new Bishop(Piece.Color.WHITE);

        assertEquals(false,
                testGame.board[0][0].piece.isValidMove(testGame.board[0][0],
                        testGame.board[7][7], testGame.board));
    }

    @Test
    public void testIsValidMove4() throws Exception {

        Game testGame = new Game(true);
        testGame.initTest();
        //move in reverse
        testGame.board[7][7].setOccupied(true);

        testGame.board[7][7].piece = new Bishop(Piece.Color.WHITE);

        assertEquals(true,
                testGame.board[7][7].piece.isValidMove(testGame.board[7][7],
                        testGame.board[1][1], testGame.board)
        );
    }


}
