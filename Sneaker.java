/**
 * Created by bryan on 2/10/2016.
 */
public class Sneaker extends Piece {

    public Sneaker(Color color){
        this.color = color;
        this.type = Type.SNEAKER;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board){
        if( Math.abs(destinationSpace.getX() - currentSpace.getX()) == 1){
            if(destinationSpace.getY() - currentSpace.getY() == 0){
                if(!destinationSpace.isOccupied()){
                    return true;
                }
                if(destinationSpace.isOccupied() &&
                        (destinationSpace.piece.color != currentSpace.piece.color)){
                    return true;
                }
            }
        }
        else if (Math.abs(destinationSpace.getY() - currentSpace.getY()) == 1){
            if(destinationSpace.getX() - currentSpace.getX() == 0){
                if(!destinationSpace.isOccupied()){
                    return true;
                }
                if(destinationSpace.isOccupied() &&
                        (destinationSpace.piece.color != currentSpace.piece.color)){
                    return true;
                }
            }
        }
        return false;
    }

    public Sneaker copy(){
        Sneaker result = new Sneaker(this.color);
        return result;
    }
}
