package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.pieces.Piece;

public class Cell {
    private Piece _piece;
    private Color _color;

    public Cell(Color color) {
        _color = color;
    }

    public void setPiece(Piece piece) {
        _piece = piece;
    }

    public void removePiece() {
        _piece = null;
    }

    public Piece getPiece() {
        return _piece;
    }

    public boolean isEmpty() {
        return _piece == null;
    }

    @Override
    public String toString() {
        String cellColor = _color == Color.BLACK ? "B" : "W";
        return _piece == null
                ? ("." + cellColor + ".")
                : (_piece.getColor().toString() + _piece.toString() + cellColor);
    }
}
