/**
 * Created by bryan on 1/28/2016.
 */
public class Space {
    private int x, y;
    private boolean occupied;
    public Piece piece;
    public Space[][] board;

    public Space(int x, int y, Piece piece, Space[][] board) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.board = board;
        setOccupied(true);
        //set x and y
        //maybe if there is a piece on it
    }

    public Space(int x, int y, Space[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
        setOccupied(false);
        this.piece = new Piece();
    }

    public Space(Space original, Space[][] board){
        this.x = original.getX();
        this.y = original.getY();
        setOccupied(original.isOccupied());
    }

    public Space copy(){
        Space result = new Space(this.getX(), this.getY(), this.board);
        result.setOccupied(this.isOccupied());
        if (result.isOccupied()){
           result.piece = this.piece.copy();
        }
        return result;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }


}
