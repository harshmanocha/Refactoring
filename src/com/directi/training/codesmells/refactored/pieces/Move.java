package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.chess.Position;

// Code Smell solved: Duplicated code across classes
public class Move {
    public static boolean isDiagonalMove(Position from, Position to) {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
    }

    public  static boolean isHorizontalOrVerticalMove(Position from, Position to) {
        return from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
    }

    public static boolean isStraightLineMove(Position from, Position to) {
        return isDiagonalMove(from, to) || isHorizontalOrVerticalMove(from, to);
    }
}
