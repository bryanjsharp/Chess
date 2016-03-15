/**
 * Created by bryan on 2/9/2016.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ChessBoard {

    //private JPanel gui = new JPanel(new BorderLayout(1,2));
    private JPanel gui = new JPanel();
    private JButton[][] squares = new JButton[8][8];
    private JPanel chessBoard;

    ChessBoard(Game game) {
        initGui(game);
    }

    //initializes the gui
    public void initGui(Game game) {
        //make the grid
        chessBoard = new JPanel(new GridLayout(0, 8));
        JToolBar toolBar = new JToolBar();
        JLabel statusBar = new JLabel(game.turn + "'s turn");
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new EndTurnListener(game, statusBar));
        toolBar.add(endTurnButton);
        JButton surrenderButton = new JButton("Surrender");
        surrenderButton.addActionListener(new SurrenderListener(game));
        toolBar.add(surrenderButton);
        toolBar.add(statusBar);
        gui.add(toolBar);
        gui.add(chessBoard);

        //create the squares
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {

                ImageIcon icon;
                //if the space should be empty, put a transparent image on it
                //the chess pieces are 45x45 pixels so make the transparent image 45x45
                if (i > 1 && i < 6) {
                    icon = new ImageIcon(new BufferedImage(45, 45, BufferedImage.TYPE_INT_ARGB));
                } else {
                    icon = new ImageIcon(initPieces(j, i));
                }

                JButton button = new JButton(icon);
                final int col = j;
                final int row = i;

                //create the unique listener for this specific button
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (game.sourceOrDest == 0) {
                            game.sourceX = col;
                            game.sourceY = row;
                            game.sourceOrDest = 1;
                        } else if (game.sourceOrDest == 1) {
                            game.destX = col;
                            game.destY = row;
                            game.sourceOrDest = 0;
                        } else
                            return;
                    }
                });
                //creates the checkboard pattern on the grid
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    button.setBackground(Color.white);
                } else {
                    button.setBackground(Color.CYAN);
                }
                squares[j][i] = button;

                //fill the rows
                chessBoard.add(squares[j][i]);
            }
        }

    }

    //this listen is for the EndTurn button, it will attempt to move the piece
    //and switch the turn. If it cannot, print an error message.
    class EndTurnListener implements ActionListener {
        public Game game;
        JLabel label;

        public EndTurnListener(Game game, JLabel status) {
            this.game = game;
            label = status;
        }

        public void actionPerformed(ActionEvent e) {
            if (game.move(game.board[game.sourceX][game.sourceY],
                    game.board[game.destX][game.destY], game.board, game.turn)) {
                game.endTurn();
                label.setText(game.turn + "'s turn");
                scan(game);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Move", "Cannot Move",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //Action Listener for the surrender button
    class SurrenderListener implements ActionListener {
        private Game game;

        public SurrenderListener(Game game) {
            this.game = game;
        }

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, game.turn + " Surrendered", "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            game.isOver = true;
        }
    }

    //returns the location of the image that should be placed on the board
    public String initPieces(int x, int y) {

        //white pieces
        if (x == 0 && y == 0) {
            return "src/images/WhiteRook.png";
        } else if (x == 1 && y == 0) {
            return "src/images/WhiteKnight.png";
        } else if (x == 2 && y == 0) {
            return "src/images/WhiteBishop.png";
        } else if (x == 3 && y == 0) {
            return "src/images/WhiteQueen.png";
        } else if (x == 4 && y == 0) {
            return "src/images/WhiteKing.png";
        } else if (x == 5 && y == 0) {
            return "src/images/WhiteBishop.png";
        } else if (x == 6 && y == 0) {
            return "src/images/WhiteKnight.png";
        } else if (x == 7 && y == 0) {
            return "src/images/WhiteRook.png";
        } else if (y == 1) {
            return "src/images/WhitePawn.png";
        }

        //black pieces
        else if (x == 0 && y == 7) {
            return "src/images/BlackRook.png";
        } else if (x == 1 && y == 7) {
            return "src/images/BlackKnight.png";
        } else if (x == 2 && y == 7) {
            return "src/images/BlackBishop.png";
        } else if (x == 3 && y == 7) {
            return "src/images/BlackQueen.png";
        } else if (x == 4 && y == 7) {
            return "src/images/BlackKing.png";
        } else if (x == 5 && y == 7) {
            return "src/images/BlackBishop.png";
        } else if (x == 6 && y == 7) {
            return "src/images/BlackKnight.png";
        } else if (x == 7 && y == 7) {
            return "src/images/BlackRook.png";
        } else if (y == 6) {
            return "src/images/BlackPawn.png";
        }

        //if the space is empty, return string empty and let initGui handle that case;
        return "empty";
    }

    public JComponent getChessBoard() {
        return chessBoard;
    }

    public JComponent getGui() {
        return gui;
    }

    //this will display the gui
    public static void runGui(Game game) {
        Runnable r = new Runnable() {
            public void run() {
                ChessBoard board = new ChessBoard(game);

                JFrame frame = new JFrame("Chess by Bryan Sharp");
                frame.add(board.getGui());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setMinimumSize(frame.getSize());
                frame.setVisible(true);
            }


        }; //runnable
        SwingUtilities.invokeLater(r);
    }

    //scans the board for the location of pieces and updates them accordingly
    public void scan(Game game) {
        Piece p;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.board[i][j].isOccupied()) {
                    p = game.board[i][j].piece;
                    if (p.type == Piece.Type.PAWN) {
                        Icon icon;
                        if (p.color == Piece.Color.WHITE) {
                            icon = new ImageIcon("src/images/WhitePawn.png");
                        } else {
                            icon = new ImageIcon("src/images/BlackPawn.png");
                        }
                        squares[i][j].setIcon(icon);
                    } else if (p.type == Piece.Type.ROOK) {
                        Icon icon;
                        if (p.color == Piece.Color.WHITE) {
                            icon = new ImageIcon("src/images/WhiteRook.png");
                        } else {
                            icon = new ImageIcon("src/images/BlackRook.png");
                        }
                        squares[i][j].setIcon(icon);
                    } else if (p.type == Piece.Type.BISHOP) {
                        Icon icon;
                        if (p.color == Piece.Color.WHITE) {
                            icon = new ImageIcon("src/images/WhiteBishop.png");
                        } else {
                            icon = new ImageIcon("src/images/BlackBishop.png");
                        }
                        squares[i][j].setIcon(icon);
                    } else if (p.type == Piece.Type.QUEEN) {
                        Icon icon;
                        if (p.color == Piece.Color.WHITE) {
                            icon = new ImageIcon("src/images/WhiteQueen.png");
                        } else {
                            icon = new ImageIcon("src/images/BlackQueen.png");
                        }
                        squares[i][j].setIcon(icon);
                    } else if (p.type == Piece.Type.KING) {
                        Icon icon;
                        if (p.color == Piece.Color.WHITE) {
                            icon = new ImageIcon("src/images/WhiteKing.png");
                        } else {
                            icon = new ImageIcon("src/images/BlackKing.png");
                        }
                        squares[i][j].setIcon(icon);
                    } else if (p.type == Piece.Type.KNIGHT) {
                        Icon icon;
                        if (p.color == Piece.Color.WHITE) {
                            icon = new ImageIcon("src/images/WhiteKnight.png");
                        } else {
                            icon = new ImageIcon("src/images/BlackKnight.png");
                        }
                        squares[i][j].setIcon(icon);
                    }
                } else {
                    squares[i][j].setIcon(null);
                }

            }
        }
    }


}
