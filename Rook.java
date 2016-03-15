/**
 * Created by bryan on 2/1/2016.
 */
public class Rook extends Piece {
    //TODO: refactor
    public Rook(Color color) {
        this.color = color;
        this.type = Type.ROOK;
    }

    public boolean isValidMove(Space currentSpace, Space destinationSpace, Space[][] board) {
        return isValidCardinalMove(currentSpace, destinationSpace, board);
    }

    public Rook copy(){
        Rook result = new Rook(this.color);
        return result;
    }


}
