package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.pieces.*;
import com.sun.tools.javac.util.Pair;

public class ChessBoard
{
    // Fixes magic number code smell. Although this const is not supposed to be changed, but this avoids a typo
    private static final int BOARD_SIZE = 8;
    private final Cell[][] _board;
    private boolean _kingDead; //On fixing feature envy code smell, this should be made private and getBoard method be removed

    public ChessBoard()
    {
        _board = new Cell[BOARD_SIZE][BOARD_SIZE];
        initBoard();
        resetBoard(); // Added later
    }

    private void initBoard()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Color color = ((row + column) % 2 == 0) ? Color.WHITE : Color.BLACK;
                _board[row][column] = new Cell(color);
            }
        }
    }

    //Code smells solved: Duplicate code and long method.
    public void resetBoard()
    {
        placePieces(Color.WHITE);
        placePieces(Color.BLACK);
        _kingDead = false;
    }

    private void placePieces(Color color)
    {
        int pawnsRow, otherPiecesRow;
        switch (color) {
            case WHITE:
                pawnsRow = BOARD_SIZE - 2;
                otherPiecesRow = BOARD_SIZE - 1;
                break;
            case BLACK:
                pawnsRow = 1;
                otherPiecesRow = 0;
                break;
            default:
                System.err.println("Unexpected color passed to placePieces.");
                return;
        }
        placeOtherPieces(otherPiecesRow, color);
        placePawns(pawnsRow, color);
    }

    private void placePawns(int row, Color color)
    {
        for (int column = 0; column < BOARD_SIZE; column++)
            _board[row][column].setPiece(new Pawn(color));
    }

    private void placeOtherPieces(int row, Color color)
    {
        assert BOARD_SIZE == 8;
        for (int column = 0; column < BOARD_SIZE; column++) {
            Piece piece = null;
            if (column == 0 || column == BOARD_SIZE - 1) {
                piece = new Rook(color);
            } else if (column == 1 || column == BOARD_SIZE - 2) {
                piece = new Knight(color);
            } else if (column == 2 || column == BOARD_SIZE - 3) {
                piece = new Bishop(color);
            } else if (column == 3) {
                piece = new King(color);
            } else if (column == 4) {
                piece = new Queen(color);
            }
            _board[row][column].setPiece(piece);
        }
    }

    private boolean isPositionOutOfBounds(Position position)
    {
        return (position.getRow() < 0
                || position.getRow() >= 8
                || position.getColumn() < 0
                || position.getColumn() >= 8);
    }

    public boolean isEmpty(Position position)
    {
        return isPositionOutOfBounds(position) || getCell(position).isEmpty();
    }

    private Cell getCell(Position position)
    {
        return _board[position.getRow()][position.getColumn()];
    }

    //Dead-Code Code Smell fixed by removing getPlayerName and printMove methods (and also toString of Position),
    // as well as player 1 and player 2 fields.

    public Piece getPiece(Position position)
    {
        return (isPositionOutOfBounds(position) || getCell(position).isEmpty())
                ? null
                : getCell(position).getPiece();
    }

    //Fixed long parameter list code smell: Pass the object itself instead of passing its data.
    // (isValidMove, movePiece, updateIsKingDead, updatePawnStatus)
    public boolean isValidMove(Position from, Position to)
    {
        return !(isPositionOutOfBounds(from) || isPositionOutOfBounds(to))
                && !isEmpty(from)
                && (isEmpty(to) || getPiece(from).getColor() != getPiece(to).getColor())
                && getPiece(from).isValidMove(from, to)
                && hasNoPieceInPath(from, to);
    }

    //Fixed another instance of long method (also an example of switch-case)
    // by extracting out code for knight in a separate method.
    private boolean hasNoPieceInPath(Position from, Position to)
    {
        if (getPiece(from) instanceof Knight)
            return hasNoPieceInPathOfKnight(from, to);
        if (!MoveUtil.isStraightLineMove(from, to))
            return false;
        Pair<Integer, Integer> direction = new Pair<>(cappedCompare(to.getRow(), from.getRow()), cappedCompare(to.getColumn(), from.getColumn()));
        from = from.translatedPosition(direction);
        while (!from.equals(to)) {
            if (!isEmpty(from))
                return false;
            from = from.translatedPosition(direction);
        }
        return true;
    }

    private int cappedCompare(int x, int y)
    {
        return Math.max(-1, Math.min(1, Integer.compare(x, y)));
    }

    private boolean hasNoPieceInPathOfKnight(Position from, Position to)
    {
        int columnDiff = Math.abs(to.getColumn() - from.getColumn());
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        Pair<Integer, Integer> jumpDirection;
        if (columnDiff == 2 && rowDiff == 1)
            jumpDirection = new Pair<>(0, cappedCompare(to.getColumn(), from.getColumn()));
        else if (rowDiff == 2 && columnDiff == 1)
            jumpDirection = new Pair<>(cappedCompare(to.getRow(), from.getRow()), 0);
        else
            return false;
        Position firstStepPosition = from.translatedPosition(jumpDirection);
        return isEmpty(firstStepPosition) || isEmpty(firstStepPosition.translatedPosition(jumpDirection));
    }

    public void movePiece(Position from, Position to)
    {
        updateIsKingDead(to);
        updatePawnStatus(to);
        if (!getCell(to).isEmpty())
            getCell(to).removePiece();
        getCell(to).setPiece(getPiece(from));
        getCell(from).removePiece();
    }

    private void updateIsKingDead(Position positionBeingMovedTo)
    {
        if (getPiece(positionBeingMovedTo) instanceof King) {
            _kingDead = true;
        }
    }

    private void updatePawnStatus(Position position)
    {
        if (getPiece(position) instanceof Pawn) {
            Pawn pawn = (Pawn) getPiece(position);
            Color pawnColor = pawn.getColor();
            int forwardRow = position.getRow() + ((pawnColor == Color.BLACK) ? 1 : -1);
            Position forwardLeft = new Position(forwardRow, position.getColumn() + (pawnColor == Color.WHITE ? -1 : 1));
            Position forwardRight = new Position(forwardRow, position.getColumn() + (pawnColor == Color.WHITE ? 1 : -1));

            pawn.setHasOpponentPieceAtForwardLeft((!isEmpty(forwardLeft) && getPiece(forwardLeft).getColor() != pawnColor));
            pawn.setHasOpponentPieceAtForwardRight((!isEmpty(forwardRight) && getPiece(forwardRight).getColor() != pawnColor));
        }
    }

    public boolean getKingDead()
    {
        return _kingDead;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (int column = 0; column < BOARD_SIZE; column++) {
            stringBuilder.append("  ")
                    .append(column + 1)
                    .append("  ");
        }
        stringBuilder.append("\n");

        for (int row = 0; row < BOARD_SIZE; row++) {
            stringBuilder.append(row + 1);
            for (int column = 0; column < BOARD_SIZE; column++) {
                stringBuilder.append(" ")
                        .append(_board[row][column])
                        .append(" ");
            }
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
