package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.chess.Color;
import com.directi.training.codesmells.refactored.chess.Position;

public class Pawn extends Piece
{
    public Pawn(Color color)
    {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        return isForwardMove(from, to)
               && (Math.abs(to.getColumn() - from.getColumn()) <= 1 ||
                Math.abs(to.getRow() - from.getRow()) <= 2);
    }

    private boolean isForwardMove(Position from, Position to)
    {
        switch (getColor()) {
            case WHITE:
                return to.getRow() < from.getRow();
            case BLACK:
                return to.getRow() > from.getRow();
            default:
                return false;
        }
    }

    public boolean isValidMoveGivenContext(Position from,
                                           Position to,
                                           boolean atInitialPosition,
                                           boolean opponentPieceAtForwardLeft,
                                           boolean opponentPieceAtForwardRight)
    {
        return isForwardMove(from, to)
               && isTakingAllowedNumberOfForwardSteps(from, to, atInitialPosition)
               && isTakingAllowedNumberOfSidewaysSteps(from, to, opponentPieceAtForwardLeft, opponentPieceAtForwardRight);
    }

    private boolean isTakingAllowedNumberOfForwardSteps(Position from, Position to, boolean atInitialPosition)
    {
        int rowsAbsDiff = Math.abs(to.getRow() - from.getRow());
        return rowsAbsDiff > 0 && (rowsAbsDiff <= (atInitialPosition ? 2 : 1));
    }

    private boolean isTakingAllowedNumberOfSidewaysSteps(Position from,
                                                         Position to,
                                                         boolean opponentPieceAtForwardLeft,
                                                         boolean opponentPieceAtForwardRight)
    {
        int columnsDiff = to.getColumn() - from.getColumn();
        if (columnsDiff == -1)
            return (opponentPieceAtForwardLeft && getColor() == Color.WHITE)
                    || (opponentPieceAtForwardRight && getColor() == Color.BLACK);
        if (columnsDiff == 1) {
            return (opponentPieceAtForwardRight && getColor() == Color.WHITE)
                    || (opponentPieceAtForwardLeft && getColor() == Color.BLACK);
        }
        return columnsDiff == 0;
    }

    @Override
    public String toString()
    {
        return "p";
    }
}
