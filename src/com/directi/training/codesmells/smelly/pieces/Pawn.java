package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.chess.Color;
import com.directi.training.codesmells.smelly.chess.Position;

public class Pawn extends Piece {
    private boolean _isAtInitialPosition;
    private boolean _hasOpponentPieceAtForwardLeft;
    private boolean _hasOpponentPieceAtForwardRight;

    public Pawn(Color color) {
        super(color, 'p');
        _isAtInitialPosition = true;
    }

    public void setHasOpponentPieceAtForwardLeft(boolean hasOpponentPieceAtForwardLeft) {
        _hasOpponentPieceAtForwardLeft = hasOpponentPieceAtForwardLeft;
    }

    public void setHasOpponentPieceAtForwardRight(boolean hasOpponentPieceAtForwardRight) {
        _hasOpponentPieceAtForwardRight = hasOpponentPieceAtForwardRight;
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        boolean isValidMove =
                isForwardMove(from, to)
                && isTakingAllowedNumberOfForwardSteps(from, to)
                && isTakingAllowedNumberOfSidewaysSteps(from, to);

        if (isValidMove) {
            _isAtInitialPosition = false;
        }
        return isValidMove;
    }

    private boolean isForwardMove(Position from, Position to) {
        switch (getColor()) {
            case WHITE:
                return to.getRow() < from.getRow();
            case BLACK:
                return to.getRow() > from.getRow();
            default:
                return false;
        }
    }

    private boolean isTakingAllowedNumberOfForwardSteps(Position from, Position to) {
        int rowsAbsDiff = Math.abs(to.getRow() - from.getRow());
        return rowsAbsDiff > 0 && (rowsAbsDiff <= (_isAtInitialPosition ? 2 : 1));
    }

    private boolean isTakingAllowedNumberOfSidewaysSteps(Position from, Position to) {
        int columnsDiff = to.getColumn() - from.getColumn();
        if (columnsDiff == -1)
            return (_hasOpponentPieceAtForwardLeft && getColor() == Color.WHITE)
                    || (_hasOpponentPieceAtForwardRight && getColor() == Color.BLACK);
        if (columnsDiff == 1) {
            return (_hasOpponentPieceAtForwardRight && getColor() == Color.WHITE)
                    || (_hasOpponentPieceAtForwardLeft && getColor() == Color.BLACK);
        }
        return columnsDiff == 0;
    }

    @Override
    public String toString() {
        return "p";
    }
}
