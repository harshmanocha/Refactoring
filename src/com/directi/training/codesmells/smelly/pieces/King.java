package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.chess.Color;
import com.directi.training.codesmells.smelly.chess.Position;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return (Math.abs(from.getRow() - to.getRow()) == 1) && (Math.abs(from.getColumn() - to.getColumn()) == 1);
    }

    @Override
    public String toString() {
        return "K";
    }
}
