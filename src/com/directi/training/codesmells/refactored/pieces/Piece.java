package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.chess.Color;
import com.directi.training.codesmells.refactored.chess.Position;

public abstract class Piece {
    private Color _color;

    public Piece(Color color) {
        _color = color;
    }

    public Color getColor() {
        return _color;
    }

    public abstract boolean isValidMove(Position from, Position to);
}
