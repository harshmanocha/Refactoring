package com.directi.training.codesmells.smelly;

public enum Color
{
    WHITE("W"),
    BLACK("B");

    private String _colorString;

    Color(String colorString)
    {
        _colorString = colorString;
    }

    @Override
    public String toString()
    {
        return _colorString;
    }
}
