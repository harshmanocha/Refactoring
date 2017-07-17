package com.directi.trainining.codesmells;

import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.chess.ChessBoard;
import com.directi.training.codesmells.smelly.chess.GameEngine;
import com.directi.training.codesmells.smelly.chess.Move;
import com.directi.training.codesmells.smelly.chess.Player;
import com.directi.training.codesmells.smelly.pieces.Knight;
import com.directi.training.codesmells.smelly.pieces.Pawn;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class ChessGameTest
{

    private GameEngine _gameEngine;

    private boolean isValidMoveHelper(Position from, Position to) {
        return _gameEngine.isValidMove(new Move(from, to));
    }
    
    private void makeMoveHelper(Position from, Position to) {
        _gameEngine.makeMove(new Move(from, to));
    }

    //DO NOT CHANGE ANY STATEMENT FROM CODE BELOW

    @Before
    public void initGame()
    {
        Player player1 = new Player("A");
        Player player2 = new Player("B");
        _gameEngine = new GameEngine(player1, player2);
        _gameEngine.initGame();
    }

    @Test
    public void testNoMovementOfPieceForFirstPlayer()
    {
        Position fromPosition = new Position(6, 0);
        Position toPosition = new Position(6, 0);
        assertFalse(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testNoMovementOfPieceForSecondPlayer()
    {
        Position fromPosition = new Position(1, 0);
        Position toPosition = new Position(1, 0);
        assertFalse(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testIsValidMoveOfPawnForFirstPlayer()
    {
        Position fromPosition = new Position(6, 1);
        Position toPosition = new Position(5, 1);
        assertTrue(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testMovementOfPawnForFirstPlayer()
    {
        Position fromPosition = new Position(6, 6);
        Position toPosition = new Position(4, 6);
        makeMoveHelper(fromPosition, toPosition);
        ChessBoard chessBoard = _gameEngine.getChessBoard();
        assertTrue(chessBoard.getPiece(new Position(6, 6)) == null);
        assertTrue(chessBoard.getPiece(new Position(4, 6)) instanceof Pawn);
    }

    @Test
    public void testNotAllowsSecondPlayerWhenNotInTurn()
    {
        Position fromPosition = new Position(0, 1);
        Position toPosition = new Position(2, 0);
        assertFalse(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testIsValidMoveOfKnightForSecondPlayer()
    {
        _gameEngine.makeMove(new Move(new Position(6, 1), new Position(5, 1)));
        Position fromPosition = new Position(0, 1);
        Position toPosition = new Position(2, 0);
        assertTrue(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testMovementOfKnightForSecondPlayer()
    {
        Position fromPosition = new Position(0, 1);
        Position toPosition = new Position(2, 0);
        makeMoveHelper(fromPosition, toPosition);
        ChessBoard chessBoard = _gameEngine.getChessBoard();
        assertTrue(chessBoard.getPiece(new Position(0, 1)) == null);
        assertTrue(chessBoard.getPiece(new Position(2, 0)) instanceof Knight);
    }

    @Test
    public void testInvalidMoveOfPawn()
    {
        Position fromPosition = new Position(6, 0);
        Position toPosition = new Position(5, 1);
        assertFalse(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testInvalidMoveWhenPieceInBetween()
    {
        Position fromPosition = new Position(7, 7);
        Position toPosition = new Position(5, 7);
        assertFalse(isValidMoveHelper(fromPosition, toPosition));
    }

    @Test
    public void testMovePieceToNonEmptyPlace()
    {
        Position fromPosition = new Position(7, 2);
        Position toPosition = new Position(6, 1);
        assertFalse(isValidMoveHelper(fromPosition, toPosition));
    }
}
