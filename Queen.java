/**
 * Created by bryan on 2/1/2016.
 */
public class Queen extends Piece {

    //creates a new Queen of specified color
    public Queen(Color color) {
        this.color = color;
        this.type = Type.QUEEN;
    }

    //determines which direction the queen is heading and checks if it's valid
    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board) {
        if ((destinationSpace.getX() - currentSpace.getX() == 0) ||
                (destinationSpace.getY() - currentSpace.getY() == 0)) {
            return isValidCardinalMove(currentSpace, destinationSpace, board);
        }
        if (Math.abs(destinationSpace.getX() - currentSpace.getX()) ==
                Math.abs(destinationSpace.getY() - currentSpace.getY())) {
            return isValidDiagonalMove(currentSpace, destinationSpace, board);
        }
        return false;
    }


    //returns a new instance of Queen
    public Queen copy(){
        Queen result = new Queen(this.color);
        return result;
    }

}


