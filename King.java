/**
 * Created by bryan on 2/2/2016.
 */
public class King extends Piece {

    public King(Color color) {
        this.color = color;
        this.type = Type.KING;
    }

    //This function will only check if the king could possibly move to the spot.
    //The Game class will determine if the move will result in check or checkmate
    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board) {
        //if the spot is more than one space away, return false
        if (!(Math.abs(destinationSpace.getX() - currentSpace.getX()) <= 1) ||
                !(Math.abs(destinationSpace.getY() - currentSpace.getY()) <= 1)) {
            return false;
        }
        if (destinationSpace.isOccupied() && (this.color == destinationSpace.piece.color)) {
            return false;
        }
        return true;
    }

    public King copy(){
        King result = new King(this.color);
        return result;
    }
}
