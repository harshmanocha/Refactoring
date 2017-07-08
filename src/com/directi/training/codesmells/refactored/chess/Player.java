package com.directi.training.codesmells.refactored.chess;

public class Player {
    private String _name;
    private int _gamesWon;
    private Color _currentColor;

    public Player(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void incrementGamesWon() {
        _gamesWon++;
    }

    public Color getCurrentColor() {
        return _currentColor;
    }

    public void setCurrentColor(Color currentColor) { _currentColor = currentColor; }

    @Override
    public String toString() {
        return "NAME: " + _name + "; GAMES WON: " + _gamesWon;
    }
}
