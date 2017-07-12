package com.directi.training.codesmells.smelly;

public class Position
{
    public final int row, column;

    public Position(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString()
    {
        return "(ROW: " + row + ", COLUMN: " + column + ")";
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
