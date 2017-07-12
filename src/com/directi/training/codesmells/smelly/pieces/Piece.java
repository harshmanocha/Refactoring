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
                return Math.abs(from.row - to.row) == Math.abs(from.column - to.column);
            case 'r':
                return from.row == to.row || from.column == to.column;
            case 'k':
                int columnDiff = Math.abs(to.column - from.column);
                int rowDiff = Math.abs(to.row - from.row);
                return (columnDiff == 2 && rowDiff == 1) || (columnDiff == 1 && rowDiff == 2);
            case 'q':
                return Math.abs(from.row - to.row) == Math.abs(from.column - to.column)
                        || from.row == to.row || from.column == to.column;
            case 'K':
                return (Math.abs(from.row - to.row) == 1) && (Math.abs(from.column - to.column) == 1);
            default:
                return false;
        }
    }
}
