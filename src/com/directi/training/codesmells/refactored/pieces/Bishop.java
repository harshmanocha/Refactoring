package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.chess.Position;
import com.directi.training.codesmells.refactored.chess.Color;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return Move.isDiagonalMove(from, to);
    }

    @Override
    public String toString() {
        return "b";
    }
}
