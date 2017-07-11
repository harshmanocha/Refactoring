package com.directi.training.codesmells.refactored.chess;

import com.sun.tools.javac.util.Pair;

public class Position
{
    private int _row, _column;

    public Position(int row, int column)
    {
        _row = row;
        _column = column;
    }

    public int getRow()
    {
        return _row;
    }

    public int getColumn()
    {
        return _column;
    }

    //Fixes another instance of Feature Envy Code Smell
    public Position translatedPosition(Pair<Integer, Integer> offset)
    {
        return new Position(getRow() + offset.fst, getColumn() + offset.snd);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Position))
            return false;
        Position otherPosition = (Position) obj;
        return this == obj || (_row == otherPosition.getRow() && _column == otherPosition.getColumn());
    }
}
