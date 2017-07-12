package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Position;

// Code Smell solved: Duplicated code across classes
// (Queen class sharing code with Rook and Bishop,
// as well as code duplicated in ChessBoard for finding straight line move)
public class MoveUtil
{
    public static boolean isDiagonalMove(Position from, Position to)
    {
        return Math.abs(from.row - to.row) == Math.abs(from.column - to.column);
    }

    public static boolean isHorizontalOrVerticalMove(Position from, Position to)
    {
        return from.row == to.row || from.column == to.column;
    }

    public static boolean isStraightLineMove(Position from, Position to)
    {
        return isDiagonalMove(from, to) || isHorizontalOrVerticalMove(from, to);
    }
}
