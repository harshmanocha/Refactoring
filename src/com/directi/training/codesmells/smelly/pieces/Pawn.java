package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.chess.Color;
import com.directi.training.codesmells.smelly.chess.Position;

public class Pawn extends Piece {
    private boolean _isAtInitialPosition;
    private boolean _hasOpponentPieceAtForwardDiagonal;

    public Pawn(Color color) {
        super(color, 'p');
        _isAtInitialPosition = true;
    }

    public void setHasOpponentPieceAtForwardDiagonal(boolean hasOpponentPieceAtForwardDiagonal) {
        _hasOpponentPieceAtForwardDiagonal = hasOpponentPieceAtForwardDiagonal;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        boolean isForwardMove = false;
        switch (getColor()) {
            case WHITE:
                isForwardMove = to.getRow() < from.getRow();
                break;
            case  BLACK:
                isForwardMove = to.getRow() > from.getRow();
                break;
        }
        boolean isValidMove = (isForwardMove
                && Math.abs(to.getColumn() - from.getColumn()) <= (_hasOpponentPieceAtForwardDiagonal ? 1 : 0)
                && Math.abs(to.getRow() - from.getRow()) <= (_isAtInitialPosition ? 2 : 1));

        if (isValidMove) {
            _isAtInitialPosition = false;
        }
        return isValidMove;
    }

    @Override
    public String toString() {
        return "p";
    }
}
