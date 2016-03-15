import static org.junit.Assert.*;

/**
 * Created by bryan on 2/2/2016.
 */
public class SpaceTest {

    @org.junit.Test
    public void testGetX() throws Exception {
        Space[][] board = new Space[8][8];
        board[3][3] = new Space(3, 3,new Bishop(Piece.Color.WHITE), board);
        assertEquals(board[3][3].getX(), 3);

    }

    @org.junit.Test
    public void testGetY() throws Exception {
        Space[][] board = new Space[8][8];
        board[3][3] = new Space(3, 3,new Bishop(Piece.Color.WHITE), board);
        assertEquals(board[3][3].getY(), 3);
    }

    @org.junit.Test
    public void testIsOccupied() throws Exception {
        Space[][] board = new Space[8][8];
        board[3][3] = new Space(3, 3,new Bishop(Piece.Color.WHITE), board);
        board[3][3].setOccupied(true);
        assertEquals(board[3][3].isOccupied(), true);

    }
}