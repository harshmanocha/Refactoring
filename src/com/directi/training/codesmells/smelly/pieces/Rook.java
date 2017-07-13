package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;

public abstract class Rook extends Piece
{
    public Rook(Color color)
    {
        super(color, 'r');
    }

    @Override
    public String toString()
    {
        return "r";
    }
}
