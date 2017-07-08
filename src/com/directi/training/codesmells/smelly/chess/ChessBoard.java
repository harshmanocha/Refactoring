package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.pieces.*;

public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private final Cell[][] _board;
    private boolean _isKingDead;

    public ChessBoard() {
        _board = new Cell[BOARD_SIZE][BOARD_SIZE];
        initBoard();
        resetBoard();
    }

    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Color color = ((row + column) % 2 == 0) ? Color.WHITE : Color.BLACK;
                _board[row][column] = new Cell(color);
            }
        }
    }

    public void resetBoard() {
        placePieces(Color.WHITE);
        placePieces(Color.BLACK);
        _isKingDead = false;
    }

    private void placePieces(Color color) {
        int pawnsRow, otherPiecesRow;
        switch (color) {
            case WHITE:
                pawnsRow = BOARD_SIZE - 2;
                otherPiecesRow = BOARD_SIZE - 1;
                break;
            case BLACK:
                pawnsRow = 1;
                otherPiecesRow = 0;
                break;
            default:
                System.err.println("Unexpected color passed to placePieces.");
                return;
        }
        placeOtherPieces(otherPiecesRow, color);
        placePawns(pawnsRow, color);
    }

    private void placePawns(int row, Color color) {
        for (int i = 0; i < BOARD_SIZE; i++)
            _board[row][i].setPiece(new Pawn(color));
    }

    private void placeOtherPieces(int row, Color color) {
        assert BOARD_SIZE == 8;
        for (int column = 0; column < BOARD_SIZE; column++) {
            Piece piece = null;
            if (column == 0 || column == BOARD_SIZE - 1) {
                piece = new Rook(color);
            } else if (column == 1 || column == BOARD_SIZE -2) {
                piece = new Knight(color);
            } else if (column == 2 || column == BOARD_SIZE - 3) {
                piece = new Bishop(color);
            } else if (column == 3) {
                piece = new King(color);
            } else if (column == 4) {
                piece = new Queen(color);
            }
            _board[row][column].setPiece(piece);
        }
    }

    public void printBoard() {
        System.out.println(this.toString());
    }

    public boolean isEmpty(Position position) {
        return _board[position.getRow()][position.getColumn()].isEmpty();
    }

    private Cell getCell(Position position) {
        return _board[position.getRow()][position.getColumn()];
    }

    public Piece getPiece(Position position) {
        Cell cell = getCell(position);
        return cell.isEmpty() ? null : cell.getPiece();
    }

    public boolean isValidMove(Position from, Position to) {
        return !isEmpty(from) && getPiece(from).getColor() != getPiece(to).getColor();
    }

    public boolean movePiece(Position from, Position to) {
        Piece startPiece = getCell(from).getPiece();
        if (!isValidMove(from, to) || !startPiece.isValidMove(from, to)) {
            System.out.println("Invalid Move!");
            return false;
        }
        updateIsKingDead(to);
        updatePawnStatus(to);
        if (!getCell(to).isEmpty())
            getCell(to).removePiece();
        getCell(to).setPiece(startPiece);
        getCell(from).removePiece();
        return true;
    }

    private void updateIsKingDead(Position positionBeingMovedTo) {
        if (getPiece(positionBeingMovedTo).getClass() == King.class) {
            _isKingDead = true;
        }
    }

    private void updatePawnStatus(Position position) {
        if (getPiece(position).getClass() == Pawn.class) {
            Pawn pawn = (Pawn)getPiece(position);
            int forwardRow = position.getRow()+ ((pawn.getColor() == Color.BLACK) ? 1 : -1);
            Position forwardLeft = new Position(forwardRow, position.getColumn() - 1);
            Position forwardRight = new Position(forwardRow, position.getColumn() + 1);
            if ((!getCell(forwardLeft).isEmpty() && getPiece(forwardLeft).getColor() != pawn.getColor())
                    || (!getCell(forwardRight).isEmpty() && getPiece(forwardRight).getColor() != pawn.getColor())) {
                pawn.setHasOpponentPieceAtForwardDiagonal(true);
            } else {
                pawn.setHasOpponentPieceAtForwardDiagonal(false);
            }
        }
    }

    public boolean isKingDead() {
        return _isKingDead;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                stringBuilder.append(" ")
                             .append(_board[row][column])
                             .append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
