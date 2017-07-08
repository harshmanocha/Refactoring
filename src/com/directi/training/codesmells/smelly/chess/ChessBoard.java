package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.pieces.*;

public class ChessBoard {
    private final Cell[][] _board;
    private boolean _isKingDead;

    public ChessBoard() {
        _board = new Cell[8][8];
        initBoard();
        resetBoard();
    }

    private void initBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Color color = ((row + column) % 2 == 0) ? Color.WHITE : Color.BLACK;
                _board[row][column] = new Cell(color);
            }
        }
    }

    public void resetBoard() {
        for (int column = 0; column < 8; column++) {
            if (column == 0 || column == 7) {
                _board[7][column].setPiece(new Rook(Color.WHITE));
            } else if (column == 1 || column == 6) {
                _board[7][column].setPiece(new Knight(Color.WHITE));
            } else if (column == 2 || column == 5) {
                _board[7][column].setPiece(new Bishop(Color.WHITE));
            } else if (column == 3) {
                _board[7][column].setPiece(new King(Color.WHITE));
            } else if (column == 4) {
                _board[7][column].setPiece(new Queen(Color.WHITE));
            }
        }
        for (int column = 0; column < 8; column++)
            _board[6][column].setPiece(new Pawn(Color.WHITE));

        for (int column = 0; column < 8; column++) {
            if (column == 0 || column == 7) {
                _board[0][column].setPiece(new Rook(Color.BLACK));
            } else if (column == 1 || column == 6) {
                _board[0][column].setPiece(new Knight(Color.BLACK));
            } else if (column == 2 || column == 5) {
                _board[0][column].setPiece(new Bishop(Color.BLACK));
            } else if (column == 3) {
                _board[0][column].setPiece(new King(Color.BLACK));
            } else if (column == 4) {
                _board[0][column].setPiece(new Queen(Color.BLACK));
            }
        }
        for (int column = 0; column < 8; column++)
            _board[1][column].setPiece(new Pawn(Color.BLACK));

        _isKingDead = false;
    }

    public void printBoard() {
        System.out.println(this.toString());
    }

    public boolean isEmpty(Position position) {
        return getCell(position).isEmpty();
    }

    private Cell getCell(Position position) {
        return _board[position.getRow()][position.getColumn()];
    }

    public Piece getPiece(Position position) {
        Cell cell = getCell(position);
        return cell.isEmpty() ? null : cell.getPiece();
    }

    public boolean isValidMove(int fromRow, int fromColumn, int toRow, int toColumn) {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);
        return !isEmpty(from)
                && getPiece(from).getColor() != getPiece(to).getColor()
                && getPiece(from).isValidMove(from, to);
    }

    public boolean movePiece(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (!isValidMove(fromRow, fromColumn, toRow, toColumn)) {
            System.out.println("Invalid Move!");
            return false;
        }
        updateIsKingDead(toRow, toColumn);
        updatePawnStatus(toRow, toColumn);
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);
        if (!getCell(to).isEmpty())
            getCell(to).removePiece();
        getCell(to).setPiece(getPiece(from));
        getCell(from).removePiece();
        return true;
    }

    private void updateIsKingDead(int row, int column) {
        if (getPiece(new Position(row, column)).getClass() == King.class) {
            _isKingDead = true;
        }
    }

    private void updatePawnStatus(int row, int column) {
        if (getPiece(new Position(row, column)).getClass() == Pawn.class) {
            Pawn pawn = (Pawn)getPiece(new Position(row, column));
            int forwardRow = row + ((pawn.getColor() == Color.BLACK) ? 1 : -1);
            Position forwardLeft = new Position(forwardRow, column - 1);
            Position forwardRight = new Position(forwardRow, column + 1);
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
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                stringBuilder.append(" ")
                             .append(_board[row][column])
                             .append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
