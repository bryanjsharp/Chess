/**
 * Created by bryan on 1/28/2016.
 */
public class Piece {
    //Will be used as a base class for specific pieces


    public enum Color {BLACK, WHITE, EMPTY}
    public enum Type {PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING, SNEAKER, SLIDER, EMPTY}

    public Type type;
    public Color color;

    //Each piece will define it's own behavior for isValidMove
    public Piece() {
        type = Type.EMPTY;
        color = Color.EMPTY;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board) {
        //if this function is running, it means the space is empty
        return false;
    }

    public boolean isValidCardinalMove(Space currentSpace, Space destinationSpace, Space[][] board) {
        int xDiff, yDiff;
        xDiff = destinationSpace.getX() - currentSpace.getX();
        yDiff = destinationSpace.getY() - currentSpace.getY();
        if ((xDiff == 0) || (yDiff == 0)) {
            if (xDiff == 0) {
                return this.isValidVerticalMove(currentSpace, destinationSpace,
                        Math.abs(yDiff), board);
            }
            if (yDiff == 0) {
                return this.isValidHorizontalMove(currentSpace, destinationSpace,
                        Math.abs(xDiff), board);
            }
        }
        return false;
    }

    private boolean isValidVerticalMove(Space currentSpace, Space destinationSpace,
                                        int yDiff, Space[][] board) {
        //check to see if destination is occupied
        if (yDiff > 0) {
            for (int i = currentSpace.getY(); i < (destinationSpace.getY() - 1); i++) {
                if (board[currentSpace.getX()][i].isOccupied()) {
                    return false;
                }
            }
        }
        if (yDiff < 0) {
            for (int i = destinationSpace.getY() + 1; i < currentSpace.getY(); i++) {
                if (board[currentSpace.getX()][i].isOccupied()) {
                    return false;
                }
            }
        }
        if (destinationSpace.isOccupied() && (this.color == destinationSpace.piece.color)) {
            return false;
        }
        return true;
    }

    private boolean isValidHorizontalMove(Space currentSpace, Space destinationSpace,
                                          int xDiff, Space[][] board) {
        if (xDiff > 0) {
            for (int i = currentSpace.getX(); i < (destinationSpace.getX() - 1); i++) {
                if (board[i][currentSpace.getY()].isOccupied()) {
                    return false;
                }
            }
        }
        if (xDiff < 0) {
            for (int i = destinationSpace.getX() + 1; i < currentSpace.getX(); i++) {
                if (board[i][currentSpace.getY()].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidDiagonalMove(Space currentSpace, Space destinationSpace, Space [][] board) {
        int diff;
        //Checks to see that the bishop is moving diagonally
        if (Math.abs(destinationSpace.getX() - currentSpace.getX()) !=
                Math.abs(destinationSpace.getY() - currentSpace.getY())) {
            return false;
        }
        //use a multiplier of -1 or 1 depending on the direction the bishop is heading
        int xMultiplier = (destinationSpace.getX() - currentSpace.getX()) >= 0 ? 1 : -1;
        int yMultiplier = (destinationSpace.getY() - currentSpace.getY()) >= 0 ? 1 : -1;
        diff = Math.abs(destinationSpace.getX() - currentSpace.getX());
        //yDiff = Math.abs(destinationSpace.getY() - currentSpace.getY());

        for (int i = 1; i < diff - 1; i++) {    //the -1 is so we can check the destSpace for an attack
            if (board[currentSpace.getX() + (i * xMultiplier)]
                    [currentSpace.getY() + (i * yMultiplier)].isOccupied()) {
                return false;
            }
            if (destinationSpace.isOccupied()) {
                return this.color != destinationSpace.piece.color;
            }
        }
        return true;
    }

    // will return a new copy of piece p
    public Piece copy(){
        //implement for each class
        Piece result = new Piece();
        return result;
    }

}
