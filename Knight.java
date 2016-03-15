/**
 * Created by bryan on 2/1/2016.
 */
public class Knight extends Piece {
    public Knight(Color color) {
        this.color = color;
        this.type = Type.KNIGHT;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space board[][]) {
        int xDiff = Math.abs(destinationSpace.getX() - currentSpace.getX());
        int yDiff = Math.abs(destinationSpace.getY() - currentSpace.getY());
        if ((xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2)) {
            if (destinationSpace.isOccupied()) {
                return this.color != destinationSpace.piece.color;
            }
            return true;
        }
        return false;
    }

    public Knight copy(){
        Knight result = new Knight(this.color);
        return result;
    }
}
