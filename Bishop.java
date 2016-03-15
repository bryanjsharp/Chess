/**
 * Created by bryan on 2/1/2016.
 */
public class Bishop extends Piece {

    public Bishop(Color color) {
        this.color = color;
        type = Type.BISHOP;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board) {
        return isValidDiagonalMove(currentSpace, destinationSpace, board);
    }
    //return a copy of bishop p
    public Bishop copy(){
        Bishop result = new Bishop(this.color);
        return result;
    }
}
