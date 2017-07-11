package com.directi.training.codesmells.refactored.chess;

public enum Color
{
    WHITE("W"),
    BLACK("B");

    private String _string;

    Color(String string)
    {
        _string = string;
    }

    @Override
    public String toString()
    {
        return _string;
    }
}
