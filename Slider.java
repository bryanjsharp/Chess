/**
 * Created by bryan on 2/7/2016.
 */
public class Slider extends Piece {
    //this piece can only one space diagonally
    public Slider(Color color){
        this.color = color;
        this.type = Type.SLIDER;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board){
        if(destinationSpace.isOccupied() && (this.color == destinationSpace.piece.color)){
            return false;
        }

        if((Math.abs(destinationSpace.getX() - currentSpace.getX()) == 1) &&
                (Math.abs(destinationSpace.getY()-currentSpace.getY())) == 1){
            return true;
        }
        return false;
    }

}
