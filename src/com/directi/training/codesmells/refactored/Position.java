package com.directi.training.codesmells.refactored;


public class Position
{
    public final int row, column;

    public Position(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    //Fixes another instance of Feature Envy Code Smell
    public Position translatedPosition(Direction direction)
    {
        return new Position(row + direction.rowOffset, column + direction.columnOffset);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Position))
            return false;
        Position otherPosition = (Position) obj;
        return this == obj || (this.row == otherPosition.row && this.column == otherPosition.column);
    }
}
