package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;

//Fixed Collapsing Hierarchy (another instance of lazy-class)
public class Knight extends Piece
{
    public Knight(Color color)
    {
        super(color);
    }

    public boolean isValidMove(Position from, Position to)
    {
        int columnDiff = Math.abs(to.column - from.column);
        int rowDiff = Math.abs(to.row - from.row);
        return (columnDiff == 2 && rowDiff == 1) || (columnDiff == 1 && rowDiff == 2);
    }

    @Override
    public String toString()
    {
        return "k";
    }
}
