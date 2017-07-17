package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Direction;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.pieces.King;
import com.directi.training.codesmells.smelly.pieces.Knight;
import com.directi.training.codesmells.smelly.pieces.Pawn;
import com.directi.training.codesmells.smelly.pieces.Piece;

public class ChessBoard
{
    private final Cell[][] _board;
    public boolean _kingDead;
    public Player player1, player2;

    public ChessBoard()
    {
        _board = new Cell[8][8];
        initBoard();
    }

    private void initBoard()
    {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Color color = ((row + column) % 2 == 0) ? Color.WHITE : Color.BLACK;
                _board[row][column] = new Cell(color);
            }
        }
    }

    public Cell[][] getBoard()
    {
        return _board;
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

    public Piece getPiece(Position position)
    {
        return (isPositionOutOfBounds(position) || getCell(position).isEmpty())
                ? null
                : getCell(position).getPiece();
    }

    public String getPlayerName(Position position)
    {
        if (isPositionOutOfBounds(position))
            return null;
        Color color = getCell(position).getPiece().getColor();
        if (color == player1.getColor()) {
            return player1.getName();
        } else {
            return player2.getName();
        }
    }

    private void printMove(Position from, Position to)
    {
        System.out.println(getPlayerName(from) + " moved " + getPiece(from) + " from " + from + " to " + to);
        if (getPiece(from).getColor() != getPiece(to).getColor()) {
            System.out.println("And has captured " + getPiece(to) + " of " + getPlayerName(to));
        }
    }

    public boolean isValidMove(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);
        return !from.equals(to)
               && !(isPositionOutOfBounds(from) || isPositionOutOfBounds(to))
               && !isEmpty(from)
               && (isEmpty(to) || getPiece(from).getColor() != getPiece(to).getColor())
               && getPiece(from).isValidMove(from, to)
               && hasNoPieceInPath(from, to)
               && (!(getPiece(from) instanceof Pawn) || isValidPawnMove(from, to));
    }

    private boolean hasNoPieceInPath(Position from, Position to)
    {
        if (getPiece(from) instanceof Knight)
            return true;
        if (!isStraightLineMove(from, to))
            return false;
        Direction direction = new Direction(cappedCompare(to.getRow(), from.getRow()), cappedCompare(to.getColumn(), from.getColumn()));
        from = translatedPosition(from, direction);
        while (!from.equals(to)) {
            if (!isEmpty(from))
                return false;
            from = translatedPosition(from, direction);
        }
        return true;
    }

    private boolean isStraightLineMove(Position from, Position to)
    {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn())
                || from.getRow() == to.getRow()
                || from.getColumn() == to.getColumn();
    }

    private int cappedCompare(int x, int y)
    {
        return Math.max(-1, Math.min(1, Integer.compare(x, y)));
    }

    private Position translatedPosition(Position from, Direction direction)
    {
        return new Position(from.getRow() + direction.getRowOffset(), from.getColumn() + direction.getColumnOffset());
    }

    public void movePiece(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);
        updateIsKingDead(toRow, toColumn);
        if (!getCell(to).isEmpty())
            getCell(to).removePiece();
        getCell(to).setPiece(getPiece(from));
        getCell(from).removePiece();
    }

    private void updateIsKingDead(int row, int column)
    {
        if (getPiece(new Position(row, column)) instanceof King) {
            _kingDead = true;
        }
    }

    private boolean isValidPawnMove(Position from, Position to)
    {
        assert getPiece(from) instanceof Pawn;
        Pawn pawn = (Pawn)getPiece(from);
        Color pawnColor = pawn.getColor();
        int forwardRow = from.getRow() + ((pawnColor == Color.BLACK) ? 1 : -1);
        Position forwardLeft = new Position(forwardRow, from.getColumn() + (pawnColor == Color.WHITE ? -1 : 1));
        Position forwardRight = new Position(forwardRow, from.getColumn() + (pawnColor == Color.WHITE ? 1 : -1));

        boolean opponentPieceAtForwardLeft = !isEmpty(forwardLeft) && getPiece(forwardLeft).getColor() != pawnColor;
        boolean opponentPieceAtForwardRight = !isEmpty(forwardRight) && getPiece(forwardRight).getColor() != pawnColor;
        boolean atInitialPosition = from.getRow() == ((pawnColor == Color.BLACK) ? 1 : 6);

        return pawn.isValidMoveGivenContext(from, to, atInitialPosition, opponentPieceAtForwardLeft, opponentPieceAtForwardRight);
    }

    public boolean isKingDead()
    {
        return _kingDead;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (int column = 0; column < 8; column++) {
            stringBuilder.append("  ")
                    .append(column + 1)
                    .append("  ");
        }
        stringBuilder.append("\n");

        for (int row = 0; row < 8; row++) {
            stringBuilder.append(row + 1);
            for (int column = 0; column < 8; column++) {
                stringBuilder.append(" ")
                        .append(_board[row][column])
                        .append(" ");
            }
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
