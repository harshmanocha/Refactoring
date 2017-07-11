package com.directi.training.codesmells.smelly.chess;

public class Player
{
    private String _name;
    private int _gamesWon;
    private Color _currentColor;

    public Player(String name)
    {
        _name = name;
        _gamesWon = 0;
    }

    public String getName()
    {
        return _name;
    }

    public void increase()
    {
        _gamesWon++;
    }

    public Color getCurrentColor()
    {
        return _currentColor;
    }

    public void setCurrentColor(Color currentColor)
    {
        _currentColor = currentColor;
    }

    @Override
    public String toString()
    {
        return "NAME: " + _name + "; GAMES WON: " + _gamesWon;
    }
}
