package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;

public abstract class Piece
{
    private Color _color; //Fixed Indecent Exposure Code Smell by making field private

    public Piece(Color color)
    {
        _color = color;
    }

    public Color getColor()
    {
        return _color;
    }

    //Fixed Switch-case code smell through polymorphism. Note that type field has also been removed.
    public abstract boolean isValidMove(Position from, Position to);
}
