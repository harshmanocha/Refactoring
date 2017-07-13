package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;

public abstract class Piece
{
    public Color _color;
    public char _type;

    public Piece(Color color, char type)
    {
        _color = color;
        _type = type;
    }

    public Color getColor()
    {
        return _color;
    }

    public boolean isValidMove(Position from, Position to)
    {
        switch (_type) {
            case 'b':
                return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
            case 'r':
                return from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
            case 'k':
                int columnDiff = Math.abs(to.getColumn() - from.getColumn());
                int rowDiff = Math.abs(to.getRow() - from.getRow());
                return (columnDiff == 2 && rowDiff == 1) || (columnDiff == 1 && rowDiff == 2);
            case 'q':
                return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn())
                        || from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
            case 'K':
                return (Math.abs(from.getRow() - to.getRow()) == 1) && (Math.abs(from.getColumn() - to.getColumn()) == 1);
            default:
                return false;
        }
    }
}
