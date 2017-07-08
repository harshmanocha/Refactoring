package com.directi.training.codesmells.refactored.chess;

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
}
