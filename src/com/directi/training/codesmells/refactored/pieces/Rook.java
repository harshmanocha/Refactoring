package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.chess.Color;
import com.directi.training.codesmells.refactored.chess.Position;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    public boolean isValidMove(Position from, Position to) {
        return Move.isHorizontalOrVerticalMove(from, to);
    }

    @Override
    public String toString() {
        return "r";
    }
}
