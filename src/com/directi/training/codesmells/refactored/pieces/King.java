package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;

public class King extends Piece
{
    public King(Color color)
    {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        return (Math.abs(from.row - to.row) == 1) && (Math.abs(from.column - to.column) == 1);
    }

    @Override
    public String toString()
    {
        return "K";
    }
}
