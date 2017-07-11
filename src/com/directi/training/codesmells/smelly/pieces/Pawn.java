package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.chess.Color;
import com.directi.training.codesmells.smelly.chess.Position;

public class Pawn extends Piece
{
    private boolean _atInitialPosition;
    private boolean _opponentPieceAtForwardLeft;
    private boolean _opponentPieceAtForwardRight;

    public Pawn(Color color)
    {
        super(color, 'p');
        _atInitialPosition = true;
    }

    public void setOpponentPieceAtForwardLeft(boolean opponentPieceAtForwardLeft)
    {
        _opponentPieceAtForwardLeft = opponentPieceAtForwardLeft;
    }

    public void setOpponentPieceAtForwardRight(boolean opponentPieceAtForwardRight)
    {
        _opponentPieceAtForwardRight = opponentPieceAtForwardRight;
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        boolean isValidMove =
                isForwardMove(from, to)
                        && isTakingAllowedNumberOfForwardSteps(from, to)
                        && isTakingAllowedNumberOfSidewaysSteps(from, to);

        if (isValidMove) {
            _atInitialPosition = false;
        }
        return isValidMove;
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

    private boolean isTakingAllowedNumberOfForwardSteps(Position from, Position to)
    {
        int rowsAbsDiff = Math.abs(to.getRow() - from.getRow());
        return rowsAbsDiff > 0 && (rowsAbsDiff <= (_atInitialPosition ? 2 : 1));
    }

    private boolean isTakingAllowedNumberOfSidewaysSteps(Position from, Position to)
    {
        int columnsDiff = to.getColumn() - from.getColumn();
        if (columnsDiff == -1)
            return (_opponentPieceAtForwardLeft && getColor() == Color.WHITE)
                    || (_opponentPieceAtForwardRight && getColor() == Color.BLACK);
        if (columnsDiff == 1) {
            return (_opponentPieceAtForwardRight && getColor() == Color.WHITE)
                    || (_opponentPieceAtForwardLeft && getColor() == Color.BLACK);
        }
        return columnsDiff == 0;
    }

    @Override
    public String toString()
    {
        return "p";
    }
}
