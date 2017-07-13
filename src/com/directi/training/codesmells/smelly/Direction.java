package com.directi.training.codesmells.smelly;

public class Direction
{
    private final int _rowOffset;
    private final int _columnOffset;

    public Direction(int rowOffset, int columnOffset) {
        _rowOffset = rowOffset;
        _columnOffset = columnOffset;
    }

    public int getRowOffset()
    {
        return _rowOffset;
    }

    public int getColumnOffset()
    {
        return _columnOffset;
    }
}
