package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;

public class Pawn extends Piece
{
    public Pawn(Color color)
    {
        super(color, 'p');
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        int columnsMoved = Math.abs(to.column - from.column);
        int rowsMoved = Math.abs(to.row - from.row);
        return isForwardMove(from, to)
               && ((columnsMoved <= 1 && rowsMoved == 1) || (columnsMoved == 0 && rowsMoved == 2));
    }

    private boolean isForwardMove(Position from, Position to)
    {
        switch (getColor()) {
            case WHITE:
                return to.row < from.row;
            case BLACK:
                return to.row > from.row;
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
        int rowsAbsDiff = Math.abs(to.row - from.row);
        return rowsAbsDiff > 0 && (rowsAbsDiff <= (atInitialPosition ? 2 : 1));
    }

    private boolean isTakingAllowedNumberOfSidewaysSteps(Position from,
                                                         Position to,
                                                         boolean opponentPieceAtForwardLeft,
                                                         boolean opponentPieceAtForwardRight)
    {
        int columnsDiff = to.column - from.column;
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
