package com.directi.training.codesmells.smelly.chess;

public class Position {
    private int _row, _column;

    public Position(int row, int column) {
        _row = row;
        _column = column;
    }

    public int getRow() {
        return _row;
    }

    public int getColumn() {
        return _column;
    }

    @Override
    public String toString() {
        return "(ROW: " + _row + ", COLUMN: " + _column + ")";
    }
}
