import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/3/2016.
 */
public class GameTest {

    @Test
    public void testBoardInit() throws Exception {


    }

    @Test
    public void testMove1() throws Exception {
        Game testGame = new Game(true);
        testGame.initTest();

        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new Bishop(Piece.Color.WHITE);

        //move bishop from bottom left corner to top right
        testGame.move(testGame.board[0][0], testGame.board[7][7],
                testGame.board, Piece.Color.WHITE);

        //check if dest is occupied
        assertEquals(true, testGame.board[7][7].isOccupied());

        //check to see that source is no longer occupied
        assertEquals(false, testGame.board[0][0].isOccupied());

        //see if dest has a bishop
        assertEquals(Piece.Type.BISHOP, testGame.board[7][7].piece.type);

    }


    @Test
    public void testMove2() throws Exception {
        Game testGame = new Game(true);
        testGame.initTest();


        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new Bishop(Piece.Color.WHITE);

        //move bishop from bottom left corner to top right
        testGame.move(testGame.board[0][0], testGame.board[7][7],
                testGame.board, Piece.Color.WHITE);

        //move from the destination to another space
        testGame.move(testGame.board[7][7], testGame.board[5][5],
                testGame.board, Piece.Color.WHITE);
        assertEquals(true, testGame.board[5][5].isOccupied());
    }

    @Test
    public void testIsInCheck() throws Exception {
        Game testGame = new Game(true);
        testGame.initTest();


        testGame.blackKing = testGame.board[0][0];
        testGame.board[0][0].setOccupied(true);
        testGame.board[0][0].piece = new King(Piece.Color.BLACK);

        //tests empty board for check
        assertEquals(false, testGame.isInCheck(Piece.Color.BLACK));

        //test bishop with possible attack on king
        testGame.board[6][6].setOccupied(true);
        testGame.board[6][6].piece = new Bishop(Piece.Color.WHITE);
        assertEquals(true, testGame.isInCheck(Piece.Color.BLACK));

        //test blocked check
        testGame.board[5][5].setOccupied(true);
        testGame.board[5][5].piece = new Rook(Piece.Color.BLACK);
        assertEquals(false, testGame.isInCheck(Piece.Color.BLACK));


    }

    @Test
    public void testIsCheckmate() throws Exception {
        Game testGame = new Game(true);
        testGame.initTest();

        testGame.blackKing = testGame.board[3][3];
        testGame.board[6][0].setOccupied(true);
        testGame.board[6][0].piece = new King(Piece.Color.BLACK);
        testGame.setKingSpace(Piece.Color.BLACK, 6, 0);

        assertEquals(false, testGame.isInCheck(Piece.Color.BLACK));
        //assertEquals(false, testGame.isCheckmate(testGame.blackKing, testGame.board));

        testGame.board[2][0].setOccupied(true);
        testGame.board[2][0].piece = new Rook(Piece.Color.WHITE);
        testGame.board[7][1].setOccupied(true);
        testGame.board[7][1].piece = new Pawn(Piece.Color.BLACK);
        testGame.board[6][1].setOccupied(true);
        testGame.board[6][1].piece = new Pawn(Piece.Color.BLACK);
        testGame.board[5][1].setOccupied(true);
        testGame.board[5][1].piece = new Pawn(Piece.Color.BLACK);
        //test Checkmate
        assertEquals(true, testGame.isInCheck(Piece.Color.BLACK));
        assertEquals(true, testGame.isCheckmate(testGame.blackKing, testGame.board));
    }
}