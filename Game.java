/**
 * Created by bryan on 1/28/2016.
 */

import java.util.*;

public class Game {
    public Space[][] board;
    public boolean isOver;
    public boolean turnIsOver;
    public Space whiteKing, blackKing;
    public int blackKingX, blackKingY;
    public int whiteKingX, whiteKingY;
    public int sourceX, sourceY, destX, destY;
    int sourceOrDest;
    Piece.Color turn;
    Stack stack;

    //this variable will keep track of button pushes.
    //If 0, it is selecting the source piece. When 1, it is the destination.

    //new game
    public Game() {
        board = new Space[8][8];
        boardInit(board);
        isOver = false;
        turnIsOver = false;
        sourceOrDest = 0;
        sourceX = 0;
        sourceY = 0;
        destX = 0;
        destY = 0;
        turn = Piece.Color.WHITE;
        stack = new Stack();
    }

    //constructor used only for testing
    public Game(boolean test) {
        isOver = false;
    }

    //returns true if successful, false if not successful
    public void boardInit(Space[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Space(i, j, new Piece(), board);
            }
        }
        //white pieces
        board[0][0].piece = new Rook(Piece.Color.WHITE);
        board[1][0].piece = new Knight(Piece.Color.WHITE);
        board[2][0].piece = new Bishop(Piece.Color.WHITE);
        board[3][0].piece = new Queen(Piece.Color.WHITE);
        board[4][0].piece = new King(Piece.Color.WHITE);
        board[4][0] = whiteKing;
        board[5][0].piece = new Bishop(Piece.Color.WHITE);
        board[6][0].piece = new Knight(Piece.Color.WHITE);
        board[7][0].piece = new Rook(Piece.Color.WHITE);
        for (int i = 0; i < 8; i++) {
            board[i][1].piece = new Pawn(Piece.Color.WHITE);
        }
        //black pieces
        board[0][7].piece = new Rook(Piece.Color.BLACK);
        board[1][7].piece = new Knight(Piece.Color.BLACK);
        board[2][7].piece = new Bishop(Piece.Color.BLACK);
        board[3][7].piece = new Queen(Piece.Color.BLACK);
        board[4][7].piece = new King(Piece.Color.BLACK);
        board[4][7] = blackKing;
        board[5][7].piece = new Bishop(Piece.Color.BLACK);
        board[6][7].piece = new Knight(Piece.Color.BLACK);
        board[7][7].piece = new Rook(Piece.Color.BLACK);
        for (int i = 0; i < 8; i++) {
            board[i][6].piece = new Pawn(Piece.Color.BLACK);
        }

        //mark empty spaces as unoccupied
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                board[i][j].setOccupied(false);
            }
        }
    }

    //returns true if successful, false if failed
    public boolean move(Space sourceSpace, Space destinationSpace,
                        Space[][] board, Piece.Color turn) {
        if (!sourceSpace.isOccupied()) {
            return false;
        }
        if (sourceSpace.piece.color != turn) {
            return false;
        }

        if (sourceSpace.piece.isValidMove(sourceSpace, destinationSpace, board)) {
            //replace destinationSpace's piece with the one at source
            board[destinationSpace.getX()][destinationSpace.getY()].setOccupied(true);
            board[sourceSpace.getX()][sourceSpace.getY()].setOccupied(false);
            //destinationSpace.piece = sourceSpace.piece;
            board[destinationSpace.getX()][destinationSpace.getY()].piece = sourceSpace.piece;
            return true;
        }
        return false;
    }

    public void setKingSpace(Piece.Color color, int x, int y) {
        if (color == Piece.Color.WHITE) {
            whiteKing.setX(x);
            whiteKing.setY(y);
        }
        if (color == Piece.Color.BLACK) {
            blackKing.setX(x);
            blackKing.setY(y);
        }
    }

    public boolean isInCheck(Piece.Color color) {
        //find the king of the specified color and determine if it is in check
        int kingX = 0;
        int kingY = 0;
        Piece.Color opponentColor = Piece.Color.EMPTY;
        if (color == Piece.Color.WHITE) {
            kingX = this.whiteKing.getX();
            kingY = this.whiteKing.getY();
            opponentColor = Piece.Color.BLACK;
        }
        if (color == Piece.Color.BLACK) {
//            kingX = this.blackKing.getX();
//            kingY = this.blackKing.getY();
            kingX = this.blackKingX;
            kingY = this.blackKingY;
            opponentColor = Piece.Color.WHITE;
        }


        //check every space to see if they can attack
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((opponentColor == this.board[i][j].piece.color) &&
                        (this.board[i][j].piece.isValidMove(this.board[i][j],
                                this.board[kingX][kingY], this.board))) {
                    return true;
                }
            }
        }
        return false;
    }

    //Check every possible move of every piece of the same color and
    //check if any of those moves will remove king from check.
    //Should only be called if in Check
    public boolean isCheckmate(Space kingSpace, Space[][] board) {

        boolean checkFound;
        Space tempSource, tempDest;
        Piece.Color kingColor = kingSpace.piece.color;
        Piece.Color opponentColor = Piece.Color.EMPTY;

        //determine the correct colors to use
        if (kingColor == Piece.Color.BLACK) {
            opponentColor = Piece.Color.WHITE;
        } else if (kingColor == Piece.Color.WHITE) {
            opponentColor = Piece.Color.BLACK;
        }

        //loop through all spaces to find enemy pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isOccupied() &&
                        board[i][j].piece.color == opponentColor) {
                    tempSource = board[i][j].copy();
                    for (int x = 0; i < 8; x++) {
                        for (int y = 0; i < 8; y++) {
                            //if its a valid move, move piece and see if
                            //the king is still in check
                            if (board[i][j].piece.isValidMove(board[i][j],
                                    board[x][y], board)) {
                                tempDest = board[x][y].copy();
                                move(board[i][j], board[x][y], board, opponentColor);
                                checkFound = isInCheck(kingColor);
                                board[i][j] = tempSource;
                                board[x][y] = tempDest;
                                if (!checkFound) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }

        }

        //if no possible moves take the king out of check...
        return true;
    }

    public void initTest() {
        this.board = new Space[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Space(i, j, this.board);
            }
        }
    }


    //this class will contain the necessary data to track a turn
    //this "Turn" object will then be pushed onto the stack within game
    //and popped when the undo button is pressed.
    class Turn {
        int sourceX, sourceY, destX, destY;
        Piece.Type sourcePiece, destPiece;
        Piece.Color sourceColor, destColor;
        boolean sourceIsOccupied, destIsOccupied;

        public Turn(Game game) {
            this.sourceX = game.sourceX;
            this.sourceY = game.sourceY;
            this.destX = game.destX;
            this.destY = game.destY;
            this.sourceColor = game.board[sourceX][sourceY].piece.color;
            this.destColor = game.board[destX][destY].piece.color;
            this.sourcePiece = game.board[sourceX][sourceY].piece.type;
            this.destPiece = game.board[destX][destY].piece.type;
            this.sourceIsOccupied = game.board[sourceX][sourceY].isOccupied();
            this.destIsOccupied = game.board[destX][destY].isOccupied();
        }
    }

    public void pushTurn(Turn turn) {
        stack.push(turn);
    }

    //takes a snapshot of the movement information and pushes it onto the stack
    public void endTurn() {
        Turn t = new Turn(this);
        this.pushTurn(t);
        if (turn == Piece.Color.WHITE) {
            turn = Piece.Color.BLACK;
        } else {
            turn = Piece.Color.WHITE;
        }
        return;
    }

}





