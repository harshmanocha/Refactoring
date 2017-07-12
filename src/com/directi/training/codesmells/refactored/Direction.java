package com.directi.training.codesmells.refactored;

public class Direction
{
    public final int rowOffset;
    public final int columnOffset;

    public Direction(int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
    }
}
