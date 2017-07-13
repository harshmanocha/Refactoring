package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Position;

// Code Smell solved: Duplicated code across classes
// (Queen class sharing code with Rook and Bishop,
// as well as code duplicated in ChessBoard for finding straight line move)
public class MoveUtil
{
    public static boolean isDiagonalMove(Position from, Position to)
    {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
    }

    public static boolean isHorizontalOrVerticalMove(Position from, Position to)
    {
        return from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
    }

    public static boolean isStraightLineMove(Position from, Position to)
    {
        return isDiagonalMove(from, to) || isHorizontalOrVerticalMove(from, to);
    }
}
