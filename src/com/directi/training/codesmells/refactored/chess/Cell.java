package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.pieces.Piece;

public class Cell
{
    private Piece _piece;
    private Color _color;

    public Cell(Color color)
    {
        _color = color;
    }

    public void removePiece()
    {
        _piece = null;
    }

    public Piece getPiece()
    {
        return _piece;
    }

    public void setPiece(Piece piece)
    {
        _piece = piece;
    }

    public boolean isEmpty()
    {
        return _piece == null;
    }

    @Override
    public String toString()
    {
        return _piece == null
                ? ("." + _color + ".")
                : (_piece.getColor().toString() + _color + _piece.toString());
    }
}
