/**
 * Created by bryan on 1/29/2016.
 */
public class Pawn extends Piece {

    public Pawn(Color color) {
        this.color = color;
        this.type = Type.PAWN;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space board[][]) {
        //check to see if it is a standard forward movement
        int correctNumOfSpaces = 0;
        //firstMove allows the pawn to move 2 spaces on the first move only
        int firstMove = 0;
        if (this.color == Color.BLACK) {
            if (currentSpace.getY() == 6) {
                firstMove = -1;
            }
            correctNumOfSpaces = -1;
        } else if (this.color == Color.WHITE) {
            if (currentSpace.getY() == 1) {
                firstMove = 1;
            }
            correctNumOfSpaces = 1;
        }
        if (destinationSpace.isOccupied()) {
            return isValidAttack(currentSpace, destinationSpace, correctNumOfSpaces);
        }
        return (((destinationSpace.getY() - currentSpace.getY()) == correctNumOfSpaces + firstMove)
                || ((destinationSpace.getY() - currentSpace.getY() == correctNumOfSpaces)))
                && ((destinationSpace.getX() - currentSpace.getX()) == 0);
    }

    private boolean isValidAttack(Space currentSpace, Space destinationSpace,
                                  int correctNumOfSpaces) {
        //Verifies that attacking is diagonal
        if (((destinationSpace.getX() - currentSpace.getX()) == -1)
                || destinationSpace.getX() - currentSpace.getX() == 1) {
            return (destinationSpace.piece.color != this.color)
                    && ((destinationSpace.getY() - currentSpace.getY())
                    == correctNumOfSpaces);
        }
        return false;
    }

    public Pawn copy(){
        Pawn result = new Pawn(this.color);
        return result;
    }
}
