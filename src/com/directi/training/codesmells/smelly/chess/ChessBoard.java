package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.pieces.*;

public class ChessBoard {
    private final Cell[][] _board;
    public boolean _isKingDead;
    public Player player1, player2;

    public ChessBoard() {
        _board = new Cell[8][8];
        initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Color color = ((row + column) % 2 == 0) ? Color.WHITE : Color.BLACK;
                _board[row][column] = new Cell(color);
            }
        }
    }

    public Cell[][] getBoard() {
        return _board;
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

    public String getPlayerName(Position position) {
        Color color = getCell(position).getPiece().getColor();
        if (color == player1.getCurrentColor()) {
            return player1.getName();
        } else {
            return player2.getName();
        }
    }

    private void printMove(Position from, Position to) {
        System.out.println(getPlayerName(from) + " moved " + getPiece(from) + " from " + from + " to " + to);
        if (getPiece(from).getColor() != getPiece(to).getColor()) {
            System.out.println("And has captured " + getPiece(to) + " of " + getPlayerName(to));
        }
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
        if (getPiece(new Position(row, column)) instanceof King) {
            _isKingDead = true;
        }
    }

    private void updatePawnStatus(int row, int column) {
        if (getPiece(new Position(row, column)) instanceof Pawn) {
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
