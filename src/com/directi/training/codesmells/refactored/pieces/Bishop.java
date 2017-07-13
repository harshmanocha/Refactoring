package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.chess.MoveUtil;
import com.directi.training.codesmells.refactored.Position;

//Fixed Collapsing Hierarchy (another instance of lazy-class)
public class Bishop extends Piece
{

    public Bishop(Color color)
    {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        return MoveUtil.isDiagonalMove(from, to);
    }

    @Override
    public String toString()
    {
        return "b";
    }
}
