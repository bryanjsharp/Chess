import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryan on 2/2/2016.
 */
public class RookTest {

    public Game initTestGame(){
        Game testGame = new Game(true);
        testGame.board = new Space[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                testGame.board[i][j] = new Space(i, j, testGame.board);
            }
        }
        return testGame;
    }

    @org.junit.Test
    public void testIsValidMove1() throws Exception {

        Game testGame = initTestGame();

        testGame.board[0][0].piece = new Rook(Piece.Color.WHITE);
        //checks a legal move
        assertEquals(true, testGame.board[0][0].piece.isValidMove(testGame.board[0][0], testGame.board[5][0], testGame.board));
    }
    @Test
    public void testIsValidMove2() throws Exception {
        Game testGame = initTestGame();

        testGame.board[0][0].piece = new Rook(Piece.Color.WHITE);
        //checks an illegal move
        assertEquals(false, testGame.board[0][0].piece.isValidMove(testGame.board[0][0], testGame.board[5][3], testGame.board));
    }

    @Test
    public void testIsValidMove3() throws Exception {
        Game testGame = initTestGame();
        //downward movement
        testGame.board[0][0].setOccupied(false);
        testGame.board[7][7].setOccupied(true);
        testGame.board[7][7].piece = new Rook(Piece.Color.WHITE);
        assertEquals(true, testGame.board[7][7].piece.isValidMove(testGame.board[7][7], testGame.board[7][0], testGame.board));

    }
    @Test
    public void testIsValidMove4() throws Exception {
        Game testGame = initTestGame();
        //leftward movement
        testGame.board[7][7].setOccupied(true);
        testGame.board[7][7].piece = new Rook(Piece.Color.WHITE);
        assertEquals(true, testGame.board[7][7].piece.isValidMove(testGame.board[7][7],
                testGame.board[0][7], testGame.board));
    }

    @Test
    public void testIsValidMove5() throws Exception {
        Game testGame = initTestGame();
        testGame.board[7][7].setOccupied(true);
        testGame.board[7][7].piece = new Rook(Piece.Color.WHITE);
        //a move where there is a piece friendly piece in the way
        testGame.board[2][7].setOccupied(true);
        testGame.board[2][7].piece = new Bishop(Piece.Color.WHITE);
        assertEquals(false, testGame.board[7][7].piece.isValidMove(testGame.board[7][7], testGame.board[0][7], testGame.board));
    }

    @Test
    public void testIsValidMove6() throws Exception {
        Game testGame = initTestGame();
        testGame.board[7][7].setOccupied(true);
        testGame.board[7][7].piece = new Rook(Piece.Color.WHITE);
        //attack an opponent
        testGame.board[6][7].setOccupied(true);
        testGame.board[6][7].piece = new Bishop(Piece.Color.BLACK);
        assertEquals(true, testGame.board[7][7].piece.isValidMove(testGame.board[7][7], testGame.board[6][7], testGame.board));
    }
}